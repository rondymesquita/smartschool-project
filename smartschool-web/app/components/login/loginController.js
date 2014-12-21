app = angular.module('SmartschoolApp', []);

app.controller('LoginController', ['$scope', 'httpClient', function ($scope, httpClient) {

	$scope.formData = {};

	this.submit = function() {

		var email = $scope.formData.email;
		var password = $scope.formData.password;


		httpClient.login(email, password)
		.then(function(data){
			console.log("Seconds Success! : ");
			console.log(data);
		},function(data){
			console.log("Seconds Error : "+data);
		});

	};

}]);
