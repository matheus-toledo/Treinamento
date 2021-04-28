angular.module("TreinamentoApp")
    .controller("UsuarioListarController", UsuarioListarController);

UsuarioListarController.$inject = ['$scope', '$http', '$state', 'UsuarioService'];

function UsuarioListarController($scope, $http, $state, UsuarioService) {
    $scope.editar = _editar;
    $scope.deletar = _deletar;

    _inicializar();

    function _inicializar() {
        _listar();
    }

    function _atualizar(id) {
        $scope.usuarios = $scope.usuarios.filter(usuario => usuario.id !== id);
    }

    function _deletar(nome, id) {
        if (confirm(`Deseja deletar o usuário ${nome}?`)) {
            UsuarioService.deletar(id).then(() => {
                _atualizar(id);
            });
        }
    }

    function _editar(id) {
        $state.go('usuarioEditar', {id:id});
    }

    function _listar() {
        UsuarioService.listar()
            .then(response => {
                $scope.usuarios = response;
            })

    }

}