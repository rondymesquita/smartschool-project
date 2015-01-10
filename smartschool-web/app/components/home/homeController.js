
angular
    .module('SmartschoolApp')
    .controller('HomeController', ['$scope', '$rootScope','$routeParams', 'httpClient','constants', function ($scope, $rootScope, $routeParams, httpClient, constants) {

        $scope.appName = "Smartschool";
        $scope.username = $rootScope.username;
        console.log($.cookie());

        // $scope.username = "Smartschool";
}]);
