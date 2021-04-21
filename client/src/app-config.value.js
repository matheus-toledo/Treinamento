angular.module("TreinamentoApp").value("config",{
    alunoUrl: "http://localhost:8080/api/alunos",
    usuarioUrl: "http://localhost:8080/api/usuarios",
    escolaUrl: "http://localhost:8080/api/escolas",
    materiaUrl:"http://localhost:8080/api/materias",
    nomeMaxLength: 255,
    descricaoMaxLength: 300

})