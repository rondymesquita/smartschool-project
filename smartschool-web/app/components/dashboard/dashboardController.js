
angular.module('SmartschoolApp').controller('DashboardController', ['$scope', 'httpClient','constants', '$http', function ($scope, httpClient, constants, $http) {
    $scope.title = "Dashboard";
    $scope.shortcuts = [];

      //loading shortcuts
      $http.get('../../shared/shortcuts.json').success (function(data){
        $scope.shortcuts = data;
      });

}]);
