angular.module('TreinamentoApp').config(EscolaRoute);

EscolaRoute.$inject = ['$stateProvider'];

function EscolaRoute($stateProvider){
    $stateProvider
        .state({
            name:'escolaListar',
            url: '/escolas',
            templateUrl: './src/escola/escola-listar.html',
            controller: 'EscolaListarController'
        })
        .state({
            name: 'escolaIncluir',
            url: '/escolas/novo',
            templateUrl: './src/escola/escola-incluir.html',
            controller: 'EscolaIncluirController'
        })
        .state({
            name: 'escolaEditar',
            url: '/escolas/{id:int}',
            templateUrl: './src/escola/escola-editar.html',
            controller: 'EscolaEditarController'
        });
}

