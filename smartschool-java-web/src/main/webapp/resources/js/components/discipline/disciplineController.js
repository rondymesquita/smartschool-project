
angular
.module('SmartschoolApp')
.controller('DisciplineController', ['$scope','$rootScope', '$filter', 'DisciplineService','config','constants','toast', '$http', '$location', disciplineController]);

function disciplineController($scope, $rootScope, $filter, disciplineService,  config, constants,  toast, $http, $location) {

	$scope.deleteDiscipline = function(code){
        console.log(code);

        $scope.onTransaction = true;

        disciplineService.delete(code)
        .then(function(data){
            toast.success(constants.message.REGISTRY_DELETED);
            $("#disciplineDeleteModal").modal("hide");

            $scope.onTransaction = false;
            $scope.onResponse = true;
            
            //$location.url('/dashboard');
            window.location.replace(config.disciplinesUrl);
        },function(data){

            if(data.status == 0){
                toast.error(constants.message.CONNECTION_ERROR);
            }else{
                toast.error(data.status + " " +data.statusText);
            }

            $scope.onTransaction = false;
            $scope.onResponse = true;
        });


    }
  

}
