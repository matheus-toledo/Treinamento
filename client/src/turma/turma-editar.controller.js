angular.module("TreinamentoApp")
    .controller("TurmaEditarController", TurmaEditarController);

TurmaEditarController.$inject = ['$scope', '$stateParams', 'TurmaService', '$state'];

function TurmaEditarController($scope, $stateParams, TurmaService, $state) {
    _inicializar();
    //////////////////////////////////////

    function _inicializar() {
        $scope.editar = editar;
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.siglaPattern = /^[A-Z]{1,5}$/;

        TurmaService.obter($stateParams.id)
            .then(turmaData => {
                $scope.turma = turmaData;
            }).catch(()=>{
                $state.go('turmaListar');
        })
    }

    function editar() {
        TurmaService.editar($scope.turma).then(()=>{
            $state.go('turmaListar');
        })
    }

}