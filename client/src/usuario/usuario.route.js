angular.module('TreinamentoApp').config(UsuarioRoute);

UsuarioRoute.$inject = ['$stateProvider'];

function UsuarioRoute($stateProvider){
    $stateProvider
        .state('usuarioListar',{
            url: '/usuarios',
            templateUrl: './src/usuario/usuario-listar.html',
            controller: 'UsuarioListarController'
        })
        .state({
            name: 'usuarioEditar',
            url: '/usuarios/{id:int}',
            templateUrl: './src/usuario/usuario-editar.html',
            controller: 'UsuarioEditarController'
        })
        .state({
            name: 'usuarioIncluir',
            url: '/usuarios/novo',
            templateUrl: './src/usuario/usuario-incluir.html',
            controller: 'UsuarioIncluirController'
        });
}




