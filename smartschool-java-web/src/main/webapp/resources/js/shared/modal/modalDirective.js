angular.module('SmartschoolApp').directive('modal', ['$sce', '$http', 'config', function ($sce, $http, config) {
    return {
        restrict: 'AE',
        scope: {
            onPrimaryButtonClick: '&onPrimaryButtonClickEvent',
            id: '=handler',
            modalDismissible: '=?'

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

                //console.log(scope);


                if(attrs['onPrimaryButtonClickEvent'].indexOf("save") > -1);
                    scope.showSaveOptions = true;

                scope.modalId = attrs['id'];
                scope.modalTitle = attrs['modalTitle'];

                scope.primaryButtonText = attrs['primaryButtonText'];
                scope.primaryButtonContext = attrs['primaryButtonContext'];
                scope.secondaryButtonText = attrs['secondaryButtonText'];
                scope.modalBody = attrs['modalBody'];
                scope.modalBodyHtml = $sce.trustAsHtml(attrs['modalBodyHtml']);

                    // $http.get(attrs['modalBody']).success (function(data){
                    //     scope.modalBody = $sce.trustAsHtml(data);
                    // });

            }
        },

    };
}]);
