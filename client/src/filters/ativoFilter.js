angular.module('TreinamentoApp')
    .filter('ativo', Ativo)

function Ativo(){
    return function (isAtivo){
        if (isAtivo===true){
            return "Sim";
        } else{
            return "Não";
        }
    }
}