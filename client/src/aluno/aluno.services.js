angular.module("TreinamentoApp")
    .service("AlunoService", AlunoService);

AlunoService.$inject = ["$http", 'configParams'];

function AlunoService($http, configParams) {

    this.listar = _listar
    this.cadastrar = _cadastrar
    this.editar = _editar
    this.deletar = _deletar
    this.consultar = _consultar

    function _listar() {
        return $http.get(configParams.alunoUrl)
            .then(response => {
                return response.data;
            })
    }

    function _consultar(id) {
        return $http.get(`${configParams.alunoUrl}/${id}`)
            .then(response => {
                return response.data;
            })
    }

    function _cadastrar(aluno) {
        return $http.post(`${configParams.alunoUrl}`, aluno)
            .then(response => {
                window.alert("Aluno cadastrado com sucesso");
                return response.data;
            })
    }

    function _editar(aluno) {
        return $http.put(`${configParams.alunoUrl}/${aluno.id}`, aluno)
            .then(() => {
                window.alert("Aluno editado com sucesso");
            })
    }

    function _deletar(id) {
        return $http.delete(`${configParams.alunoUrl}/${id}`)
            .then(() => {
                window.alert("Aluno deletado com sucesso");
            });
    }

}