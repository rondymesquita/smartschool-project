angular.module('SmartschoolApp').service('SemesterService', ['$http', 'constants', 'config', function($http, constants, config) {

	this.delete = function(code) {

	      url = config.url + config.semestersUri + code;
	      console.log(url);

	      return $http({
	          url: url,
	          method: 'DELETE',
	      }).success(function(data, status, header, config) {

	      }).error(function(data, status, header, config){

	      })['finally'](function() {

	      });
	    };

}]);
