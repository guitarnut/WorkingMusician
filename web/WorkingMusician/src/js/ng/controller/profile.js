/**
 * Created by guitarnut on 10/2/16.
 */
app.controller("ProfileController", ['$rootScope', '$scope', '$http', function($rootScope, $scope, $http){

    $scope.loginUser = function() {
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
        }, function error(resp) {
            // do nothing for now
        })
    };

    $scope.logoutUser = function() {
        $rootScope.userId = null;
    };

    $scope.createUser = function() {
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
            $rootScope.userId = resp.data.user.id;
        }, function error(resp) {
            // do nothing for now
        })
    };

    function _validateInput() {

    }

    function _getProfile() {
        $http({
            method: 'GET',
            url: '//dev.sandbox.com:8080/user'
        }).then(function success(resp) {
            $scope.profile = resp.data.users[0];
        }, function error(resp) {
            // do nothing for now
        })
    }

}]);
