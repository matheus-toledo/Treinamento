angular.module("TreinamentoApp")
    .controller("MateriaListarController", MateriaListarController);

MateriaListarController.$inject = ['$scope', '$http', '$state', 'MateriaService'];

function MateriaListarController($scope, $http, $state, MateriaService) {
    $scope.editar = _editar;
    $scope.deletar = _deletar;
    $scope.materias=[];

    _inicializar();

    function _inicializar() {
        _listar();
    }

    function _atualizar(id) {
        $scope.materias = $scope.materias.filter(materia => materia.id !== id);
    }


    function _deletar(nome, id) {
        if (confirm(`Deseja deletar a matéria ${nome}?`)) {
            MateriaService.deletar(id).then(() => {
                _atualizar(id);
            });
        }
    }

    function _editar(id) {
        $state.go('materiaEditar', {id:id});
    }

    function _listar() {
        MateriaService.listar()
            .then(response => {
                $scope.materias = response;
            })

    }

}