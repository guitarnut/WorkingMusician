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

                if (!result.hasOwnProperty("profile")) {
                    $location.path('/createprofile/' + $rootScope.userId);
                } else {
                    $location.path('/profile/' + $rootScope.userId);
                    $scope.profile = JSON.parse(result.profile);
                    $scope.genres = JSON.parse(result.genres);
                    $scope.vocals = JSON.parse(result.vocals);
                    $scope.instruments = JSON.parse(result.instruments);
                }
            }, function error(resp) {
                // do nothing for now
            })
        }

        if ($rootScope.userId) {
            _getProfile();
        } else {
            $location.path('/createprofile/' + $rootScope.userId);
        }

    }]);