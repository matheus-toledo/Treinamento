angular.module("TreinamentoApp")
    .controller("AlunoIncluirController", AlunoIncluirController);

AlunoIncluirController.$inject = ['$scope', '$http', '$state', 'AlunoService'];

function AlunoIncluirController($scope, $http, $state, AlunoService) {

    $scope.incluir = _incluir;

    $scope.aluno = {};

    //////////////////////////////////////

    function _incluir() {
        AlunoService.cadastrar($scope.aluno).then(response => {
            $scope.aluno = response;
            $state.go('alunoEditar', {id: $scope.aluno.id});
        })
    }
}