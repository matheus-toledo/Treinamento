angular.module("TreinamentoApp")
    .controller("UsuarioIncluirController", UsuarioIncluirController);


UsuarioIncluirController.$inject = ['$scope', '$http', '$state', 'UsuarioService'];

function UsuarioIncluirController($scope, $http, $state, UsuarioService) {

    $scope.incluir = incluir;

    $scope.usuario = {};

    function incluir() {
        UsuarioService.cadastrar($scope.usuario).then(response => {
                $state.go('usuarioEditar', {id: response.id});
            });
    }

}