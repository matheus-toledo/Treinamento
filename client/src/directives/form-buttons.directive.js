angular.module("TreinamentoApp")
.directive("formButtons",formButtons)

function formButtons(){
    return{
        templateUrl:'./src/directives/form-buttons.html',
        restrict:'E',
        scope:{
            voltarSref: '@',
            submitSref: '@',
            submitText: '@',
        }
    }
}