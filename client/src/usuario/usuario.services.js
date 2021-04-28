angular.module("TreinamentoApp")
    .service("UsuarioService", UsuarioService);

UsuarioService.$inject = ["$http",'configParams'];

function UsuarioService ($http, configParams){

    this.listar = _listar
    this.cadastrar = _cadastrar
    this.editar = _editar
    this.deletar = _deletar
    this.consultar = _consultar


    function _listar (){
        return $http.get(configParams.usuarioUrl).then(response => {
          return response.data;
        }).catch(data => {
            window.alert('Erro ao listar usuários: ' + data);
        });
    }

    function _cadastrar(usuario) {
        return $http.post(`${configParams.usuarioUrl}`, usuario)
            .then(response => {
                window.alert("Usuário cadastrado com sucesso");
                return response.data;
            })
            .catch(function (reject) {
                window.alert(`Erro no cadastro do usuário: ${reject.message}`);
            });
    }

    function _editar(usuario) {
        return $http.put(`${configParams.usuarioUrl}/${usuario.id}`, usuario)
            .then(() => {
                window.alert("Usuário editado com sucesso");
            })
            .catch(reject => {
                window.alert('Erro ao editar usuário' + reject.messageerror);
            });
    }

    function _deletar(id) {
        return $http.delete(`${configParams.usuarioUrl}/${id}`)
            .then(() => {
                window.alert("Usuário deletado com sucesso");

            }).catch(reject => {
                window.alert("Erro ao tentar deletar usuário " + reject.message);
            });

    }

    function _consultar(id) {
        return $http.get(`${configParams.usuarioUrl}/${id}`)
            .then(response =>{
                return response.data;
            });
    }



}