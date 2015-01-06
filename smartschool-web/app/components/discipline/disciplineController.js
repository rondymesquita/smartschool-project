
angular
.module('SmartschoolApp')
.controller('DisciplineController', ['$scope', '$filter', 'DisciplineService','constants','toast','ngTableParams', disciplineController]);

function disciplineController($scope, $filter, disciplineService,  constants,  toast, ngTableParams) {

    $scope.title = "Disciplinas";
    $scope.disciplines = [];
    $scope.onTransaction = false;
    $scope.onResponse = false;
    $scope.formData;
    $scope.saveAndNew;


    $scope.searchDisciplines = function(){

        $scope.onTransaction = true;
        $scope.responseData = new ResponseData(constants.message.LOADING, constants.status.LOADING);

        disciplineService.list()
        .then(function(data, status){

            $scope.disciplines = data.data;

            if($scope.disciplines.length == 0){
                $scope.responseData = new ResponseData(constants.message.EMPTY, constants.status.WARNING);
            }

            $scope.tableParams = new ngTableParams({
                page: constants.table.FIRST_PAGE,            // show first page
                count: constants.table.COUNTS_PER_PAGE,           // count per page
                sorting: {
                    code: constants.table.SORTING
                }
            }, {
                total: $scope.disciplines.length, // length of data
                getData: function($defer, params) {
                    var orderedData = params.sorting() ? $filter('orderBy')($scope.disciplines, params.orderBy()) : $scope.disciplines;
                    $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            });

            $scope.onTransaction = false;
            $scope.onResponse = true;

        },function(data){
            console.log(data.status)

            if(data.status == 0)
                toast.error(constants.message.CONNECTION_ERROR);
            else
                toast.error(data.status + " " +data.statusText);

            $scope.onTransaction = false;
            $scope.onResponse = true;
        });
    }

    $scope.saveDiscipline = function(){

        $scope.onTransaction = true;
        console.log($scope.formData);
        disciplineService.save($scope.formData)
        .then(function(data){


            toast.success(constants.message.REGISTRY_SAVED);
            console.log($scope.saveAndNew);

            if($scope.saveAndNew == undefined){
                $("#disciplineCreateModal").modal("hide");
            }


            $scope.formData = {};
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

    $scope.updateDiscipline = function(){
        
    }

}
