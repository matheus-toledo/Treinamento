angular.module("TreinamentoApp")
    .service("CursoService", CursoService);

CursoService.$inject = ["$http",'configParams'];

function CursoService ($http, configParams){

    this.listar = _listar
    this.cadastrar = _cadastrar
    this.editar = _editar
    this.deletar = _deletar
    this.consultar = _consultar


    function _listar (){
        return $http.get(configParams.cursoUrl).then(response => {
            return response.data;
        }).catch(data => {
            window.alert('Erro ao listar cursos: ' + data);
        });
    }

    function _cadastrar(curso) {
        return $http.post(`${configParams.cursoUrl}`, curso)
            .then(response => {
                window.alert("Curso cadastrado com sucesso");
                return response.data;
            })
            .catch(function (reject) {
                window.alert(`Erro no cadastro do curso: ${reject.message}`);
            });
    }

    function _editar(curso) {
        return $http.put(`${configParams.cursoUrl}/${curso.id}`, curso)
            .then(() => {
                window.alert("Curso editado com sucesso");
            })
            .catch(reject => {
                window.alert('Erro ao editar curso' + reject.messageerror);
            });
    }

    function _deletar(id) {
        return $http.delete(`${configParams.cursoUrl}/${id}`)
            .then(() => {
                window.alert("Curso deletado com sucesso");

            }).catch(reject => {
                window.alert("Erro ao tentar deletar curso: " + reject.message);
            });

    }

    function _consultar(id) {
        return $http.get(`${configParams.cursoUrl}/${id}`)
            .then(response =>{
                return response.data;
            });
    }



}