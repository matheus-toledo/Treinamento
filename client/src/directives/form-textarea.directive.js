angular.module("TreinamentoApp")
    .directive("formTextarea", formTextarea)

function formTextarea() {
    formTextareaController.$inject =['$scope'];
    return {
        restrict: "E",
        templateUrl: './src/directives/form-textarea.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            pattern: "=?",
            max: "@?"

        },
        controller: formTextareaController
    }

    function formTextareaController($scope){
        _incializar();

        function _incializar(){

        }
    }

}