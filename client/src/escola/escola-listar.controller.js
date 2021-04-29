angular.module("TreinamentoApp")
    .controller("EscolaListarController", EscolaListarController);

EscolaListarController.$inject = ['$scope', '$http', '$state', 'EscolaService'];

function EscolaListarController($scope, $http, $state, EscolaService) {
    $scope.editar = editar;
    $scope.deletar = deletar;

    _inicializar();

    /////////////////////////////////////////////

    function _inicializar() {
        _listar();
    }

    function _atualizar(id) {
        $scope.escolas = $scope.escolas.filter(escola => escola.id !== id);
    }

    function deletar(nome, id) {
        if (confirm(`Deseja deletar a escola: ${nome}?`)) {
            EscolaService.deletar(id).then(() => {
                _atualizar(id);
            }).catch(()=>{

            });
        }
    }

    function editar(id) {
        $state.go('escolaEditar', {id: id});
    }

    function _listar() {
        EscolaService.listar().then(response => {
            $scope.escolas = response;
        })
    }

}