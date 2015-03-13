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

    .when('/professors', {
        templateUrl : './../professor/professorView.html',
        controller  : 'ProfessorController'
    })


    .when('/login', {
        templateUrl : './../login/loginView.html',
        controller  : 'LoginController'
    })

    .when('/change-password', {
        templateUrl : './../auth/changePasswordView.html',
        controller  : 'ChangePasswordController'
    })


    .otherwise({
        templateUrl : './../dashboard/dashboardView.html'
    });

}
