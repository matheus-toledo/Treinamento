angular.module("TreinamentoApp")
    .directive("formSelectCurso", formSelectCurso)

function formSelectCurso() {
    return {
        restrict: "E",
        templateUrl: './src/directives/form-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            ngMultiple: "@?",
            defaultOption: "@",
        },
        controller: ['$scope', 'CursoService', function ($scope, CursoService) {
            $scope.options = [];
            $scope.$watch('ngModel.id', function () {
                if ($scope.ngModel.id !== undefined && $scope.options !== undefined) {
                    $scope.ngModel.sigla = $scope.options.find(option => option.id === $scope.ngModel.id).sigla;
                }
            })

            _inicializar();

            function _inicializar() {

                CursoService.listar().then(cursosData => {
                    $scope.options = cursosData;
                })
            }

        }]
    }
}