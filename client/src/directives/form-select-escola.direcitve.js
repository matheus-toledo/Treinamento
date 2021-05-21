angular.module("TreinamentoApp")
    .directive("formSelectEscola", formSelectEscola)

function formSelectEscola() {
    formSelectEscolaController.$inject = ['$scope', 'EscolaService']

    return {
        restrict: "E",
        templateUrl: './src/directives/form-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
        },
        controller: formSelectEscolaController
    }

    function formSelectEscolaController($scope, EscolaService) {
        _inicializar();

        function _inicializar() {
            $scope.defaultOptionText = '--Selecione uma Escola--'

            $scope.options = [];

            EscolaService.listar().then(escolaData => {
                $scope.options = escolaData;
            })
        }
    }
}