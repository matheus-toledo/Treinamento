angular.module("TreinamentoApp")
    .controller("UsuarioIncluirController", UsuarioIncluirController);


UsuarioIncluirController.$inject = ['$scope', '$http', '$state', 'UsuarioService'];

function UsuarioIncluirController($scope, $http, $state, UsuarioService) {

    $scope.incluir = _incluir;

    $scope.usuario = {};

    function _incluir() {
        UsuarioService.cadastrar($scope.usuario).then(response => {
                $scope.usuario = response;
                $state.go('usuarioEditar', {id: $scope.usuario.id});
            });
    }

}