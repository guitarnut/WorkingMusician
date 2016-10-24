/**
 * Created by guitarnut on 10/2/16.
 */
app.controller("LoginController", [
    '$rootScope', '$scope', '$http', '$location', '$routeParams',
    function ($rootScope, $scope, $http, $location, $routeParams) {

        // temp
        //$rootScope.userId = "29";
        $scope.validUsername = false;

        if($routeParams.message === 'error') {
            $scope.message = "You entered an incorrect username or password."
        }

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
                console.log(resp.data);
                $rootScope.userId = resp.data.id;
                if (!$rootScope.userId) {
                    $location.path('/new/error');
                } else {
                    $location.path('/profile/' + $rootScope.userId);
                }
            }, function error(resp) {
                // do nothing for now
            })
        };

        $scope.logoutUser = function () {
            $rootScope.userId = null;
            $scope.profile = null;
            $location.path('/');
        };

        $scope.validateUser = function () {
            var postData = {
                username: $scope.email
            };

            $http({
                method: 'POST',
                url: '//dev.sandbox.com:8080/user/validate',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(postData)
            }).then(function success(resp) {
                if(resp.data.message === "true") {
                    $scope.validUsername = true;
                    $scope.feedback = null;
                } else {
                    $scope.validUsername = false;
                    $scope.feedback = "Username already exists";
                }
            }, function error(resp) {
                // do nothing for now
            })
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

    }]);
