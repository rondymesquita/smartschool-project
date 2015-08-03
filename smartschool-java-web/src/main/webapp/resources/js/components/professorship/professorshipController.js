
angular
.module('SmartschoolApp')
.controller('ProfessorshipController', ['$scope','$rootScope', '$filter', 'ProfessorService','DisciplineService','StudentService','ProfessorshipService','constants','$http', professorshipController]);

function professorshipController($scope, $rootScope, $filter, professorService, disciplineService, studentService, professorshipService, constants, $http) {


    $scope.onTransaction = false;
    $scope.onResponse = false;
    $scope.onWarning = false;
    $scope.onSuccess = false;
    $scope.studentsToSave = [];
    $scope.showResponseData = false;
    
    $scope.searchStudents = function(){
  
    	$scope.onTransaction = true;
    	$scope.responseData = new ResponseData(constants.message.LOADING, constants.status.LOADING);
    	
    	studentService.list()
    	.then(function(data, status){
    		console.log(data.status);
    		$scope.students = data.data;
    		if($scope.students.length == 0){
                $scope.responseData = new ResponseData(constants.message.EMPTY, constants.status.WARNING);
                $scope.onWarning = true;
            }else{
            	$scope.onSuccess = true;	
            }
    		
    	},function(data){
    		console.log(data);

            if(data.status == 0)
                toast.error(constants.message.CONNECTION_ERROR);
            else
                toast.error(data.status + " " +data.statusText);
            
    	}).finally(function(){
    		$scope.onTransaction = false;
            $scope.onResponse = true;
    	});
    }
    
    $scope.searchStudentsByCodeOrName = function(search){
    	  
    	$scope.onTransaction = true;
    	$scope.responseData = new ResponseData(constants.message.LOADING, constants.status.LOADING);
    	
    	studentService.searchByCodeOrName(search)
    	.then(function(data, status){
    		console.log(data.data);
    		$scope.students = data.data;
    		if($scope.students.length == 0){
                $scope.responseData = new ResponseData(constants.message.EMPTY, constants.status.WARNING);
                $scope.onWarning = true;
            }else{
            	$scope.onSuccess = true;	
            }
    		
    	},function(data){
    		console.log(data);

            if(data.status == 0)
                toast.error(constants.message.CONNECTION_ERROR);
            else
                toast.error(data.status + " " +data.statusText);
            
    	}).finally(function(){
    		$scope.onTransaction = false;
            $scope.onResponse = true;
    	});
    }
    
    
    
    $scope.addStudent = function(student){
    	var index = $scope.studentsToSave.indexOf(student);
    	if (index == -1) {
    		$scope.studentsToSave.push(student);
    	}
    }
    
    $scope.removeStudent = function(student){
    	var index = $scope.studentsToSave.indexOf(student);
    	if (index > -1) {
    		$scope.studentsToSave.splice(index, 1);
    	}
    }



}
