angular.module("TreinamentoApp")
    .directive("formSelectUsuarios", formSelectUsuarios)

function formSelectUsuarios() {
    formSelectUsuariosController.$inject =['$scope','UsuarioService'];
    return {
        restrict: "E",
        templateUrl: './src/directives/form-select.html',
        scope: {
            id: "@",
            label: "@",
            ngModel: "=",
        },
        controller: formSelectUsuariosController
    }

    function formSelectUsuariosController($scope,UsuarioService){
        _incializar();

        function _incializar(){
            $scope.defaultOptionText = '--Selecione um usuário--';
            $scope.options=[];
            UsuarioService.listar()
                .then(usuariosData=>{
                    $scope.options = usuariosData;
                })
        }
    }

}