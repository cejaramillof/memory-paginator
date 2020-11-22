
/*
 * Servicio del front
 */
app.service('webService', ['$http', 'ENDPOINT', function ($http, ENDPOINT) {

  this.getAll = function () {
    return $http.get(ENDPOINT);
  };

  this.startProcess = function (process) {
    return $http.put(ENDPOINT, process);
  };

  this.stopProcess = function (id) {
    return $http.delete(ENDPOINT + id);
  };

}]);

