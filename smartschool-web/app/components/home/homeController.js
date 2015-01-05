
app = angular.module('SmartschoolApp').controller('HomeController', ['$scope', 'httpClient','constants', function ($scope, httpClient, constants) {
  $scope.message = "Home Page";
}]);

app.run(function(){
  console.log("loaded");
});
