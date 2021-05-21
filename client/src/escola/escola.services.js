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
            });
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
            });
    }

    function _editar(aluno) {
        return $http.put(`${configParams.escolaUrl}/${aluno.id}`, aluno)
            .then(() => {
                window.alert("Escola editada com sucesso");
            });
    }

    function _deletar(id) {
        return $http.delete(`${configParams.escolaUrl}/${id}`)
            .then(() => {
                window.alert("Escola deletada com sucesso");
            });
    }
}