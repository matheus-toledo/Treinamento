angular.module("TreinamentoApp")
    .controller("CursoEditarController", CursoEditarController);


CursoEditarController.$inject = ['$scope', '$http', '$state', '$stateParams', 'CursoService', 'UsuarioService', 'EscolaService', 'MateriaService','configParams'];

function CursoEditarController($scope, $http, $state, $stateParams, CursoService, UsuarioService, EscolaService, MateriaService,configParams) {

    $scope.editar = _editar;
    $scope.curso = {};
    $scope.usuarios = [];
    $scope.escolas = [];
    $scope.materias =[];
    $scope.cursoDescricaoMaxLength = configParams.nomeMaxLength;
    $scope.cursoSiglaMaxLength = configParams.cursoSiglaMaxLength;
    $scope.cursoNomeMaxLength = configParams.cursoNomeMaxLength;

    _inicializar();

    function _inicializar() {
        UsuarioService.listar().then(response => $scope.usuarios = response);

        EscolaService.listar()
            .then(response =>{
                $scope.escolas = response;
            })

        MateriaService.listar()
            .then(response =>{
                $scope.materias=response;
            })

        CursoService.consultar($stateParams.id)
            .then(response => {
                $scope.curso = response;
            })
    }

    function _editar() {
        CursoService.editar($scope.curso)
            .then(() => {
                $state.go('cursoListar');
            })
    }
}