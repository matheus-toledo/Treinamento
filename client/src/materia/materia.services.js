angular.module("TreinamentoApp")
    .service("MateriaService", MateriaService);

MateriaService.$inject = ["$http",'config'];

function MateriaService ($http, config){

    this.listar = _listar
    this.cadastrar = _cadastrar
    this.editar = _editar
    this.deletar = _deletar
    this.consultar = _consultar


    function _listar (){
        return $http.get(config.materiaUrl).then(response => {
            return response.data;
        }).catch(data => {
            window.alert('Erro ao listar matérias: ' + data);
        });
    }

    function _cadastrar(materia) {
        return $http.post(`${config.materiaUrl}`, materia)
            .then(response => {
                window.alert("Matéria cadastrada com sucesso");
                return response.data;
            })
            .catch(function (reject) {
                window.alert(`Erro no cadastro da matéria: ${reject.message}`);
            });
    }

    function _editar(materia) {
        return $http.put(`${config.materiaUrl}/${materia.id}`, materia)
            .then(() => {
                window.alert("Matéria editada com sucesso");
            })
            .catch(reject => {
                window.alert('Erro ao editar matéria' + reject.messageerror);
            });
    }

    function _deletar(id) {
        return $http.delete(`${config.materiaUrl}/${id}`)
            .then(() => {
                window.alert("Matéria deletada com sucesso");

            }).catch(reject => {
                window.alert("Erro ao tentar deletar matéria " + reject.message);
            });

    }

    function _consultar(id) {
        return $http.get(`${config.materiaUrl}/${id}`)
            .then(response =>{
                return response.data;
            });
    }



}