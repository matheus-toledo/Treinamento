angular.module("TreinamentoApp")
    .controller("CursoIncluirController", CursoIncluirController);


CursoIncluirController.$inject = ['$scope', '$state', 'CursoService'];

function CursoIncluirController($scope, $state, CursoService) {
    _inicializar()

    function _inicializar() {
        $scope.incluir = _incluir;
        $scope.nomePattern = /^[a-zA-Z](\s|\S|\d){0,254}$/;
        $scope.descricaoPattern = /^[a-zA-Z](\s|\S|\d){0,2499}$/;
        $scope.siglaPattern = /^[A-Z]{1,5}$/;
        $scope.curso = {};
        $scope.curso.coordenador={};
        $scope.curso.escola={};
        $scope.curso.materiasIds=[];
    }

    function _incluir() {
        CursoService.cadastrar($scope.curso).then(response => {
            $scope.curso = response;
            $state.go('cursoEditar', {id: $scope.curso.id});
        });
    }

}