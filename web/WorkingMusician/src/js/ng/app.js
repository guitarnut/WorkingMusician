/**
 * Created by guitarnut on 10/2/16.
 */
var app = angular.module('WorkingMusician', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "welcome.html"
        })
        .when("/new", {
            templateUrl: "newuser.html"
        });
}]);