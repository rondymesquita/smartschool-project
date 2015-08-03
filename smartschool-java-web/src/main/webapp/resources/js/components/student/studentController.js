/**
 * Created by rondymesquita on 3/11/15.
 */

angular
    .module('SmartschoolApp')
    .controller('StudentController', ['$scope','$rootScope', '$filter', 'StudentService','config','constants','toast', '$http', studentController]);

function studentController($scope, $rootScope, $filter, studentService,  config, constants,  toast, $http) {
	
}
