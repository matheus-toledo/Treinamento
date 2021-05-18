angular.module("TreinamentoApp")
    .controller("TurmaListarController", TurmaListarController)

TurmaListarController.$inject = ['$scope', 'TurmaService','$state'];

function TurmaListarController($scope, TurmaService, $state) {
    $scope.turmas = [];
    $scope.deletar = deletar;
    $scope.editar = editar;

    _inicializar();

    function _inicializar() {
        _listar();
    }

    function editar(id) {
        $state.go('turmaEditar', {id: id})
    }

    function deletar(nome, id) {
        if (confirm(`Deseja deletar a turma ${nome}`)) {
            TurmaService.deletar(id).then(() => {
                _atualizar(id);
            });
        }
    }

    function _listar() {
        TurmaService.listar().then(response => {
            $scope.turmas = response;
        })
    }

    function _atualizar(id) {
       $scope.turmas = $scope.turmas.filter(turma => turma.id !== id)
    }
}