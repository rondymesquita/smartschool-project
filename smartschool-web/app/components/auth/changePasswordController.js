
angular.module('SmartschoolApp').controller('ChangePasswordController', ['$scope','$rootScope', 'httpClient','constants','$location', function ($scope, $rootScope, httpClient, constants, $location) {

	$scope.formData = {};
	$scope.onTransaction = false;
	$scope.onResponse = false;
	$scope.alertMessage = "";
	$scope.title = "Mudar senha";

	$scope.changePassword = function() {

		var email = $scope.formData.email;
		var password = $scope.formData.password;
		var newPassword = $scope.formData.newPassword;


		$scope.onTransaction = true;

		httpClient.changePassword(email, password, newPassword)
		.then(function(data, status){


				$scope.onTransaction = false;
				$scope.onResponse = true;

				$location.url('/dashboard');


		},function(data){

			if(data.status == 0)
				$scope.responseDataLogin = new ResponseData(constants.message.CONNECTION_ERROR, constants.status.DANGER);
			else
				$scope.responseDataLogin = new ResponseData(data.status + " - " + data.statusText, constants.status.DANGER);

				$scope.onTransaction = false;
				$scope.onResponse = true;
		});

	};


}]);
