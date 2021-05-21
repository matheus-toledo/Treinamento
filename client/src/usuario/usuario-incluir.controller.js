angular.module("TreinamentoApp")
    .controller("UsuarioIncluirController", UsuarioIncluirController);

UsuarioIncluirController.$inject = ['$scope', '$http', '$state', 'UsuarioService'];

function UsuarioIncluirController($scope, $http, $state, UsuarioService) {

    _inicializar();

    function _inicializar(){
        $scope.incluir = incluir;
        $scope.usuario = {};
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/
    }

    function incluir() {
        UsuarioService.cadastrar($scope.usuario).then(response => {
                $state.go('usuarioEditar', {id: response.id});
            }).catch(()=>{});
    }
}