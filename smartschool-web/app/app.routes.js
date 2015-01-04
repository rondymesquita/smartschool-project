angular.module('SmartschoolApp', ['ngRoute']).config(function($routeProvider) {

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
    })

});
