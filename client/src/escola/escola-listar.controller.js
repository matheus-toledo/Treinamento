angular.module("TreinamentoApp")
    .controller("EscolaListarController", EscolaListarController);

EscolaListarController.$inject = ['$scope', '$http', '$state','config','EscolaService'];

function EscolaListarController($scope, $http, $state,config, EscolaService) {
    $scope.editar = _editar;
    $scope.deletar = _deletar;

    _inicializar();

    /////////////////////////////////////////////

    function _inicializar() {
        _listar();
    }

    function _atualizar(id) {
        $scope.escolas = $scope.escolas.filter(escola => escola.id !== id);
    }

    function _deletar(nome,id) {
        if (confirm(`Deseja deletar a escola: ${nome}?`)) {
            EscolaService.deletar(id).then(()=>{
                _atualizar(id);
            })
        }
    }

    function _editar(id) {
        $state.go('escolaEditar', {id:id});
    }

    function _listar() {
        EscolaService.listar().then(response =>{
            $scope.escolas = response;
        })
    }

}