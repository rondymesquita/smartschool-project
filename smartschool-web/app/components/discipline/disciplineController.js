
angular.module('SmartschoolApp').controller('DisciplineController', ['$scope', 'DisciplineService','constants', function ($scope, disciplineService, constants) {


  $scope.title = "Disciplinas";
  $scope.disciplines = [];
  $scope.onTransaction = false;
  $scope.onResponse = false;
  $scope.formData;


  $scope.searchDisciplines = function(){

      $scope.onTransaction = true;
      $scope.responseData = new ResponseData(constants.message.LOADING, constants.status.LOADING);

      disciplineService.list()
      .then(function(data, status){

        $scope.disciplines = data.data;

        if(data.data.length == 0){
          $scope.responseData = new ResponseData(constants.message.EMPTY, constants.status.WARNING);
        }

        $scope.onTransaction = false;
        $scope.onResponse = true;

      },function(data){
        console.log(data.status)

        if(data.status == 0)
            $scope.responseData = new ResponseData(constants.message.CONNECTION_ERROR, constants.status.DANGER);
        else
            $scope.responseData = new ResponseData(constants.message.ERROR, constants.status.DANGER);

        $scope.onTransaction = false;
        $scope.onResponse = true;
      });
  }

  $scope.saveDiscipline = function(){

      $scope.onTransaction = true;

      disciplineService.save()
      .then(function(data){

          $scope.onTransaction = false;
          $scope.onResponse = true;

      },function(data){

          if(data.status == 0)
              $scope.responseDataModal = new ResponseData(constants.message.CONNECTION_ERROR, constants.status.DANGER);
          else
              $scope.responseDataModal = new ResponseData(constants.message.ERROR, constants.status.DANGER);

          $scope.onTransaction = false;
          $scope.onResponse = true;

      });
  }

}]);
