angular.module("TreinamentoApp")
    .controller("TurmaEditarController", TurmaEditarController);

TurmaEditarController.$inject = ['$scope', '$stateParams', 'TurmaService', '$state'];

function TurmaEditarController($scope, $stateParams, TurmaService, $state) {
    $scope.editar = editar;
    _inicializar();

    //////////////////////////////////////

    function _inicializar() {

        TurmaService.obter($stateParams.id)
            .then(turmaData => {
                $scope.turma = turmaData;
            })
    }

    function editar() {
        TurmaService.editar($scope.turma).then(()=>{
            $state.go('turmaListar');
        })
    }

}