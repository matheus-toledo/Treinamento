angular.module("TreinamentoApp")
    .directive("formRadio", formRadio)

function formRadio() {
    return {
        restrict: "E",
        templateUrl: './src/directives/form-radio.html',
        scope: {
            label: "@",
            ngModel: "=",
            grupo: "@"
        }
    }
}