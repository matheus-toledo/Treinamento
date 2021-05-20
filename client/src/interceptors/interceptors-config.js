angular.module("TreinamentoApp")
    .config(function ($httpProvider) {
        $httpProvider.interceptors.push('globalInterceptor')
    })