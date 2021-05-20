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
        });
    }

    function _cadastrar(curso) {
        return $http.post(`${configParams.cursoUrl}`, curso)
            .then(response => {
                window.alert("Curso cadastrado com sucesso");
                return response.data;
            });
    }

    function _editar(curso) {
        return $http.put(`${configParams.cursoUrl}/${curso.id}`, curso)
            .then(() => {
                window.alert("Curso editado com sucesso");
            });
    }

    function _deletar(id) {
        return $http.delete(`${configParams.cursoUrl}/${id}`)
            .then(() => {
                window.alert("Curso deletado com sucesso");

            });

    }

    function _consultar(id) {
        return $http.get(`${configParams.cursoUrl}/${id}`)
            .then(response =>{
                return response.data;
            });
    }



}