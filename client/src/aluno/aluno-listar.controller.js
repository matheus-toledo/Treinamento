angular.module("TreinamentoApp")
    .controller("AlunoListarControler", AlunoListarControler);

AlunoListarControler.$inject = ['$scope', '$http', '$state', 'AlunoService'];

function AlunoListarControler($scope, $http, $state, AlunoService) {
    $scope.editar = editar;
    $scope.deletar = deletar;

    _inicializar();

    ///////////////////////////////////

    function _inicializar() {
        _listar();
    }

    function _atualizar(id) {
        $scope.alunos = $scope.alunos.filter(aluno => aluno.id !== id);
    }

    function deletar(nome, id) {
        if (confirm(`Deseja deletar o aluno ${nome}?`)) {
            AlunoService.deletar(id).then(() => {
            }).then(() => {
                _atualizar(id);
            }).catch(()=>{});
        }
    }

    function editar(id) {
        $state.go('alunoEditar', {id: id});
    }

    function _listar() {
        AlunoService.listar().then(response => {
            $scope.alunos = response;
        })
    }

}