/**
 * Created by rondymesquita on 3/11/15.
 */

angular
    .module('SmartschoolApp')
    .controller('ProfessorController', ['$scope','$rootScope', '$filter', 'ProfessorService','constants','toast','ngTableParams', '$http', professorController]);

function professorController($scope, $rootScope, $filter, professorService,  constants,  toast, ngTableParams, $http) {

    $scope.title = "Professores";
    $scope.professors = [];
    $scope.onTransaction = false;
    $scope.onResponse = false;
    $scope.professor;
    $scope.saveAndNew = false;

    $scope.professor={
        person : {
            role : 'ROLE_MANAGER'
        }
    }

    $scope.$watch('formModalProfessor', function(formModal) {
        console.log(formModal);
        $rootScope.formModal = formModal;
    });

    $scope.searchProfessors = function(){

        $scope.onTransaction = true;
        $scope.responseData = new ResponseData(constants.message.LOADING, constants.status.LOADING);

        professorService.list()
            .then(function(data, status){
                console.log(data);

                $scope.professors = data.data;


                if($scope.professors.length == 0){
                    $scope.responseData = new ResponseData(constants.message.EMPTY, constants.status.WARNING);
                }

                $scope.tableParams = new ngTableParams({
                    page: constants.table.FIRST_PAGE,            // show first page
                    count: constants.table.COUNTS_PER_PAGE,           // count per page
                    sorting: {
                        code: constants.table.SORTING
                    }
                }, {
                    total: $scope.professors.length, // length of data
                    getData: function($defer, params) {
                        var orderedData = params.sorting() ? $filter('orderBy')($scope.professors, params.orderBy()) : $scope.professors;
                        $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                    }
                });

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

    $scope.saveProfessor = function(){

        $scope.onTransaction = true;

        professorService.save($scope.professor)
            .then(function(data){

                toast.success(constants.message.REGISTRY_SAVED);
                console.log($scope.saveAndNew);
                if(!$scope.saveAndNew){
                    console.log("Hiding!");
                    $("#professorCreateModal").find(".modal").modal("hide");
                }


                $scope.professor = {};
                $scope.onTransaction = false;
                $scope.onResponse = true;

            },function(data){

                if(data.status == 0){
                    toast.error(constants.message.CONNECTION_ERROR);
                }else{
                    toast.error(data.status + " " +data.statusText);
                }

                $scope.onTransaction = false;
                $scope.onResponse = true;

            });
    }

    $scope.updateProfessor = function(professor){
        $scope.professor = professor;
        console.log(professor);

        $scope.onTransaction = true;

        professorService.update($scope.professor)
            .then(function(data){

                toast.success(constants.message.REGISTRY_UPDATED);
                $("#professorUpdateModal").find(".modal").modal("hide");

                $scope.onTransaction = false;
                $scope.onResponse = true;
            },function(data){
                if(data.status == 0){
                    toast.error(constants.message.CONNECTION_ERROR);
                }else{
                    toast.error(data.status + " " +data.statusText);
                }

                $scope.onTransaction = false;
                $scope.onResponse = true;
            });

    }

    $scope.deleteProfessor = function(professor){
        console.log(professor);

        $scope.onTransaction = true;

        professorService.delete(professor.code)
            .then(function(data){
                toast.success(constants.message.REGISTRY_DELETED);
                $("#professorDeleteModal").find(".modal").modal("hide");


                $scope.professor = {};
                $scope.onTransaction = false;
                $scope.onResponse = true;
                $scope.searchProfessors();
            },function(data){

                if(data.status == 0){
                    toast.error(constants.message.CONNECTION_ERROR);
                }else{
                    toast.error(data.status + " " +data.statusText);
                }

                $scope.onTransaction = false;
                $scope.onResponse = true;
            });


    }

}
