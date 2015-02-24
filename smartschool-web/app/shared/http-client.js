angular.module('SmartschoolApp').service('httpClient', ['$http', 'config', function($http, config) {

	this.sayHello = function() {
		alert("Hello!");
	};


	this.login = function(username, password) {

		url = config.url + config.loginUri;
		console.log(url);

		return $http({
			url: url,
			method: 'POST',
			data: JSON.stringify({"username":username,"password":password})

		}).success(function(data, status, header, config) {
			// console.log(data);
		}).error(function(data, status, header, config){
			// console.log(data);
		})['finally'](function() {

		});


	};

	this.changePassword = function(username, password, newPassword) {

		url = config.url + config.changePasswordUri;
		console.log(url);

		return $http({
			url: url,
			method: 'POST',
			data: JSON.stringify({"username":username,"password":password, "newPassword":newPassword})

		}).success(function(data, status, header, config) {
			// console.log(data);
		}).error(function(data, status, header, config){
			// console.log(data);
		})['finally'](function() {

		});


	};


}]);
