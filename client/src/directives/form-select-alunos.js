angular.module("TreinamentoApp")
    .directive("formSelectAlunos", formSelectAlunos)

function formSelectAlunos() {
    return {
        restrict: "E",
        templateUrl: './src/directives/form-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            ngMultiple: "@?"
        },
        controller: ['$scope','AlunoService', function ($scope, AlunoService) {
            $scope.options = [];


            _inicializar();

            function _inicializar() {
                /*AlunoService.listar().then(alunosData => {
                    $scope.options = alunosData;
                })*/
            }

        }]
    }
}