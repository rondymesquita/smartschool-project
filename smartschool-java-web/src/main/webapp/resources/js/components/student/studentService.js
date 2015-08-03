/**
 * Created by rondymesquita on 3/11/15.
 */
angular.module('SmartschoolApp').service('StudentService', ['$http', 'constants', 'config', function($http, constants, config) {

		this.list = function(code) {

	      url = config.studentsApiUrl;
	      console.log(url);

	      return $http({
	          url: url,
	          method: 'GET',
	      }).success(function(data, status, header, config) {

	      }).error(function(data, status, header, config){

	      })['finally'](function() {

	      });
	    };
	    
this.searchByCodeOrName = function(search) {
	
	if(search === undefined)
		url = config.studentsApiUrl;
	else
		url = config.studentsApiUrl + search;
	
	console.log(url);
	
	return $http({
	    url: url,
	    method: 'GET',
	}).success(function(data, status, header, config) {
	}).error(function(data, status, header, config){
		
	})['finally'](function() {
		
	});
	
};
	    

}]);
