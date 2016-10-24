/**
 * Created by guitarnut on 10/2/16.
 */
var app = angular.module('WorkingMusician', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "welcome.html"
        })
        .when("/search", {
            templateUrl: "search.html"
        })
        .when("/faq", {
            templateUrl: "faq.html"
        })
        .when("/contact", {
            templateUrl: "contact.html"
        })
        .when("/new", {
            templateUrl: "newuser.html"
        })
        .when("/new/:message", {
            templateUrl: "newuser.html"
        })
        .when("/profile/:userId", {
            templateUrl: "profile.html"
        })
        .when("/createprofile/:userId", {
            templateUrl: "createprofile.html"
        })
        .when("/viewprofile/:userId", {
            templateUrl: "viewprofile.html"
        })
        .otherwise({redirectTo : '/'})
}]);