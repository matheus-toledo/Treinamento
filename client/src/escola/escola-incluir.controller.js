angular.module("TreinamentoApp")
    .controller("EscolaIncluirController", EscolaIncluirController);


EscolaIncluirController.$inject = ['$scope', '$http', '$state', 'EscolaService', 'UsuarioService', 'config'];

function EscolaIncluirController($scope, $http, $state, EscolaService, UsuarioService, config) {

    $scope.incluir = _incluir;
    $scope.nomeMaxLength = config.nomeMaxLength;
    $scope.descricaoMaxLength = config.descricaoMaxLength;
    $scope.escola = {};
    $scope.usuarios = [];
    _inicializar();

    function _inicializar() {
        $scope.escola.ativa=true;
        UsuarioService.listar()
            .then(response => {
                $scope.usuarios = response;
            })
    }

    function _incluir() {
        EscolaService.cadastrar($scope.escola).then(response => {
            $scope.escola = response;
            $state.go('escolaEditar', {id: $scope.escola.id});
        })
    }
}