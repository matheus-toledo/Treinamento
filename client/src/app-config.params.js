angular.module("TreinamentoApp").value("configParams", {
    alunoUrl: "http://localhost:8080/api/alunos",
    usuarioUrl: "http://localhost:8080/api/usuarios",
    escolaUrl: "http://localhost:8080/api/escolas",
    materiaUrl: "http://localhost:8080/api/materias",
    cursoUrl: "http://localhost:8080/api/cursos",
    nomeMaxLength: 255,
    descricaoMaxLength: 300,
    materiaDescricaoMaxLength: 2500,
    cursoNomeMaxLength: 255,
    cursoSiglaMaxLength: 5,
    cursoDescricaoMaxLength: 2500
})