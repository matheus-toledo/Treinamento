angular.module("TreinamentoApp")
    .controller("TurmaListarController", TurmaListarController)

TurmaListarController.$inject = ['$scope', 'TurmaService'];

function TurmaListarController($scope, TurmaService) {
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
                _atualizar();
            }).catch();
        }
    }

    function _listar() {
        TurmaService.listar().then(response => {
            $scope.turma = response;
        })
    }

    function _atualizar(id) {
        $scope.turmas.filter(turma => turma.id !== id)
    }
}