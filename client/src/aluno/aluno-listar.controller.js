angular.module("TreinamentoApp")
    .controller("AlunoListarControler", AlunoListarControler);

AlunoListarControler.$inject = ['$scope', '$http', '$state','config','AlunoService'];

function AlunoListarControler($scope, $http, $state,config, AlunoService) {
    $scope.editar = _editar;
    $scope.deletar = _deletar;

    _inicializar();

    /////////////////////////////////////////////

    function _inicializar() {
        _listar();
    }

    function _atualizar(id) {
        $scope.alunos = $scope.alunos.filter(aluno => aluno.id !== id);
    }

    function _deletar(nome,id) {
        if (confirm(`Deseja deletar o aluno ${nome}?`)) {
            AlunoService.deletar(id).then(()=>{
                _atualizar(id);
            })
        }
    }

    function _editar(id) {
        $state.go('alunoEditar', {id:id});
    }

    function _listar() {
        AlunoService.listar().then(response =>{
            $scope.alunos = response;
        })
    }

}