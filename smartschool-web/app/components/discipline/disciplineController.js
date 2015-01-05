
angular.module('SmartschoolApp').controller('DisciplineController', ['$scope', 'DisciplineService','constants','toast',function ($scope, disciplineService, constants, toast) {

  $scope.controllerName = "dsadaslidjqwo";
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
              //$scope.responseDataModal = new ResponseData(constants.message.CONNECTION_ERROR, constants.status.DANGER);
              //toast.error(constants.message.CONNECTION_ERROR);
              toast.success(constants.message.REGISTRY_SAVED);
          else
              //$scope.responseDataModal = new ResponseData(constants.message.ERROR, constants.status.DANGER);
              toast.error(constants.message.ERROR);

          $scope.onTransaction = false;
          $scope.onResponse = true;

      });
  }

}]);
