angular.module("TreinamentoApp")
    .controller("EscolaEditarController", EscolaEditarController);


EscolaEditarController.$inject = ['$scope', '$http', '$state', 'EscolaService', '$stateParams', 'UsuarioService'];

function EscolaEditarController($scope, $http, $state, EscolaService, $stateParams, UsuarioService) {

    $scope.editar = _editar;
    $scope.escola = {};
    $scope.usuarios = [];
    $scope.simChecked = undefined;
    $scope.naoChecked = undefined;

    _inicializar();

    ////////////////////////

    function _inicializar() {
        UsuarioService.listar()
            .then(response => {
                $scope.usuarios = response;
            }).then(() => {

        })

        EscolaService.consultar($stateParams.id)
            .then(response => {
                $scope.escola = response;
                $scope.simChecked=$scope.escola.ativa;
                $scope.naoChecked=!$scope.simChecked;
            });

    }

    function _editar() {
        console.log($scope.escola.diretor);
        EscolaService.editar($scope.escola)
            .then(() => {
                $state.go('escolaListar');
            });

    }
}