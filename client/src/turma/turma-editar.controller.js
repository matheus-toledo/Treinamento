angular.module("TreinamentoApp")
    .controller("TurmaEditarController", TurmaEditarController);

TurmaEditarController.$inject = ['$scope', '$stateParams', 'AlunoService', 'CursoService','TurmaService', '$state'];

function TurmaEditarController($scope, $stateParams, AlunoService, CursoService, TurmaService, $state) {
    $scope.turma = {};
    $scope.cursosId = [];
    $scope.alunosId = [];
    $scope.editar = editar;

    _inicializar();

    function _inicializar() {
        Promise.all([TurmaService.obter($stateParams.id), AlunoService.listar(), CursoService.listar()])
            .then(values => {
                $scope.turma = values[0];
                $scope.alunosId = values[1].map(aluno => aluno.id);
                $scope.cursosId = values[2].map(curso => curso.id);
            })
    }

    function editar() {
        TurmaService.editar($scope.turma);
        $state.go('turmaListar');
    }


}