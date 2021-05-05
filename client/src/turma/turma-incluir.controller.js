angular.module("TreinamentoApp")
    .controller('TurmaIncluirController', TurmaIncluirController)

TurmaIncluirController.$inject = ['$scope', 'TurmaService', 'AlunoService', 'CursoService', '$state'];

function TurmaIncluirController($scope, TurmaService, AlunoService, CursoService, $state) {
    $scope.turma = {};
    $scope.alunosId = [];
    $scope.cursoId = [];
    $scope.incluir = incluir;

    _inicializar();

    function incluir() {
        TurmaService.incluir($scope.turma);
        $state.go('turmaEditar', {id: $scope.turma.id});
    }

    function _inicializar() {
        Promise.all([AlunoService.listar(), CursoService.listar()])
            .then(values => {
                $scope.alunosId = values[0].map(aluno => aluno.id);
                $scope.cursoId = values[1].map(curso => curso.id);
            })
    }
}