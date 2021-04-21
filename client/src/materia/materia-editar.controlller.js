angular.module("TreinamentoApp")
    .controller("MateriaEditarController", MateriaEditarController);


MateriaEditarController.$inject = ['$scope', '$http', '$state', '$stateParams', 'MateriaService', 'UsuarioService'];

function MateriaEditarController($scope, $http, $state, $stateParams, MateriaService) {

    $scope.editar = _editar;
    $scope.materia = {};
    $scope.usuarios=[];
    _inicializar();

    function _inicializar() {
        MateriaService.consultar($stateParams.id)
            .then(response => {
                $scope.materia = response;
            })

        UsuarioService.listar()
            .then(response =>{
                $scope.usuarios = response;
            })

    }

    function _editar() {
        MateriaService.editar($scope.materia)
            .then(() => {
                $state.go('materiaListar');
            })
    }
}