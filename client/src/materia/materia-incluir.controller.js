angular.module("TreinamentoApp")
    .controller("MateriaIncluirController", MateriaIncluirController);


MateriaIncluirController.$inject = ['$scope', '$http', '$state', 'UsuarioService','MateriaService', 'configParams'];

function MateriaIncluirController($scope, $http, $state, UsuarioService, MateriaService, configParams) {

    $scope.incluir = incluir;
    $scope.materia = {};
    $scope.usuarios = [];
    _inicializar()

    function _inicializar(){
        UsuarioService.listar()
            .then(response =>{
                $scope.usuarios = response;
                $scope.materia.professor=response[0];
            })
    }

    function incluir() {
        MateriaService.cadastrar($scope.materia).then(response => {
            $state.go('materiaEditar', {id: response.id});
        });
    }

}