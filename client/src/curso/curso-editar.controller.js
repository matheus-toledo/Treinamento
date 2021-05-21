angular.module("TreinamentoApp")
    .controller("CursoEditarController", CursoEditarController);


CursoEditarController.$inject = ['$scope','$state', '$stateParams', 'CursoService'];

function CursoEditarController($scope, $state, $stateParams, CursoService) {
    _inicializar();

    function _inicializar() {
        $scope.editar = _editar;
        $scope.curso = {};

        CursoService.consultar($stateParams.id)
            .then(response => {
                $scope.curso = response;
            }).catch(()=>{
                $state.go('cursoListar');
        })
    }

    function _editar() {
        CursoService.editar($scope.curso)
            .then(() => {
                $state.go('cursoListar');
            });
    }

}