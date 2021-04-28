angular.module("TreinamentoApp")
    .controller("UsuarioEditarController", UsuarioEditarController);


UsuarioEditarController.$inject = ['$scope', '$http', '$state', '$stateParams', 'UsuarioService'];

function UsuarioEditarController($scope, $http, $state, $stateParams, UsuarioService) {

    $scope.editar = editar;

    _inicializar();

    function _inicializar() {
        $scope.usuario = {};
        UsuarioService.consultar($stateParams.id)
            .then(response => {
                $scope.usuario = response;
            })
    }

    function editar() {
        UsuarioService.editar($scope.usuario)
            .then(() => {
                $state.go('usuarioListar');
            })
    }
}