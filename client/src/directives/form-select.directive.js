angular.module("TreinamentoApp")
    .directive("formSelect", formSelect)

function formSelect() {
    return {
        restrict: "E",
        templateUrl: './src/directives/form-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
            ngMultiple: "@",
            options: "=",
            defaultOption: "@"
        },
        link:function(scope,element,attr,ngModel) {

        }
    }
}