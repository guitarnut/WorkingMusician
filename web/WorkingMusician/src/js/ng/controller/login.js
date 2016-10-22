/**
 * Created by guitarnut on 10/2/16.
 */
app.controller("LoginController", [
    '$rootScope', '$scope', '$http', '$location',
    function ($rootScope, $scope, $http, $location) {

        // temp
        $rootScope.userId = "19";

        $scope.loginUser = function () {
            var postData = {
                username: $scope.email,
                password: $scope.password
            };

            $http({
                method: 'POST',
                url: '//dev.sandbox.com:8080/user/login',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(postData)
            }).then(function success(resp) {
                $rootScope.userId = resp.data.id;
                $location.path('/profile');
            }, function error(resp) {
                // do nothing for now
            })
        };

        $scope.logoutUser = function () {
            $rootScope.userId = null;
            $location.path('/');
        };

        $scope.createUser = function () {
            $scope.result = "Waiting...";

            var postData = {
                username: $scope.email,
                password: $scope.password
            };

            $http({
                method: 'POST',
                url: '//dev.sandbox.com:8080/user',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(postData)
            }).then(function success(resp) {
                $scope.result = resp.data.message;
                $rootScope.userId = JSON.parse(resp.data.user).id;
                $location.path('/profile/' + $rootScope.userId);
            }, function error(resp) {
                // do nothing for now
            })
        };

        function _validateInput() {
        }

        function _getProfile() {
            $http({
                method: 'GET',
                url: '//dev.sandbox.com:8080/user/' + $rootScope.userId
            }).then(function success(resp) {
                return (resp.data);
            }, function error(resp) {
                // do nothing for now
            })
        }


    }]);
