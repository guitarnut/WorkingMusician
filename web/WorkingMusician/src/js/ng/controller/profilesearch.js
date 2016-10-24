/**
 * Created by guitarnut on 10/24/16.
 */

app.controller("SearchProfilesController", [
    '$rootScope', '$scope', '$http', '$location', '$routeParams',
    function ($rootScope, $scope, $http, $location, $routeParams) {

        function _getAllProfiles() {
            if (!$scope.profiles) {
                $http({
                    method: 'GET',
                    url: '//dev.sandbox.com:8080/profile/all'
                }).then(function success(resp) {
                    var result = resp.data;
                    var profiles = {};

                    Object.keys(result).map(function (v) {
                        var profile = JSON.parse(result[v].profile);
                        profile['genres'] = JSON.parse(result[v].genres);
                        profile['instruments'] = JSON.parse(result[v].instruments);
                        profile['vocals'] = JSON.parse(result[v].vocals);

                        profiles[profile.userId] = profile;
                    });

                    $scope.profiles = profiles;

                    if ($routeParams.userId) {
                        _showSingleProfile();
                    }
                }, function error(resp) {
                    // do nothing for now
                })
            }
        }

        function _showSingleProfile() {
            if (!$scope.profiles[$routeParams.userId]) {
                $location.path('/search');
            }

            $http({
                method: 'GET',
                url: '//dev.sandbox.com:8080/profile/' + $routeParams.userId + '/viewed'
            });

            $scope.viewprofile = $scope.profiles[$routeParams.userId];
        }

        _getAllProfiles();

    }]);