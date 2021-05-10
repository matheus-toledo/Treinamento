angular.module("TreinamentoApp")
    .directive("formInputTurmaMatricula", formInputMatricula)

function formInputMatricula() {
    return {
        templateUrl: './src/directives/form-input.html',
        restrict: 'E',
        scope: {
            id: "@",
            label: "@",
            type: "@",
            ngModel: "=",
            max: "@",
            leitura: '=?',
            ngPattern: "@?",
            sigla: "="
        },
        controller: ['$scope', 'TurmaService', function ($scope, TurmaService) {
            $scope.listaDeSequencias = [];
            $scope.$watch('sigla', function () {
                if ($scope.sigla !== undefined) {
                    let result = $scope.listaDeSequencias.find(sequencia => sequencia.sigla === $scope.sigla)

                    if (result !== undefined) {
                        $scope.ngModel = $scope.sigla + ' - ' + result.sequencial;
                    } else {
                        $scope.ngModel = $scope.sigla + ' - ' + 1;
                    }

                }

            })

            _inicializar();

            function _inicializar() {
                TurmaService.listarProximasSequencias()
                    .then(sequenciasData => {
                        $scope.listaDeSequencias = sequenciasData;
                    })
            }

        }],
        link: function (scope) {
            if (scope.max === undefined) {
                scope.max = "";
            }
            if (scope.ngPattern === undefined) {
                scope.ngPattern = "";
            }
        }
    }
}