
angular
.module('SmartschoolApp')
.controller('SemesterController', ['$scope','$rootScope', '$filter', 'SemesterService','config','constants','toast', '$http', '$location', semesterController]);

function semesterController($scope, $rootScope, $filter, semesterService,  config, constants,  toast, $http, $location) {

	$scope.deleteSemester = function(code){
        console.log(code);

        $scope.onTransaction = true;

        semesterService.delete(code)
        .then(function(data){
            toast.success(constants.message.REGISTRY_DELETED);
            $("#semesterDeleteModal").modal("hide");

            $scope.onTransaction = false;
            $scope.onResponse = true;
            
            window.location.replace(config.semestersUrl);
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
