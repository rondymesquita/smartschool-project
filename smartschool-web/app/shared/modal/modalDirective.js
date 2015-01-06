angular.module('SmartschoolApp').directive('smModal',function(){
    return {
        restrict: 'A',
        scope:{
            title:'=modalTitle',
            header: '=modalHeader',
            body: '=modalBody',
            footer: '=modalFooter',
            callbackbuttonleft: '&ngClickLeftButton',
            callbackbuttonright: '&ngClick',
            handler: '=lolo'
        },
        controller: function($scope){
            $scope.defaultButtonHandler = function(){

            };

            $scope.primaryButtonHandler = function(){

            };
        }
    };
});
