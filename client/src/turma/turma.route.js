angular.module("TreinamentoApp")
    .config(TurmaRoute);

TurmaRoute.$inject = ['$stateProvider'];

function TurmaRoute($stateProvider) {
    $stateProvider
        .state({
            name: 'turmaListar',
            url: '/turmas',
            templateUrl: './src/turma/turma-listar.html',
            controller: 'TurmaListarController'
        })
        .state({
            name: 'turmaIncluir',
            url: '/turmas/novo',
            templateUrl: './src/turma/turma-incluir.html',
            controller: 'TurmaIncluirController'
        })
        .state({
            name: 'turmaEditar',
            url: '/turmas/{id:int}',
            templateUrl: './src/turma/turma-editar.html',
            controller: 'TurmaEditarController'
        })

}