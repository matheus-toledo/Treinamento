angular.module('TreinamentoApp').config(CursoRoute);

CursoRoute.$inject = ['$stateProvider'];

function CursoRoute($stateProvider){
    $stateProvider
        .state('cursoListar',{
            url: '/cursos',
            templateUrl: './src/curso/curso-listar.html',
            controller: 'CursoListarController'
        })
        .state({
            name: 'cursoEditar',
            url: '/cursos/{id:int}',
            templateUrl: './src/curso/curso-editar.html',
            controller: 'CursoEditarController'
        })
        .state({
            name: 'cursoIncluir',
            url: '/cursos/novo',
            templateUrl: './src/curso/curso-incluir.html',
            controller: 'CursoIncluirController'
        });
}




