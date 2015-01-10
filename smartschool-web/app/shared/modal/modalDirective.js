angular.module('SmartschoolApp').directive('modal', ['$sce', '$http', function ($sce, $http) {
    return {
        restrict: 'AE',
        scope: {
            onPrimaryButtonClick: '&onPrimaryButtonClickEvent',
            handler: '=id',

        },
        templateUrl: '../../shared/modal/modalView.html',
        transclude: true,
        controller: function ($scope, $element, $attrs) {
            console.log("attrs");
            console.log($attrs);
            $scope.handler = new Date().getTime(); //previous 'customModal'

        },
        link: function(scope, element, attrs){

            scope.parentScope = scope.$parent;

            if(attrs['onPrimaryButtonClickEvent'].indexOf("save") > -1);
                scope.showSaveOptions = true;

            scope.modalId = attrs['id'];
            scope.modalTitle = attrs['modalTitle'];
            scope.primaryButtonText = attrs['primaryButtonText'];
            scope.modalBody = attrs['modalBody'];

            // $http.get(attrs['modalBody']).success (function(data){
            //     scope.modalBody = $sce.trustAsHtml(data);
            // });

        }
    };
}]);
