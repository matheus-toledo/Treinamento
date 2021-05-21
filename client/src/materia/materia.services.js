angular.module("TreinamentoApp")
    .service("MateriaService", MateriaService);

MateriaService.$inject = ["$http",'configParams'];

function MateriaService ($http, configParams){
    this.listar = _listar
    this.cadastrar = _cadastrar
    this.editar = _editar
    this.deletar = _deletar
    this.consultar = _consultar

    function _listar (){
        return $http.get(configParams.materiaUrl).then(response => {
            return response.data;
        });
    }

    function _cadastrar(materia) {
        return $http.post(`${configParams.materiaUrl}`, materia)
            .then(response => {
                window.alert("Matéria cadastrada com sucesso");
                return response.data;
            });
    }

    function _editar(materia) {
        return $http.put(`${configParams.materiaUrl}/${materia.id}`, materia)
            .then(() => {
                window.alert("Matéria editada com sucesso");
            });
    }

    function _deletar(id) {
        return $http.delete(`${configParams.materiaUrl}/${id}`)
            .then(() => {
                window.alert("Matéria deletada com sucesso");
            });
    }

    function _consultar(id) {
        return $http.get(`${configParams.materiaUrl}/${id}`)
            .then(response =>{
                return response.data;
            });
    }
}