angular.module("TreinamentoApp")
    .controller("AlunoIncluirController", AlunoIncluirController);

AlunoIncluirController.$inject = ['$scope', '$http', '$state', 'AlunoService'];

function AlunoIncluirController($scope, $http, $state, AlunoService) {

    $scope.incluir = _incluir;

    $scope.aluno = {};

    //////////////////////////////////////

    function _incluir() {
        AlunoService.cadastrar($scope.aluno).then(response => {
            return response.id;
        }).then(id=>{
            $state.go('alunoEditar', {id: id});
        }).catch(()=>{});
    }
}