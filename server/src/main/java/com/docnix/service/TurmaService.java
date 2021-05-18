package com.docnix.service;

import com.docnix.entity.Aluno;
import com.docnix.entity.Turma;

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

    public Turma inserir(Turma turma) {
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

    public Turma obter(Long id) {
        Turma turma = turmaRepository.obter(id);
        //listar alunos da turma
        return turma;
    }

    //listar
    public List<Turma> listar() {
        return turmaRepository.listar();
    }

    //editar
    public Turma editar(Turma turma) {
        //verificar se o curso mudou
        if (turma.getCurso().getId() != turmaRepository.consultarCursoDaTurma(turma.getId())) {
            //-se mudou troca o sequencial
            gerarSequencia(turma);
        }

        //gerar alunos da turma
        turma.setAlunos(new HashSet<>());
        turma.getAlunosIds().forEach(id -> turma.getAlunos().add(new Aluno(id)));

        //gerar o sequencial dos alunos
        Long sequencialDosAlunos = turmaRepository.obterMaiorSequencialDoAlunosDeUmaTurma(turma.getId()).orElse(0L);

        //alunos originais
        List idsAlunosDaTurmaOriginal = turmaRepository.obterIdAlunosDaTurmaOriginal(turma.getId());


        //lista de alunos para remover sequencial
        List idsParaRemoverSequecia = obterIdsDosAlunosParaRemoverSequencia(idsAlunosDaTurmaOriginal, turma.getAlunosIds());

        //lista de alunos para adicionar sequencial
        List idsParaInserirSequencia = obterIdsDosAlunosParaAdicionarSequencia(turma.getAlunosIds(), idsAlunosDaTurmaOriginal);

        //editar turma
        Turma turmaEditada = turmaRepository.editar(turma);

        //remover sequencia dos alunos que deixaram de ser da turma
        alunoService.removerSequencias(idsParaRemoverSequecia);

        alunoService.updateSequencia(idsParaInserirSequencia, sequencialDosAlunos);

        return turmaEditada;
    }

    //deletar
    public void deletar(Long id) {
        List idsAlunosTurma = turmaRepository.obterIdAlunosDaTurmaOriginal(id);
        alunoService.removerSequencias(idsAlunosTurma);
        turmaRepository.deletar(id);
    }

    private List obterIdsDosAlunosParaRemoverSequencia(List idsAlunosDaTurmaOriginal, List idsAlunosDaNovaTurma) {
        return (List) idsAlunosDaTurmaOriginal.stream().filter(id -> !idsAlunosDaNovaTurma.contains(id)).collect(Collectors.toList());
    }

    private List obterIdsDosAlunosParaAdicionarSequencia(List idsAlunosDaNovaTurma, List idsAlunosDaTurmaOriginal) {
        return (List) idsAlunosDaNovaTurma.stream().filter(id -> !idsAlunosDaTurmaOriginal.contains(id)).collect(Collectors.toList());
    }


}
