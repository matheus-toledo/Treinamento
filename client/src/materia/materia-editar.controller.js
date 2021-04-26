angular.module("TreinamentoApp")
    .controller("MateriaEditarController", MateriaEditarController);


MateriaEditarController.$inject = ['$scope', '$http', '$state', '$stateParams', 'MateriaService', 'UsuarioService'];

function MateriaEditarController($scope, $http, $state, $stateParams, MateriaService, UsuarioService) {

    $scope.editar = editar;
    $scope.materia = {};
    $scope.usuarios=[];
    _inicializar();

    function _inicializar() {
        UsuarioService.listar()
            .then(response =>{
                $scope.usuarios = response;
            });

        MateriaService.consultar($stateParams.id)
            .then(response => {
                $scope.materia = response;
            })

    }

    function editar() {
        _atualizar();
        MateriaService.editar($scope.materia)
            .then(() => {
                $state.go('materiaListar');
            })
    }

    function _atualizar(){
        $scope.materia.professor = $scope.usuarios.filter(usuario =>usuario.id===$scope.materia.professor.id)[0];
    }

}