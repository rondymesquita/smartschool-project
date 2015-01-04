angular.module('SmartschoolApp').service('httpClient', ['$http', 'constants', function($http, constants) {

	this.sayHello = function() {
		alert("Hello!");
	};


	this.login = function(username, password) {

		url = constants.url + constants.loginUri;

		return $http({
			url: constants.url + constants.loginUri,
			method: 'POST',
			// headers : { 'Content-Type': 'application/x-www-form-urlencoded' },
			// headers : { 'Access-Control-Allow-Origin' :'true'},

			data: JSON.stringify({"username":username,"password":password})

		}).success(function(data, status, header, config) {
			// console.log(data);
		}).error(function(data, status, header, config){
			// console.log(data);
		})['finally'](function() {

		});


	};


}]);
