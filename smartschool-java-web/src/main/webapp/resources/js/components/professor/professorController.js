/**
 * Created by rondymesquita on 3/11/15.
 */

angular
    .module('SmartschoolApp')
    .controller('ProfessorController', ['$scope','$rootScope', '$filter', 'ProfessorService','constants','toast', '$http', professorController]);

function professorController($scope, $rootScope, $filter, professorService,  constants,  toast, $http) {

	$scope.deleteProfessor = function(code){
        console.log(code);

        $scope.onTransaction = true;

        disciplineService.delete(code)
        .then(function(data){
            
            $("#disciplineDeleteModal").modal("hide");

            $scope.onTransaction = false;
            $scope.onResponse = true;
            
            window.location.replace(config.professorsUrl);
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
