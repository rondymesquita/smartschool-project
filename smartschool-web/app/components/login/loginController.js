
angular.module('SmartschoolApp').controller('LoginController', ['$scope','$rootScope', 'httpClient','constants','$location', function ($scope, $rootScope, httpClient, constants, $location) {

	$scope.formData = {};
	$scope.onTransaction = false;
	$scope.onResponse = false;
	$scope.alertMessage = "";
	$scope.username = ";"

	$scope.login = function() {

		var email = $scope.formData.email;
		var password = $scope.formData.password;
		console.log("Eita");
		$scope.onTransaction = true;
		httpClient.login(email, password)
		.then(function(data, status){

				console.log("Token: "+data.data.authToken);
				$.cookie(constants.authTokenKey, data.data.authToken);
				$.cookie(constants.usernameKey, data.data.username);
				$.cookie(constants.personType, data.data.personType);

				console.log("Login Successful");
				$scope.onTransaction = false;
				$scope.onResponse = true;

				$location.url('/dashboard');


		},function(data){
			console.log(data);
			if(data.status == 0)
				$scope.responseDataLogin = new ResponseData(constants.message.CONNECTION_ERROR, constants.status.DANGER);
			else
				$scope.responseDataLogin = new ResponseData(data.data.status + " - " + data.data.message, constants.status.DANGER);

				$scope.onTransaction = false;
				$scope.onResponse = true;
		});

	};


}]);
