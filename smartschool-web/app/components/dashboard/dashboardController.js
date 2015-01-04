
angular.module('SmartschoolApp').controller('DashboardController', ['$scope', 'httpClient','constants', '$http', function ($scope, httpClient, constants, $http) {
  $scope.message = "DashboardController";
  $scope.shortcuts = [];

  //loading shortcuts
  $http.get('../../shared/shortcuts.json').success (function(data){
    $scope.shortcuts = data;
  });

}]);
