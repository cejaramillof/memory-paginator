
/*
 * Controlador del front
 */
app.controller('frontController', ['$scope', 'webService', function ($scope, webService) {

    $scope.data = {
        listProcess: [],
        selectedProcess: null,
        task: null
    };

    init();

    function init() {
        webService.getAll().then(function(response) {
            $scope.data.listProcess = response.data;
            console.log($scope.data.listProcess);
        });
    }

    $scope.startProcess = function(selectedProcess) {
        selectedProcess.state = 'active';
        webService.startProcess(selectedProcess).then(function(response) {
            $scope.data.task = response.data;
            console.log($scope.data.task);
        });
    }

    $scope.stopProcess = function(id) {
       webService.stopProcess(id).then(function(response) {
           $scope.data.task = response.data;
       });
    }

}]);

