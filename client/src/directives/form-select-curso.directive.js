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
            sigla: "="
        },
        controller: formSelectCursoController
    }

    formSelectCursoController.$inject = ['$scope', 'CursoService']

    function formSelectCursoController($scope, CursoService) {

        $scope.$watch('ngModel', _watchNgModel);

        _inicializar();

        function _inicializar() {
            $scope.defaultOptionText = '--Selecione um Curso--'

            $scope.options = [];

            CursoService.listar().then(cursosData => {
                $scope.options = cursosData;
            })
        }

        function _watchNgModel(newValue) {
            if (newValue && $scope.options) {
                $scope.sigla = $scope.options.find(option => option.id === $scope.ngModel).sigla;
            }
        }


    }

}