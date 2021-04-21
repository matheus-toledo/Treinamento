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
            templateUrl: './src/materia/usuario-editar.html',
            controller: 'MateriaEditarController'
        })
        .state({
            name: 'materiaIncluir',
            url: '/materia/novo',
            templateUrl: './src/materia/usuario-incluir.html',
            controller: 'MateriaIncluirController'
        });
}




