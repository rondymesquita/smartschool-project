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
            $scope.handler = new Date().getTime(); //previous 'customModal'

        },
        link: function(scope, element, attrs){

            scope.parentScope = scope.$parent;

            console.log(attrs['onPrimaryButtonClickEvent'].indexOf("save"));
            if(attrs['onPrimaryButtonClickEvent'].indexOf("save") > -1);
                scope.showSaveOptions = true;

            scope.modalId = attrs['id'];
            scope.modalTitle = attrs['modalTitle'];
            console.log(attrs['modalDismissible']);

            if(attrs['modalDismissible'])
                scope.modalDismissible = attrs['modalDismissible'];

            if(scope.modalDismissible == 'true'){
                scope.dataBackdrop = "true";
                scope.dataKeyboard = "true";
                console.log("Fechavel! "+scope.modalId);
            }else{
                scope.dataBackdrop = "static";
                scope.dataKeyboard = "false";
                console.log("nao fechavel "+scope.modalId);
            }


            scope.primaryButtonText = attrs['primaryButtonText'];
            scope.secondaryButtonText = attrs['secondaryButtonText'];
            scope.modalBody = attrs['modalBody'];

            // $http.get(attrs['modalBody']).success (function(data){
            //     scope.modalBody = $sce.trustAsHtml(data);
            // });

        }
    };
}]);
