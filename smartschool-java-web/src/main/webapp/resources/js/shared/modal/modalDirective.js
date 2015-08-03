angular.module('SmartschoolApp').directive('modal', ['$sce', '$http', 'config', function ($sce, $http, config) {
    return {
        restrict: 'AE',
        scope: {
            onPrimaryButtonClick: '&onPrimaryButtonClickEvent',
            id: '=handler',
            modalDismissible: '=?',
            onPrimaryButtonClickAction: '@onPrimaryButtonClickAction',
            primaryButtonText: '@primaryButtonText',
            modalId: '@modalId',
            modalTitle: '@modalTitle',
            primaryButtonContext: '@primaryButtonContext',
            secondaryButtonText: '@secondaryButtonText',
            modalBody: '@modalBody',
            code: '@code',
            object: '@object'
            

        },
        templateUrl: config.url+'/resources/js/shared/modal/modalView.html',
        transclude: true,
        controller: function ($scope, $element, $attrs) {
        	$scope.handler = $attrs.handler;
            //$scope.handler = new Date().getTime(); //previous 'customModal'

        },
        compile: function(element, attrs){

                // console.log(attrs);

            return function(scope, element, attrs){

                scope.parentScope = scope.$parent;
                scope.rootScope = scope.$root;
                scope.modalBodyHtml = $sce.trustAsHtml(attrs['modalBodyHtml']);
                
                //scope.onPrimaryButtonClickAction = attrs['onPrimaryButtonClickAction'];

                    // $http.get(attrs['modalBody']).success (function(data){
                    //     scope.modalBody = $sce.trustAsHtml(data);
                    // });

            }
        },

    };
}]);
