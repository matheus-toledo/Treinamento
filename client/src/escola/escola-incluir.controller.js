angular.module("TreinamentoApp")
    .controller("EscolaIncluirController", EscolaIncluirController);


EscolaIncluirController.$inject = ['$scope', '$http', '$state', 'EscolaService', 'UsuarioService', 'configParams'];

function EscolaIncluirController($scope, $http, $state, EscolaService, UsuarioService, configParams) {

    $scope.incluir = incluir;
    $scope.escola = {};
    $scope.usuarios = [];
    _inicializar();

    function _inicializar() {
        $scope.escola.ativa=true;
        UsuarioService.listar()
            .then(response => {
                $scope.usuarios = response;
                $scope.escola.diretor=response[0];
            })
    }

    function incluir() {
        EscolaService.cadastrar($scope.escola).then(response => {
            $scope.escola = response;
            $state.go('escolaEditar', {id: $scope.escola.id});
        })
    }
}