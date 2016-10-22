/**
 * Created by guitarnut on 10/2/16.
 */
<<<<<<< HEAD
app.controller("ProfileController", [
    '$rootScope', '$scope', '$http', '$location', '$routeParams',
    function ($rootScope, $scope, $http, $location, $routeParams) {

        if (!$rootScope.userId) {
            $location.path('/');
        }
=======
app.controller("ProfileController", ['$rootScope', '$scope', '$http', '$location', '$routeParams', function ($rootScope, $scope, $http, $location, $routeParams) {

    $scope.profileForm = {};
    $scope.createProfile = true;

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
            city:$scope.profileForm.city,
            state:$scope.profileForm.state,
            profession:$scope.profileForm.profession,
            availability:$scope.profileForm.availability,
            travel:$scope.profileForm.travel
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
>>>>>>> 4e5c1f2... Changed to MySQL/Hibernate

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
<<<<<<< HEAD
=======
    };

    function _findValue(category, value) {
        var result = "";

        category.map(function(item) {
            if(item.id === value) {
                result = item.name
            }
        });

        return result;
    }

    function _getProfile() {
        $http({
            method: 'GET',
            url: '//dev.sandbox.com:8080/profile/' + $rootScope.userId
        }).then(function success(resp) {
            var result = resp.data;
            console.log(result.hasOwnProperty("profile"));

            if (!result.hasOwnProperty("profile")) {
                $scope.createProfile = true;
                $scope.profileFormValues = profileFormValues;
            } else {
                $scope.createProfile = false;
                $scope.profile = JSON.parse(result.profile);
            }
        }, function error(resp) {
            // do nothing for now
        })
    }

    if (!$scope.profile) {
        _getProfile();
    }
>>>>>>> 4e5c1f2... Changed to MySQL/Hibernate

    }]);