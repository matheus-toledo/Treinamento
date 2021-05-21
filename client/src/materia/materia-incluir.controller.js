angular.module("TreinamentoApp")
    .controller("MateriaIncluirController", MateriaIncluirController);


MateriaIncluirController.$inject = ['$scope', '$state','MateriaService'];

function MateriaIncluirController($scope, $state, MateriaService) {
    _inicializar();

    function _inicializar(){
        $scope.incluir = incluir;
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.descricaoPattern = /^[a-zA-Z](\s|\S|\d){0,2499}$/;
        $scope.materia = {};
        $scope.materia.professor = {};
    }

    function incluir() {
        MateriaService.cadastrar($scope.materia).then(response => {
            $state.go('materiaEditar', {id: response.id});
        }).catch(()=>{});
    }

}