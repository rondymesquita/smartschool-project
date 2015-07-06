/**
 * Created by rondymesquita on 3/11/15.
 */

angular
    .module('SmartschoolApp')
    .controller('StudentController', ['$scope','$rootScope', '$filter', 'ProfessorService','config','constants','toast', '$http', studentController]);

function studentController($scope, $rootScope, $filter, professorService,  config, constants,  toast, $http) {

	console.log(config);
	
	$scope.deleteStudent = function(code){
        console.log(code);

        $scope.onTransaction = true;

        studentService.delete(code)
        .then(function(data){
            
            $("#studentDeleteModal").modal("hide");

            $scope.onTransaction = false;
            $scope.onResponse = true;
            
            window.location.replace(config.professorsUrl);
        },function(data){

            if(data.status == 0){
                toast.error(constants.message.CONNECTION_ERROR);
            }else{
                toast.error(data.status + " " + data.statusText);
            }

            $scope.onTransaction = false;
            $scope.onResponse = true;
        });

    }

}
