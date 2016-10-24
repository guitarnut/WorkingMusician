/**
 * Created by guitarnut on 10/2/16.
 */
app.controller("CreateProfileController", [
    '$rootScope', '$scope', '$http', '$location', '$routeParams',
    function ($rootScope, $scope, $http, $location, $routeParams) {

        $scope.profileForm = {};
        $scope.profileFormValues = profileFormValues;

        if (!$rootScope.userId) {
            $location.path('/');
        } else {
            $scope.user = $rootScope.userId;
        }

        $scope.saveProfile = function () {
            var postData = {
                profile: {
                    userId: $routeParams.userId,
                    firstName: $scope.profileForm.firstName,
                    lastName: $scope.profileForm.lastName,
                    city: $scope.profileForm.city,
                    state: $scope.profileForm.state,
                    profession: $scope.profileForm.profession,
                    availability: $scope.profileForm.availability,
                    travel: $scope.profileForm.travel
                },
                instruments: $scope.profileForm.instruments,
                vocals: $scope.profileForm.vocals,
                genres: $scope.profileForm.genres
            };

            _removeUnwantedKeysFromArrayOfObjects(postData.instruments,$rootScope.userId);
            _removeUnwantedKeysFromArrayOfObjects(postData.vocals,$rootScope.userId);
            _removeUnwantedKeysFromArrayOfObjects(postData.genres,$rootScope.userId);

            console.log(postData);

            $http({
                method: 'POST',
                url: '//dev.sandbox.com:8080/profile/' + $routeParams.userId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(postData)
            }).then(function success(resp) {
                $scope.result = resp.data.message;
                $location.path('/profile/' + $rootScope.userId);
            }, function error(resp) {
                // do nothing for now
            })
        };

        function _removeUnwantedKeysFromArrayOfObjects(array, userId) {
            if(!array) {
                return;
            }

            array.map(function(obj) {
                delete obj.$$hashKey;
                delete obj.id;
                obj['userId'] = Number(userId);
            })
        }

        function _findValue(category, value) {
            var result = "";

            category.map(function(item) {
                if(item.id === value) {
                    result = item.name
                }
            });

            return result;
        }

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