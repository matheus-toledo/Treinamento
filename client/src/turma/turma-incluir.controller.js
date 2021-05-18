angular.module("TreinamentoApp")
    .controller('TurmaIncluirController', TurmaIncluirController)

TurmaIncluirController.$inject = ['$scope', 'TurmaService', '$state', 'AlunoService','$stateParams'];

function TurmaIncluirController($scope, TurmaService, $state, AlunoService,$stateParams) {

    $scope.incluir = incluir;

    _inicializar();

    ////////////////////////////////////////////////////////

    function _inicializar() {
        $scope.turma = {
            curso: {},
            alunos: []
        };

    }

    function incluir() {
 /*       $scope.turma.alunos = $scope.turma.alunos.map(id => {
            return {id:id}
        })*/

        $scope.turma.alunosIds = angular.copy($scope.turma.alunos);
        $scope.turma.alunos = [];

        TurmaService.incluir($scope.turma)
            .then(responseData=>{

                $state.go('turmaEditar', {id: responseData.id});
            })
    }

    function getAlunos(arr) {
        return arr.filter(elem => {
            if ($scope.turma.alunos.includes(elem.id)) {
                return elem
            }
        })
    }


}