app.directive("createUser", function() {

    return {
        restrict: 'E',
        scope: true,
        controller: 'LoginController',
        templateUrl: 'templates/createnewuser.html',
        link: function(scope, elem, attrs) {
        }
    }
});
