
angular
.module('SmartschoolApp')
.controller('CourseController', ['$scope','$rootScope', '$filter', 'CourseService','config','constants','toast', '$http', '$location', courseController]);

function courseController($scope, $rootScope, $filter, courseService,  config, constants,  toast, $http, $location) {

	$scope.deleteCourse = function(code){
        console.log(code);

        $scope.onTransaction = true;

        courseService.delete(code)
        .then(function(data){
            toast.success(constants.message.REGISTRY_DELETED);
            $("#courseDeleteModal").modal("hide");

            $scope.onTransaction = false;
            $scope.onResponse = true;
            
            window.location.replace(config.coursesUrl);
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
