angular.module("TreinamentoApp")
    .directive("formSelectAlunos", formSelectAlunos)

function formSelectAlunos() {
    return {
        restrict: "E",
        templateUrl: './src/directives/form-select-multiple.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            ngMultiple: "@?"
        },
        controller: formSelectAlunosController,
    }

    formSelectAlunosController.$inject = ['$scope', 'AlunoService'];

    function formSelectAlunosController($scope, AlunoService) {

        _inicializar();

        ///////////////////////////////

        function _inicializar() {
            $scope.options = [];

            AlunoService.listar().then(alunosData => {
                $scope.options = alunosData;
            })
        }

    }

}