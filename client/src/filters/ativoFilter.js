angular.module('TreinamentoApp')
    .filter('ativo', Ativo)

function Ativo(){
    return function (isAtivo){
        return isAtivo === true ? "Sim" : "Não";
    }
}