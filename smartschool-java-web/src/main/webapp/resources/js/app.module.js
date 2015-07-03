angular
.module('SmartschoolApp', [])
.run(function(){
    console.log("Angular Loaded!");
    console.log("===============");
}).config(function($httpProvider){
    $httpProvider.interceptors.push(interceptor);
});


var interceptor = function ($q, $location, constants) {
    return {
        request: function (request) {
//
//            if($.cookie(constants.usernameKey) == undefined){
//                // console.log("Not logged");
//                //$("#authRequiredModal").find(".modal").modal("show");
//
//            }else{
//                //console.log($.cookie(constants.authTokenKey));
//                request.headers[constants.authTokenKey] = $.cookie(constants.authTokenKey);
//
//
//            }

            return request;
        },

        response: function (result) {
          
            return result;
        },

        responseError: function (rejection) {
          
            console.log(rejection);
            return $q.reject(rejection);
        }
    }
};


function ResponseData(message, status){
    this.message = message;
    this.status = status;
}

angular.module('SmartschoolApp').directive('ngRedirectOnEnter', function () {
    return function (scope, element, attrs) {
    	
        element.bind("keyup", function (event) {
        	
            if(event.which === 13) {
            	
            	console.log(attrs);
            	window.location.replace(attrs.ngRedirectOnEnter);
            	
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});
