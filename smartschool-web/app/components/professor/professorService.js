/**
 * Created by rondymesquita on 3/11/15.
 */
angular.module('SmartschoolApp').service('ProfessorService', ['$http', 'constants', 'config', function($http, constants, config) {

    this.list = function() {

        url = config.url + config.professorsUri;
        console.log(url);

        return $http({
            url: url,
            method: 'GET'
        }).success(function(data, status, header, config) {
            console.log("Success");
//       console.log(data);
        }).error(function(data, status, header, config){
            console.log("Error");
//       console.log(data);
            // console.log(status);
            // console.log(header);
            // console.log(config);
        })['finally'](function() {

        });
    };

    this.save = function(formData) {

        url = config.url + config.professorsUri;
        console.log(url);

        return $http({
            url: url,
            method: 'POST',
            data:formData
        }).success(function(data, status, header, config) {

        }).error(function(data, status, header, config){

        })['finally'](function() {

        });
    };


    this.update = function(formData) {

        url = config.url + config.professorsUri;
        console.log(url);

        return $http({
            url: url,
            method: 'PUT',
            data:formData
        }).success(function(data, status, header, config) {

        }).error(function(data, status, header, config){

        })['finally'](function() {

        });
    };

    this.delete = function(code) {

        url = config.url + config.professorsUri + code;
        console.log(url);

        return $http({
            url: url,
            method: 'DELETE',
        }).success(function(data, status, header, config) {

        }).error(function(data, status, header, config){

        })['finally'](function() {

        });
    };


}]);
