// app = angular.module('SmartschoolApp', []);
angular.module('SmartschoolApp').controller('LoginController', ['$scope', 'httpClient','constants','$location', function ($scope, httpClient, constants, $location) {

	$scope.formData = {};
	$scope.onTransaction = false;
	$scope.onResponse = false;
	$scope.alertMessage = "";

	this.submit = function() {

		var email = $scope.formData.email;
		var password = $scope.formData.password;

		$scope.onTransaction = true;
		httpClient.login(email, password)
		.then(function(data, status){

				console.log("Token: "+data.data.Token);
				$.cookie(constants.authTokenKey, data.data.Token);
				console.log($.cookie());
				window.location = "../home/homeView.html";
				//$location.url()
				console.log("Login Successful");
				$scope.onTransaction = false;
				$scope.onResponse = true;

		},function(data){

				console.log("Login Error");
				console.log(data);
				$scope.onTransaction = false;
				$scope.alertMessage = data.data.message;
				$scope.onResponse = true;
		});

	};

}]);
