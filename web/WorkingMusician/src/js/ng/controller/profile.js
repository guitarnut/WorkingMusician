/**
 * Created by guitarnut on 10/2/16.
 */
app.controller("ProfileController", ['$rootScope', '$scope', '$http', '$location', function ($rootScope, $scope, $http, $location) {

    $scope.profileForm = {};

    if (!$rootScope.userId) {
        $location.path('/');
    } else {
        $scope.user = $rootScope.userId;
    }

    $scope.saveProfile = function () {
        console.log($scope.profileForm);
    };

    $scope.selectOption = function (key, item) {
        item.selected = !item.selected;

        if (!$scope.profileForm.hasOwnProperty(key)) {
            $scope.profileForm[key] = [];
        }

        if (item.selected) {
            $scope.profileForm[key].push(item);
        } else {
            var index = $scope.profileForm[key].indexOf(item);
            $scope.profileForm[key].splice(index, 1);
        }
    };

    function _getProfile() {
        $http({
            method: 'GET',
            url: '//dev.sandbox.com:8080/profile/' + $rootScope.userId
        }).then(function success(resp) {
            $scope.profile = JSON.parse(resp.data.profile);

            if (Object.keys($scope.profile).length === 0) {
                $scope.createProfile = true;
                $scope.profileFormValues = profileFormValues;
                console.log($scope.profileFormValues);
            }
        }, function error(resp) {
            // do nothing for now
        })
    }

    if (!$scope.profile) {
        _getProfile();
    }

}]);