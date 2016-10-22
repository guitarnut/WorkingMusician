/**
 * Created by guitarnut on 10/2/16.
 */
app.controller("ProfileController", [
    '$rootScope', '$scope', '$http', '$location', '$routeParams',
    function ($rootScope, $scope, $http, $location, $routeParams) {

        if (!$rootScope.userId) {
            $location.path('/');
        }

        function _getProfile() {
            $http({
                method: 'GET',
                url: '//dev.sandbox.com:8080/profile/' + $rootScope.userId
            }).then(function success(resp) {
                var result = resp.data;
                console.log(result.hasOwnProperty("profile"));

                if (!result.hasOwnProperty("profile")) {
                    $location.path('/createprofile/' + $rootScope.userId);
                    $scope.profileFormValues = profileFormValues;
                } else {
                    $location.path('/profile/' + $rootScope.userId);
                    $scope.profile = JSON.parse(result.profile);
                }
            }, function error(resp) {
                // do nothing for now
            })
        }

        if (!$scope.profile && $rootScope.userId) {
            _getProfile();
        } else {
            $location.path('/createprofile/' + $rootScope.userId);
        }

    }]);