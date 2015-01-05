
angular
.module('SmartschoolApp')
.controller('DisciplineController', ['$scope', 'DisciplineService','constants','toast','ngTableParams', disciplineController]);

function disciplineController($scope,   disciplineService,  constants,  toast, ngTableParams) {

    $scope.title = "Disciplinas";
    $scope.disciplines = [];
    $scope.onTransaction = false;
    $scope.onResponse = false;
    $scope.formData;


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
                page: 1,            // show first page
                count: 10           // count per page
            }, {
                total: $scope.disciplines.length, // length of data
                getData: function($defer, params) {
                    $defer.resolve($scope.disciplines.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            });

            $scope.onTransaction = false;
            $scope.onResponse = true;

        },function(data){
            console.log(data.status)

            if(data.status == 0)
                $scope.responseData = new ResponseData(constants.message.CONNECTION_ERROR, constants.status.DANGER);
                else
                    $scope.responseData = new ResponseData(constants.message.ERROR, constants.status.DANGER);

                    $scope.onTransaction = false;
                    $scope.onResponse = true;
                });
    }

    $scope.saveDiscipline = function(){

        $scope.onTransaction = true;

        disciplineService.save()
        .then(function(data){

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

}
