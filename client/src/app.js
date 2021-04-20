const app = angular.module("TreinamentoApp",['ui.router']);

app.config(['$httpProvider', function($httpProvider){

    $httpProvider.defaults.transformResponse.push((data)=>{
        convertDateStringsToDates(data);
        return data;
    });

    //Converte datas retornadas no formato JSON para datas no formato Javascript em cada request
    var regexIso8601 = /^([\+-]?\d{4}(?!\d{2}\b))((-?)((0[1-9]|1[0-2])(\3([12]\d|0[1-9]|3[01]))?|W([0-4]\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\d|[12]\d{2}|3([0-5]\d|6[1-6])))([T\s]((([01]\d|2[0-3])((:?)[0-5]\d)?|24\:?00)([\.,]\d+(?!:))?)?(\17[0-5]\d([\.,]\d+)?)?([zZ]|([\+-])([01]\d|2[0-3]):?([0-5]\d)?)?)?)?$/;
    function convertDateStringsToDates(input) {
        if (typeof input !== 'object') {
            return input;
        }
        for (var key in input) {
            if (!input.hasOwnProperty(key)) {
                continue;
            }
            var value = input[key];
            var match;
            // Check for string properties which look like dates.
            if (typeof value === 'string' && value.length > 10 && (match = value.match(regexIso8601))) {
                var milliseconds = Date.parse(match[0]);
                if (!isNaN(milliseconds)) {
                    input[key] = new Date(milliseconds);
                } else {
                    var date = new Date(match[0].replace(" ", "T"));
                    if (!isNaN(date)) {
                        input[key] = date;
                    }
                }
            } else if (typeof value === 'object') {
                // Recurse into object
                convertDateStringsToDates(value);
            }
        }
    }

}]);