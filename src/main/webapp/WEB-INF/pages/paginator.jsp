<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="es" ng-app="appModel">
<head>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <title>${titlesite}</title>
  <meta name="Grupo 23 politécnico" content="SitePoint">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <c:url value="/css/" var="styles" />
  <c:url value="/js/" var="js" />
  <link href="${styles}bootstrap.min.css" rel="stylesheet" />
  <link href="${styles}styles.css" rel="stylesheet" />
</head>

<body ng-controller="frontController">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="#">${titlesite}</a>
    </nav>


    <section class="container-fluid">
        <div class="container">
            <div class="row mt-3">

                <div class="col-lg-6">

                    <div class="form-content">
                        <form>
                          <div class="form-group">
                            <label for="formGroupExampleInput">Programas disponibles</label>
                            <select class="form-control"
                                ng-options="option.name for option in data.listProcess track by option.id"
                                ng-model="data.selectedProcess">
                            </select>
                          </div>
                          <div class="form-group">
                            <label for="formGroupExampleInput2">Tamaño de página</label>
                            <input type="number" ng-disabled="data.selectedProcess == null"
                                    class="form-control"
                                    max="8" min="1"
                                    onkeydown="return false"
                                    ng-model="data.selectedProcess.sizePage">
                          </div>
                          <div class="form-group">
                            <button type="button" ng-click="startProcess(data.selectedProcess)" class="form-control btn-primary">Iniciar proceso</button>
                          </div>
                        </form>
                    </div>

                </div>

                <div class="col-lg-6">

                    <div>
                        <p>Información importante</p>
                    </div>

                    <ul class="list-group">
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                        Capacidad de paginación
                        <span class="badge badge-primary badge-pill">{{data.task.limitMemory}}</span>
                      </li>
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                        Memoria usada
                        <span class="badge badge-primary badge-pill">{{data.task.usedMemory}}</span>
                      </li>
                      <li class="list-group-item d-flex justify-content-between align-items-center">
                        Memoria disponible
                        <span class="badge badge-primary badge-pill">{{data.task.availibleMemory}}</span>
                      </li>
                    </ul>
                </div>

            </div>
        </div>
    </section>


    <section class="container-fluid mb-4">
        <div class="container">
            <div class="row">
                <div class="col">

                      <div class="card">
                        <div class="card-body">
                          <h5 class="card-title">Procesos activos</h5>
                          <p class="card-text">
                              {{ data.task == null ? 'No hay procesos activos' : 'Listado de programas ejecutandose actualmente' }}
                          </p>
                        </div>
                        <ul class="list-group list-group-flush text-white">
                            <li class="list-group-item bg-success d-flex justify-content-between align-items-center"
                              ng-repeat="item in data.task.listProcess | filter: {state:'active'}:true">
                                <span>{{item.name}} </span>
                                <button type="button"
                                    ng-click="stopProcess(item.id)"
                                    class="btn btn-light btn-sm">Terminar</button>
                            </li>
                        </ul>
                      </div>

                      <div class="card mt-5">
                          <div class="card-body">
                            <h5 class="card-title">Proesos pendientes</h5>
                            <p class="card-text">
                                {{ data.task == null ? 'No hay procesos pendientes' : 'Listado de programas pendientes por ejecutar' }}
                            </p>
                          </div>
                          <ul class="list-group list-group-flush text-white">
                            <li class="list-group-item bg-warning"
                                ng-repeat="item in data.task.listProcess | filter:{state: 'pending'}">
                                <span>{{item.name}} </span>
                            </li>
                          </ul>
                      </div>

                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                          <h5 class="card-title">Marco de página</h5>
                          <p class="card-text">{{ data.task != null ? 'Corriendo actualmente...' : 'Sin procesos en ejecución' }}</p>
                        </div>
                        <ul class="list-group list-group-flush">
                          <li class="list-group-item" ng-class=" { 'bg-primary text-white': item > 0, 'text-black': item == 0 } "
                              ng-repeat="item in data.task.positions track by $index">
                              <span>
                                {{item > 0 ? 'Id del proceso: ' + item : 'Espacio disponible'}}
                              </span>
                          </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script src="${js}jquery-3.5.1.slim.min.js"></script>
    <script src="${js}popper.min.js"></script>
    <script src="${js}bootstrap.min.js"></script>
    <script src="${js}angular.min.js"></script>
    <script src="${js}app.js"></script>
    <script src="${js}service.js"></script>
    <script src="${js}controller.js"></script>
  </body>

</html>