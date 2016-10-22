app.directive("createprofile", function() {

    return {
        restrict: 'E',
        scope: {},
        controller: 'CreateProfileController',
        templateUrl: 'templates/createprofile.html',
        link: function(scope, elem, attrs) {
        }
    }
});
