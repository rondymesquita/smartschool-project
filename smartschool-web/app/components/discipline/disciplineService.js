angular.module('SmartschoolApp').service('DisciplineService', ['$http', 'constants', function($http, constants) {

  this.list = function() {

    url = constants.url + constants.disciplinesUri;
    console.log(url);

    return $http({
      url: url,
      method: 'GET'
    }).success(function(data, status, header, config) {
      // console.log(data);
    }).error(function(data, status, header, config){
      // console.log(data);
    })['finally'](function() {

    });


  };

}]);
