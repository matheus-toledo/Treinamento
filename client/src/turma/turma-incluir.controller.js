angular.module("TreinamentoApp")
    .controller('TurmaIncluirController', TurmaIncluirController)

TurmaIncluirController.$inject = ['$scope', 'TurmaService', '$state','$stateParams'];

function TurmaIncluirController($scope, TurmaService, $state) {

    _inicializar();

    ////////////////////////////////////////////////////////

    function _inicializar() {
        $scope.incluir = incluir;
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.siglaPattern = /^[A-Z]{1,5}$/;
        $scope.turma = {
            curso: {},
            alunosIds: []
        };
    }

    function incluir() {

        TurmaService.incluir($scope.turma)
            .then(responseData=>{
                $state.go('turmaEditar', {id: responseData.id});
            })
    }
}