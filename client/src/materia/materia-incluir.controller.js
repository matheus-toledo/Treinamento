angular.module("TreinamentoApp")
    .controller("MateriaIncluirController", MateriaIncluirController);


MateriaIncluirController.$inject = ['$scope', '$http', '$state', 'UsuarioService','MateriaService'];

function MateriaIncluirController($scope, $http, $state, UsuarioService, MateriaService) {

    $scope.incluir = _incluir;
    $scope.materia = {};
    $scope.usuarios = [];

    _inicializar()

    function _inicializar(){
        UsuarioService.listar()
            .then(response =>{
                $scope.usuarios = response;
            })
    }

    function _incluir() {
        MateriaService.cadastrar($scope.materia).then(response => {
            $scope.materia = response;
            $state.go('materiaEditar', {id: $scope.materia.id});
        });
    }

}