angular.module('SmartschoolApp').service('httpClient', ['$http', 'constants', function($http, constants) {

	this.sayHello = function() {
		alert("Hello!");
	};


	this.login = function(username, password) {

		url = constants.url + constants.loginUri;

		return $http({
			url: constants.url + constants.loginUri,
			method: 'POST',
			data: JSON.stringify({"username":username,"password":password})
			// headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).success(function(data, status, header, config) {
			console.log(data);
			console.log("Success!");
		}).error(function(data, status, header, config){
			console.log(status);
		})['finally'](function() {
			console.log("done!");
		});



	};


	this.get = function(url, token) {

		url = constants.url + constants.professorsUri

		var defered = new $.Deferred();

		return $http({
				url: constants.url + constants.professorsUri,
				method: 'GET',
				//data: $.param($scope.formData),
				headers : { 'Content-Type': 'application/x-www-form-urlencoded' }
		}).success(function(data, status, header, config) {
				console.log(data);
				console.log("Success!");
				defered.resolve(data, status, header, config);
		}).error(function(data, status, header, config){
				console.log("Error: "+data.error.message + " : " + status);
				defered.reject(data);
		})['finally'](function() {
				// console.log("done!");
		});

	};

}]);
