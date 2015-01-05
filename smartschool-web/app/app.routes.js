angular.module('SmartschoolApp').config(config);

function config($routeProvider) {
    $routeProvider

    .when('/', {
        templateUrl : './../dashboard/dashboardView.html',
        controller  : 'DashboardController'
    })

    .when('/disciplines', {
        templateUrl : './../discipline/disciplineView.html',
        controller  : 'DisciplineController'
    })

    .otherwise({
        templateUrl : './../dashboard/dashboardView.html'
    });

}
