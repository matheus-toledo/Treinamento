angular.module("TreinamentoApp")
    .service("EscolaService", EscolaService);

EscolaService.$inject = ["$http", 'configParams'];

function EscolaService($http, configParams) {

    this.listar = _listar
    this.cadastrar = _cadastrar
    this.editar = _editar
    this.deletar = _deletar
    this.consultar = _consultar

    function _listar() {
        return $http.get(configParams.escolaUrl)
            .then(response => {
                return response.data;
            }).catch(error => {
                console.log("Erro ao obter lista de escolas:" +error);
            })
    }

    function _consultar(id) {
        return $http.get(`${configParams.escolaUrl}/${id}`)
            .then(response =>{
                return response.data;
            });
    }

    function _cadastrar(aluno) {
        return $http.post(`${configParams.escolaUrl}`, aluno)
            .then(response => {
                window.alert("Escola cadastrada com sucesso");
                return response.data;
            })
            .catch(function (reject) {
                window.alert(`Erro no cadastro da escola: ${reject.message}`);
            });
    }

    function _editar(aluno) {
        return $http.put(`${configParams.escolaUrl}/${aluno.id}`, aluno)
            .then(() => {
                window.alert("Escola editada com sucesso");
            })
            .catch(reject => {
                window.alert('Erro ao editar escola' + reject.messageerror);
            });
    }

    function _deletar(id) {
        return $http.delete(`${configParams.escolaUrl}/${id}`)
            .then(() => {
                window.alert("Escola deletada com sucesso");

            }).catch(reject => {
                window.alert(reject.data.errorMessage);
                throw new Error();
            });

    }

}