angular.module("TreinamentoApp")
    .controller('TurmaIncluirController', TurmaIncluirController)

TurmaIncluirController.$inject = ['$scope', 'TurmaService', 'AlunoService', '$state'];

function TurmaIncluirController($scope, TurmaService, AlunoService, $state) {
    $scope.turma = {};
    $scope.turma.curso={};
    $scope.incluir = incluir;
    _inicializar();

    function _inicializar(){

    }


    function incluir() {

        //TurmaService.incluir($scope.turma);
        //$state.go('turmaEditar', {id: $scope.turma.id});
        window.alert(JSON.stringify($scope.turma));
    }

}