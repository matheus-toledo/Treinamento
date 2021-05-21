angular.module("TreinamentoApp")
    .controller("EscolaEditarController", EscolaEditarController);


EscolaEditarController.$inject = ['$scope', '$http', '$state', 'EscolaService', '$stateParams'];

function EscolaEditarController($scope, $http, $state, EscolaService, $stateParams) {
    _inicializar();

    ////////////////////////

    function _inicializar() {
        $scope.editar = editar;
        $scope.escola = {};
        EscolaService.consultar($stateParams.id)
            .then(response => {
                $scope.escola = response;
            }).catch(() => {
            $state.go('escolaListar');
        })
    }

    function editar() {
        EscolaService.editar($scope.escola)
            .then(() => {
                $state.go('escolaListar');
            }).catch(()=>{});
    }
}