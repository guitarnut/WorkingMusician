app.directive("profile", function() {

    return {
        restrict: 'E',
        scope: {},
        controller: 'ProfileController',
        templateUrl: 'templates/profile.html',
        link: function(scope, elem, attrs) {
        }
    }
});