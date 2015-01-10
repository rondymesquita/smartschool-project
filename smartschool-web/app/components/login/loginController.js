
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

			if(data.status == 0)
			$scope.responseDataLogin = new ResponseData(constants.message.CONNECTION_ERROR, constants.status.DANGER);
				else
			$scope.responseDataLogin = new ResponseData(constants.message.ERROR, constants.status.DANGER);

				$scope.onTransaction = false;
				$scope.onResponse = true;
		});

	};

}]);
