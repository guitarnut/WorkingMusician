/**
 * Created by guitarnut on 10/2/16.
 */
app.controller("CreateProfileController", [
    '$rootScope', '$scope', '$http', '$location', '$routeParams',
    function ($rootScope, $scope, $http, $location, $routeParams) {

        $scope.profileForm = {};

        if (!$rootScope.userId) {
            $location.path('/');
        } else {
            $scope.user = $rootScope.userId;
        }

        $scope.saveProfile = function () {
            console.log($scope.profileForm);

            var postData = {
                userId: $routeParams.userId,
                firstName: $scope.profileForm.firstName,
                lastName: $scope.profileForm.lastName,
                city: $scope.profileForm.city,
                state: $scope.profileForm.state,
                profession: $scope.profileForm.profession,
                availability: $scope.profileForm.availability,
                travel: $scope.profileForm.travel
            };

            $http({
                method: 'POST',
                url: '//dev.sandbox.com:8080/profile/' + $routeParams.userId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(postData)
            }).then(function success(resp) {
                $scope.createProfile = false;
                $scope.result = resp.data.message;
                $rootScope.profile = JSON.parse(resp.data.profile).id;
                $location.path('/profile/' + $rootScope.userId);
            }, function error(resp) {
                // do nothing for now
            })
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

    }]);