app.directive("createUser", function() {

    return {
        restrict: 'E',
        scope: {},
        controller: 'LoginController',
        templateUrl: 'templates/createnewuser.html',
        link: function(scope, elem, attrs) {
        }
    }
});
