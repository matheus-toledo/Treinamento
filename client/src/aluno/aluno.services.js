angular.module("TreinamentoApp")
    .service("AlunoService", AlunoService);

AlunoService.$inject = ["$http", 'config'];

function AlunoService($http, config) {

    this.listar = _listar
    this.cadastrar = _cadastrar
    this.editar = _editar
    this.deletar = _deletar
    this.consultar = _consultar

    function _listar() {
        return $http.get(config.alunoUrl)
            .then(response => {
                return response.data;
            }).catch(error => {
                console.log("Erro ao obter lista de alunos:" +error);
            })
    }

    function _consultar(id) {
        return $http.get(`${config.alunoUrl}/${id}`)
            .then(response =>{
                return response.data;
            });
    }

    function _cadastrar(aluno) {
        return $http.post(`${config.alunoUrl}`, aluno)
            .then(response => {
                window.alert("Aluno cadastrado com sucesso");
                return response.data;
            })
            .catch(function (reject) {
                window.alert(`Erro no cadastro do aluno: ${reject.message}`);
            });
    }

    function _editar(aluno) {
        return $http.put(`${config.alunoUrl}/${aluno.id}`, aluno)
            .then(() => {
                window.alert("Aluno editado com sucesso");
            })
            .catch(reject => {
                window.alert('Erro ao editar aluno' + reject.messageerror);
            });
    }

    function _deletar(id) {
        return $http.delete(`${config.alunoUrl}/${id}`)
            .then(() => {
                window.alert("Aluno deletado com sucesso");

            }).catch(reject => {
                window.alert("Erro ao tentar deletar aluno " + reject.message);
            });

    }

}