angular.module('SmartschoolApp').service('DisciplineService', ['$http', 'constants', 'config', function($http, constants, config) {

	this.delete = function(code) {

	      url = config.url + config.disciplinesUri + code;
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
