
angular
.module('SmartschoolApp')
.controller('ProfessorshipController', ['$scope','$rootScope', '$filter', 'ProfessorshipService','DisciplineService','constants','toast','ngTableParams', '$http', professorshipController]);

function professorshipController($scope, $rootScope, $filter, professorshipService, disciplineService, constants,  toast, ngTableParams, $http) {

    $scope.title = "Cadeiras";
    $scope.disciplines = [];
    $scope.onTransaction = false;
    $scope.onResponse = false;
    $scope.saveAndNew = false;
    $scope.professorship;

    $scope.$watch('formModalDiscipline', function(formModal) {
        $rootScope.formModal = formModal;
    });

    $scope.selected = undefined;
    $scope.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];


    $scope.searchDisciplines = function(){

        $scope.onTransaction = true;
        $scope.responseData = new ResponseData(constants.message.LOADING, constants.status.LOADING);

        disciplineService.list()
            .then(function(data, status){

                $.each(data.data, function(index, value){
                    $scope.disciplines.push(value.name);
                });

                console.log($scope.disciplines);

                $scope.onTransaction = false;
                $scope.onResponse = true;

            },function(data){
                console.log(data);

                if(data.status == 0)
                    toast.error(constants.message.CONNECTION_ERROR);
                else
                    toast.error(data.status + " " +data.statusText);

                $scope.onTransaction = false;
                $scope.onResponse = true;
            });
    }

    $scope.searchDisciplines();



}
