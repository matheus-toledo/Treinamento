angular.module("TreinamentoApp")
    .directive("formInputReadonly", formInputReadonly);


function formInputReadonly() {
    return {
        templateUrl: './src/directives/form-input-readonly.html',
        restrict: 'E',
        scope: {
            id: "@",
            label: "@",
            type: "@",
            ngModel: "=",
        }
    }
}