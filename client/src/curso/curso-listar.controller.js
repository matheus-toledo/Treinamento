angular.module("TreinamentoApp")
    .controller("CursoListarController", CursoListarController);

CursoListarController.$inject = ['$scope', '$http', '$state', 'CursoService','configParams'];

function CursoListarController($scope, $http, $state, CursoService, configParams) {
    _inicializar();

    function _inicializar() {
        $scope.editar = editar;
        $scope.deletar = deletar;
        $scope.cursos=[];
        _listar();
    }

    function _atualizar(id) {
        $scope.cursos = $scope.cursos.filter(curso => curso.id !== id);
    }


    function deletar(nome, id) {
        if (confirm(`Deseja deletar o curso: ${nome}?`)) {
            CursoService.deletar(id).then(() => {
                _atualizar(id);
            });
        }
    }

    function editar(id) {
        $state.go('cursoEditar', {id:id});
    }

    function _listar() {
        CursoService.listar()
            .then(response => {
                $scope.cursos = response;
            })
    }

}