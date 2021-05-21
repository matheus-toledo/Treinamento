package com.docnix.service;

import com.docnix.entity.Aluno;
import com.docnix.entity.Turma;

import com.docnix.exceptionMapper.NotFoundException;
import com.docnix.exceptionMapper.RegraDeNegocioException;
import com.docnix.exceptionMapper.ServerException;
import com.docnix.repository.AlunoRepository;
import com.docnix.repository.TurmaRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TurmaService {
    private static final TurmaRepository turmaRepository = new TurmaRepository();
    private static final AlunoService alunoService = new AlunoService();
    private static final AlunoRepository alunoRepository = new AlunoRepository();

    public Turma inserir(Turma turma) throws RegraDeNegocioException, ServerException {
        if (turmaRepository.obterNome(turma.getNome()).isPresent()){
            throw new RegraDeNegocioException("Já existe uma turma cadastrada com esse nome!");
        }

        if (turmaRepository.obterSigla(turma.getSigla()).isPresent()){
            throw new RegraDeNegocioException("Já existe uma turma cadastrada com essa sigla!");
        }

        for (Long id : turma.getAlunosIds()) {
            if(alunoRepository.temTurma(id)){
                throw new RegraDeNegocioException("A turma não pode ser cadastrada pois um ou mais alunos estão cadastrados em outras turmas!");
            }
        }

        gerarSequencia(turma);
        turma.setAlunos(new HashSet<>());
        turma.getAlunosIds().forEach(alunoId -> turma.getAlunos().add(new Aluno(alunoId)));
        Turma turmaPersistida = turmaRepository.salvar(turma);
        alunoService.updateSequencia(turma.getAlunosIds(), 0L);

        return turmaPersistida;
    }

    private void gerarSequencia(Turma turma) {
        Long result = turmaRepository.consultarMaiorSequencia(turma).orElse(0L);
        turma.setSequencia(result + 1);
    }

    public String gerarMatricula(String sigla, Long id) {
        Long result;

        if (Optional.ofNullable(id).isPresent() && turmaRepository.consultarSiglaCurso(sigla, id).isPresent()) {
            result = turmaRepository.consultarSequencia(id);
            return String.format("%s - %d", sigla, result);
        }

        result = turmaRepository.consultarMaiorSequencia(sigla).orElse(0L);
        return String.format("%s - %d", sigla, result + 1);
    }

    public Turma obter(Long id) throws ServerException, NotFoundException {
        Turma turma = turmaRepository.obter(id);
        if (turma == null){
            throw new NotFoundException("Não existe essa turma no sistema!");
        }
        //listar alunos da turma
        return turma;
    }


    public List<Turma> listar() throws ServerException {
        return turmaRepository.listar();
    }

    public Turma editar(Turma turma) throws RegraDeNegocioException, ServerException {
        if (turmaRepository.obterNome(turma.getNome(),turma.getId()).isPresent()){
            throw new RegraDeNegocioException("Já existe uma turma cadastrada com esse nome!");
        }

        if (turmaRepository.obterSigla(turma.getSigla(),turma.getId()).isPresent()){
            throw new RegraDeNegocioException("Já existe uma turma cadastrada com essa sigla!");
        }

        if (!turma.getCurso().getId().equals(turmaRepository.consultarCursoDaTurma(turma.getId()))) {
            gerarSequencia(turma);
        }

        turma.setAlunos(new HashSet<>());
        turma.getAlunosIds().forEach(id -> turma.getAlunos().add(new Aluno(id)));

        Long sequencialDosAlunos = turmaRepository.obterMaiorSequencialDoAlunosDeUmaTurma(turma.getId()).orElse(0L);

        List<Long> idsAlunosDaTurmaOriginal = turmaRepository.obterIdAlunosDaTurmaOriginal(turma.getId());

        verificaAlunosEmOutrasTurmas(idsAlunosDaTurmaOriginal,turma.getAlunosIds());


        List<Long> idsParaRemoverSequecia = obterIdsDosAlunosParaRemoverSequencia(idsAlunosDaTurmaOriginal, turma.getAlunosIds());


        List<Long> idsParaInserirSequencia = obterIdsDosAlunosParaAdicionarSequencia(turma.getAlunosIds(), idsAlunosDaTurmaOriginal);


        Turma turmaEditada = turmaRepository.editar(turma);


        alunoService.removerSequencias(idsParaRemoverSequecia);

        alunoService.updateSequencia(idsParaInserirSequencia, sequencialDosAlunos);

        return turmaEditada;
    }

    private void verificaAlunosEmOutrasTurmas(List<Long> idsAlunosDaTurmaOriginal, List<Long> alunosIds) throws RegraDeNegocioException {
        for (Long novoId : alunosIds) {
            if(alunoRepository.temTurma(novoId) && !idsAlunosDaTurmaOriginal.contains(novoId)){
                throw new RegraDeNegocioException("A turma não pode ser editada pois está tentando inserir um ou mais alunos que já estão matriculados em outras turmas!");
            }

        }
    }


    public void deletar(Long id) {
        List<Long> idsAlunosTurma = turmaRepository.obterIdAlunosDaTurmaOriginal(id);
        turmaRepository.deletar(id);
        alunoService.removerSequencias(idsAlunosTurma);
    }

    private List<Long> obterIdsDosAlunosParaRemoverSequencia(List<Long> idsAlunosDaTurmaOriginal, List<Long> idsAlunosDaNovaTurma) {
        return idsAlunosDaTurmaOriginal.stream().filter(id -> !idsAlunosDaNovaTurma.contains(id)).collect(Collectors.toList());
    }

    private List<Long> obterIdsDosAlunosParaAdicionarSequencia(List<Long> idsAlunosDaNovaTurma, List<Long> idsAlunosDaTurmaOriginal) {
        return idsAlunosDaNovaTurma.stream().filter(id -> !idsAlunosDaTurmaOriginal.contains(id)).collect(Collectors.toList());
    }


}
