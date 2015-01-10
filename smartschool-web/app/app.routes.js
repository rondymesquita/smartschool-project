angular.module('SmartschoolApp').config(config);

function config($routeProvider) {
    $routeProvider

    .when('/dashboard', {
        templateUrl : './../dashboard/dashboardView.html',
        controller  : 'DashboardController'
    })

    .when('/disciplines', {
        templateUrl : './../discipline/disciplineView.html',
        controller  : 'DisciplineController'
    })

    .when('/login', {
        templateUrl : './../login/loginView.html',
        controller  : 'LoginController'
    })

    .otherwise({
        templateUrl : './../dashboard/dashboardView.html'
    });

}
