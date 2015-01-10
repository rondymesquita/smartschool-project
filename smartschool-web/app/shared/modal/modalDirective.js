angular.module('SmartschoolApp').directive('modal', ['$sce', '$http', function ($sce, $http) {
    return {
        restrict: 'AE',
        scope: {
            onPrimaryButtonClick: '&ngClickPrimaryButton',
            handler: '=id',
            
        },
        templateUrl: '../../shared/modal/modalView.html',
        transclude: true,
        controller: function ($scope) {
            $scope.handler = new Date().getTime(); //previous 'customModal'
        },
        link: function(scope, element, attrs){

            scope.modalTitle = attrs['modalTitle'];
            $http.get(attrs['modalBody']).success (function(data){
                scope.modalBody = $sce.trustAsHtml(data);
            });

        }
    };
}]);

// angular.module('SmartschoolApp').directive('modal', function () {
//     return {
//         restrict: 'A',
//         scope: {
//             title: '=modalTitle',
//             header: '=modalHeader',
//             body: '=modalBody',
//             footer: '=modalFooter',
//             callbackbuttonleft: '&ngClickLeftButton',
//             callbackbuttonright: '&ngClickRightButton',
//             handler: '=id'
//         },
//         templateUrl: '../../shared/modal/modalView.html',
//         transclude: true,
//         controller: function ($scope) {
//             $scope.handler = 'customModal';
//         },
//         link: function(scope, element, attrs){
//
//         }
//     };
// });
