angular.module("TreinamentoApp")
    .directive("formInputTurmaMatricula", formInputMatricula)

function formInputMatricula() {
    return {
        templateUrl: './src/directives/form-input-readonly.html',
        restrict: 'E',
        scope: {
            id: "@",
            label: "@",
            type: "@",
            ngModel: "=",
            max: "@",
            ngPattern: "@?",
            sigla: "=",
            turmaId: "=?"
        },
        controller: formInputMatriculaController,
        link: function (scope) {
            if (scope.max === undefined) {
                scope.max = "";
            }
            if (scope.ngPattern === undefined) {
                scope.ngPattern = "";
            }
        }
    }

    formInputMatriculaController.$inject = ['$scope', 'TurmaService'];

    function formInputMatriculaController($scope,TurmaService){
        $scope.$watch('sigla', _watchNgModel)

        _inicializar();

        /////////////////////////////////

        function _inicializar() {
            if(!$scope.turmaId){
                $scope.turmaId="";
            }
        }

        function _watchNgModel(newValue){
            if (newValue) {
                TurmaService.gerarMatricula(newValue,$scope.turmaId)
                    .then(matricula => {
                        $scope.ngModel = matricula;
                    })
            }
        }
    }

}
