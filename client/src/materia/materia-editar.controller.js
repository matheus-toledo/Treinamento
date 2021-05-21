angular.module("TreinamentoApp")
    .controller("MateriaEditarController", MateriaEditarController);


MateriaEditarController.$inject = ['$scope', '$state', '$stateParams', 'MateriaService'];

function MateriaEditarController($scope, $state, $stateParams, MateriaService) {
    _inicializar();

    function _inicializar() {
        $scope.editar = editar;
        $scope.materia = {};
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.descricaoPattern = /^[a-zA-Z](\s|\S|\d){0,2499}$/;
        MateriaService.consultar($stateParams.id)
            .then(response => {
                $scope.materia = response;
            }).catch(()=>{
                $state.go('materiaListar');
        })
    }

    function editar() {
        MateriaService.editar($scope.materia)
            .then(() => {
                $state.go('materiaListar');
            })
    }
}