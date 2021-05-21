angular.module("TreinamentoApp")
    .controller("UsuarioEditarController", UsuarioEditarController);


UsuarioEditarController.$inject = ['$scope', '$http', '$state', '$stateParams', 'UsuarioService'];

function UsuarioEditarController($scope, $http, $state, $stateParams, UsuarioService) {

    _inicializar();

    function _inicializar() {
        $scope.editar = editar;
        $scope.usuario = {};
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        UsuarioService.consultar($stateParams.id)
            .then(response => {
                $scope.usuario = response;
            }).catch(()=>{
                $state.go('usuarioListar');
        })
    }

    function editar() {
        UsuarioService.editar($scope.usuario)
            .then(() => {
                $state.go('usuarioListar');
            }).catch(()=>{});
    }
}