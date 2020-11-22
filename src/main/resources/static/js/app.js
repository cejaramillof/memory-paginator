/*
 * Base controladores Angular
 **/

url = function() {
    var url = $(location).attr('href');
    var nots = url.match(/^http:\/\/[^/]+/);
    var haves = url.match(/^https:\/\/[^/]+/);

    var m = (haves != null ? haves : nots);

    m ? m[0] : null;

    return m;
}

var APIURL = url() + "/api/task/";

var app = angular.module("appModel", []);
    app.constant("ENDPOINT", APIURL);