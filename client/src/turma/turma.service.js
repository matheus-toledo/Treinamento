angular.module("TreinamentoApp")
    .service("TurmaService",TurmaService);

TurmaService.$inject = ['configParams','$http'];

function TurmaService(configParams, $http){
    this.listar = listar;
    this.obter = obter;
    this.editar = editar;
    this.deletar = deletar;

    function listar(){
        return $http.get(configParams.turmaUrl)
            .then(response =>{
                return response.data;
            }).catch(err =>{
                console.log(err.data.errorMessage);
            })
    }

    function obter(id){
        return $http.get(`${configParams.turmaUrl}/${id}`)
            .then(response =>{
                return response.data;
            }).catch(err =>{
                console.log(err.data.errorMessage);
            })
    }

    function editar(turma){
        return $http.put(`${configParams.turmaUrl}/${turma.id}`,turma)
            .then(()=>{
                console.log('Turma editada com sucesso');
            })
            .catch(err =>{
                console.log(err.data.errorMessage);
            })
    }

    function deletar(){
        return $http.get(configParams.turmaUrl)
            .then(()=>{
                console.log('Turma deletada com sucesso');
            })
            .catch(err =>{
                console.log(err.data.errorMessage);
            });
    }


}