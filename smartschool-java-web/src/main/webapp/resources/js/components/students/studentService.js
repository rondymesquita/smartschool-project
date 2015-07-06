/**
 * Created by rondymesquita on 3/11/15.
 */
angular.module('SmartschoolApp').service('ProfessorService', ['$http', 'constants', 'config', function($http, constants, config) {

		this.delete = function(code) {

	      url = config.professorsUrl + code;
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
