angular.module("TreinamentoApp")
    .directive("formSelectMultipleMateria", formSelectMultipleMateria)

function formSelectMultipleMateria() {
    formSelectMultipleMateriaController.$inject = ['$scope', 'MateriaService'];

    return {
        restrict: "E",
        templateUrl: './src/directives/form-select-multiple.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
        },
        controller: formSelectMultipleMateriaController,
    }

    function formSelectMultipleMateriaController($scope, MateriaService) {

        _inicializar();

        ///////////////////////////////

        function _inicializar() {
            $scope.options = [];

            MateriaService.listar().then(MateriaService => {
                $scope.options = MateriaService;
            })
        }

    }

}