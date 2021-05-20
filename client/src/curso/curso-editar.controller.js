angular.module("TreinamentoApp")
    .controller("CursoEditarController", CursoEditarController);


CursoEditarController.$inject = ['$scope', '$http', '$state', '$stateParams', 'CursoService', 'UsuarioService', 'EscolaService', 'MateriaService'];

function CursoEditarController($scope, $http, $state, $stateParams, CursoService, UsuarioService, EscolaService, MateriaService) {

    $scope.editar = _editar;
    $scope.curso = {};
    $scope.usuarios = [];
    $scope.escolas = [];
    $scope.materias = [];
    $scope.cursoMateriasId = [];
    _inicializar();

    function _inicializar() {
        UsuarioService.listar().then(response => $scope.usuarios = response);

        EscolaService.listar()
            .then(response => {
                $scope.escolas = response;
            })

        MateriaService.listar()
            .then(response => {
                $scope.materias = response;
            })

        CursoService.consultar($stateParams.id)
            .then(response => {
                $scope.curso = response;
                $scope.cursoMateriasId = response.materias.map(elem => elem.id);
            }).catch(()=>{
                $state.go('cursoListar');
        })
    }

    function _editar() {
        _atualizarMaterias();
        CursoService.editar($scope.curso)
            .then(() => {
                $state.go('cursoListar');
            })
    }

    function _atualizarMaterias() {
        $scope.curso.materias = $scope.materias.filter(materia => {
            return $scope.cursoMateriasId.indexOf(materia.id) !== -1;
        })
    }

}