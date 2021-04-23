angular.module('TreinamentoApp').config(MateriaRoute);

UsuarioRoute.$inject = ['$stateProvider'];

function MateriaRoute($stateProvider){
    $stateProvider
        .state('materiaListar',{
            url: '/materias',
            templateUrl: './src/materia/materia-listar.html',
            controller: 'MateriaListarController'
        })
        .state({
            name: 'materiaEditar',
            url: '/materias/{id:int}',
            templateUrl: './src/materia/materia-editar.html',
            controller: 'MateriaEditarController'
        })
        .state({
            name: 'materiaIncluir',
            url: '/materias/novo',
            templateUrl: './src/materia/materia-incluir.html',
            controller: 'MateriaIncluirController'
        });
}




