
app = angular.module('SmartschoolApp').controller('HomeController', ['$scope', 'httpClient','constants', function ($scope, httpClient, constants) {
  $scope.appName = "Smartschool";
}]);

app.run(function(){
  console.log("loaded");
});
