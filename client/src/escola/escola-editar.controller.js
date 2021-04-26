angular.module("TreinamentoApp")
    .controller("EscolaEditarController", EscolaEditarController);


EscolaEditarController.$inject = ['$scope', '$http', '$state', 'EscolaService', '$stateParams', 'UsuarioService'];

function EscolaEditarController($scope, $http, $state, EscolaService, $stateParams, UsuarioService) {

    $scope.editar = editar;
    $scope.escola = {};
    $scope.usuarios = [];

    _inicializar();

    ////////////////////////

    function _inicializar() {
        UsuarioService.listar()
            .then(response => {
                $scope.usuarios = response;
            });

        EscolaService.consultar($stateParams.id)
            .then(response => {
                $scope.escola = response;
            });
    }

    function editar() {
        _atualizarDiretor();
        EscolaService.editar($scope.escola)
            .then(() => {
                $state.go('escolaListar');
            });

    }

    function _atualizarDiretor(){
        console.log($scope.usuarios.filter(usuario =>usuario.id===$scope.escola.diretor.id)[0])
        $scope.escola.diretor = $scope.usuarios.filter(usuario =>usuario.id===$scope.escola.diretor.id)[0];
    }
}