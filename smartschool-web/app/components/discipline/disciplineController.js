
angular.module('SmartschoolApp').controller('DisciplineController', ['$scope', 'DisciplineService','constants', function ($scope, DisciplineService, constants) {
  $scope.title = "Disciplinas";
  $scope.disciplines = [];

  DisciplineService.list()
  .then(function(data, status){
    $scope.disciplines = data.data;
  },function(data){
    console.log("Erro ao carregar disciplinas");
  });

}]);
