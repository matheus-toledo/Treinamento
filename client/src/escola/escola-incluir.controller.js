angular.module("TreinamentoApp")
    .controller("EscolaIncluirController", EscolaIncluirController);


EscolaIncluirController.$inject = ['$scope', '$http', '$state', 'EscolaService', 'UsuarioService', 'configParams'];

function EscolaIncluirController($scope, $http, $state, EscolaService, UsuarioService, configParams) {

    _inicializar();
    function _inicializar() {
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.descricaoPattern = /^[a-zA-Z](\s|\S|\d){0,299}$/;
        $scope.incluir = incluir;
        $scope.escola = {};
        $scope.escola.diretor={};

    }

    function incluir() {
        EscolaService.cadastrar($scope.escola).then(response => {
            $scope.escola = response;
            $state.go('escolaEditar', {id: $scope.escola.id});
        })
    }
}