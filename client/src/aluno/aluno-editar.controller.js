angular.module("TreinamentoApp")
    .controller("AlunoEditarControler", AlunoEditarControler);


AlunoEditarControler.$inject = ['$scope', '$http', '$state', 'AlunoService', '$stateParams'];

function AlunoEditarControler($scope, $http, $state, AlunoService, $stateParams) {

    $scope.editar = _editar;

    _inicializar();

    ////////////////////////

    function _inicializar() {
        $scope.aluno = {};

        AlunoService.consultar($stateParams.id)
            .then(response => {
                $scope.aluno = response;
            });
    }

    function _editar() {
        AlunoService.editar($scope.aluno)
            .then(() => {
                $state.go('alunoListar');
            });

    }
}