angular.module("TreinamentoApp")
    .factory('globalInterceptor', globalInterceptor);

globalInterceptor.$inject = ['$q'];

function globalInterceptor($q) {
    return {
        request: function (config) {
            return config;
        },

        responseError: function httpError(error) {
            window.alert(error.data.errorMessage);
            return $q.reject(error);
        }
    };
}