
angular
.module('SmartschoolApp')
.controller('ProfessorshipController', ['$scope','$rootScope', '$filter', 'ProfessorService','DisciplineService','ProfessorshipService','constants','$http', professorshipController]);

function professorshipController($scope, $rootScope, $filter, professorService, disciplineService, professorshipService, constants, $http) {


    $scope.onTransaction = false;
    $scope.onResponse = false;
    $scope.studentsToSave = [];
    
    $scope.searchStudents = function(student){
    	console.log(student);
    	$scope.students = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];
    }
    
    $scope.addStudent = function(student){
    	var index = $scope.studentsToSave.indexOf(student);
    	if (index == -1) {
    		$scope.studentsToSave.push(student);
    	}
    	console.log($scope.studentsToSave);
    }
    
    $scope.removeStudent = function(student){
    	var index = $scope.studentsToSave.indexOf(student);
    	if (index > -1) {
    		$scope.studentsToSave.splice(index, 1);
    	}
    	console.log($scope.studentsToSave);
    }



}
