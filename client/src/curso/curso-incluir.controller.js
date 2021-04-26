angular.module("TreinamentoApp")
    .controller("CursoIncluirController", CursoIncluirController);


CursoIncluirController.$inject = ['$scope', '$http', '$state', 'CursoService', 'UsuarioService', 'MateriaService', 'EscolaService','configParams'];

function CursoIncluirController($scope, $http, $state, CursoService, UsuarioService, MateriaService, EscolaService,configParams) {

    $scope.incluir = _incluir;
    $scope.cursoDescricaoMaxLength = configParams.nomeMaxLength;
    $scope.cursoSiglaMaxLength = configParams.cursoSiglaMaxLength;
    $scope.cursoNomeMaxLength = configParams.cursoNomeMaxLength;
    $scope.curso = {};
    $scope.usuarios = [];
    $scope.escolas = [];
    $scope.materias = [];

    _inicializar()

    function _inicializar() {
        UsuarioService.listar()
            .then(response => {
                $scope.usuarios = response;
            });

        EscolaService.listar()
            .then(response => {
                $scope.escolas = response;
            })

        MateriaService.listar()
            .then(response => {
                $scope.materias = response;
            })
    }

    function _incluir() {
        CursoService.cadastrar($scope.curso).then(response => {
            $scope.curso = response;
            $state.go('cursoEditar', {id: $scope.curso.id});
        });
    }

}