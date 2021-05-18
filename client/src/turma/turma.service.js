angular.module("TreinamentoApp")
    .service("TurmaService", TurmaService);

TurmaService.$inject = ['configParams', '$http'];

function TurmaService(configParams, $http) {
    this.listar = listar;
    this.obter = obter;
    this.editar = editar;
    this.deletar = deletar;
    this.incluir = incluir;
    this.gerarMatricula = gerarMatricula;

    function listar() {
        return $http.get(configParams.turmaUrl)
            .then(response => {
                return response.data;
            }).catch(err => {
                console.log(err.data.errorMessage);
            })
    }

    function obter(id) {
        return $http.get(`${configParams.turmaUrl}/${id}`)
            .then(response => {
                return response.data;
            }).catch(err => {
                console.log(err.data.errorMessage);
            })
    }

    function editar(turma) {
        return $http.put(`${configParams.turmaUrl}/${turma.id}`, turma)
            .then(() => {
                window.alert('Turma editada com sucesso');
            })
            .catch(err => {
                window.alert(err.data);
            })
    }

    function deletar(id) {
        return $http.delete(`${configParams.turmaUrl}/${id}`)
            .then(() => {
                window.alert('Turma deletada com sucesso');
            })
            .catch(err => {
                window.alert(err);
            });
    }

    function incluir(turma) {
        return $http.post(configParams.turmaUrl, turma)
            .then(response => {
                window.alert('Turma incluída com sucesso');
                return response.data;
            })
            .catch(err => {
                window.alert(err.data.message);
            });
    }

    function gerarMatricula(sigla,id) {
        return $http.get(`${configParams.turmaUrl}/sequencia/${sigla}?id=${id}`)
            .then(response => {
                return response.data;
            }).catch(err => {
                window.alert(err);
            });
    }


}