angular.module("TreinamentoApp")
    .controller("AlunoEditarControler", AlunoEditarControler);


AlunoEditarControler.$inject = ['$scope', '$http', '$state', 'AlunoService', '$stateParams'];

function AlunoEditarControler($scope, $http, $state, AlunoService, $stateParams) {
    $scope.aluno = {};
    $scope.editar = editar;

    _inicializar();

    /////////////////////////////////////

    function _inicializar() {
        AlunoService.consultar($stateParams.id)
            .then(response => {
                $scope.aluno = response;
            }).catch(()=>{
                $state.go('alunoListar');
        })
    }

    function editar() {
        AlunoService.editar($scope.aluno)
            .then(() => {
                $state.go('alunoListar');
            }).catch(()=>{});

    }
}