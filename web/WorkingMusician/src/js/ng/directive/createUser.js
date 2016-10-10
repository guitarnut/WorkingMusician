app.directive("createUser", function() {

    return {
        restrict: 'E',
        scope: {},
        controller: 'LoginController',
        templateUrl: 'templates/createUser.html',
        link: function(scope, elem, attrs) {
        }
    }
});
