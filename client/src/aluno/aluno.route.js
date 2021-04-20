angular.module('TreinamentoApp').config(AlunoRoute);

AlunoRoute.$inject = ['$stateProvider'];

function AlunoRoute($stateProvider){
    $stateProvider
        .state('alunoListar',{
            url: '/alunos',
            templateUrl: './src/aluno/aluno-listar.html',
            controller: 'AlunoListarControler'
        })
        .state({
            name: 'alunoEditar',
            url: '/alunos/{id:int}',
            templateUrl: './src/aluno/aluno-editar.html',
            controller: 'AlunoEditarControler'
        })
        .state({
            name: 'alunoIncluir',
            url: '/alunos/novo',
            templateUrl: './src/aluno/aluno-incluir.html',
            controller: 'AlunoIncluirController'
        });
}




