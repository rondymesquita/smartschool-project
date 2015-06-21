angular.module('SmartschoolApp').directive('typehead', ['$sce', '$http', function ($sce, $http) {
    return {
        restrict: 'AE',
        templateUrl: '../../shared/typehead/typeheadView.html',
        transclude: true,
        controller: function ($scope, $element, $attrs) {
            //$scope.handler = new Date().getTime(); //previous 'customModal'

        },
        scope: {
            model: '='
        },
        compile: function(element, attrs){

            return function(scope, element, attrs){

                console.log(attrs);

                scope.parentScope = scope.$parent;
                scope.rootScope = scope.$root;

                scope.handler = attrs['handler'];
                scope.name = attrs['name'];
                scope.type = attrs['type'];
                scope.placeholder = attrs['placeholder'];

                // constructs the suggestion engine
                var disciplines = new Bloodhound({
                    datumTokenizer: Bloodhound.tokenizers.whitespace,
                    queryTokenizer: Bloodhound.tokenizers.whitespace,
                    local: ["prog", "tecnologias web", "programacao 2", "banco de dados", "seguranca", "redes"]
                });

                $('#'+scope.handler).typeahead({
                    hint: true,
                    highlight: true,
                    minLength: 1
                },
                {
                    name: 'disciplines',
                    source: disciplines,

                });


            }
        }

    };
}]);
