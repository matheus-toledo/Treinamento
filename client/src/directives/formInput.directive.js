angular.module("TreinamentoApp")
    .directive("formInput", formInput);


function formInput() {
    return {
        templateUrl: '../client/src/view/formInput.html',
        restrict: 'E',
        scope: {
            id: "@",
            label: "@",
            type: "@",
            ngModel: "=",
            max: "@",
            ngPattern: "@?"
        },
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