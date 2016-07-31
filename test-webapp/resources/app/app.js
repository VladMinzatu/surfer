var app = angular.module("surfer-test-app", []);
app.controller("itemsController", function($scope, $http) {
    $scope.items = [];
    $scope.addItem = function() {
        $scope.errortext = "";
        if (!$scope.newItem) {return;}
        if ($scope.items.indexOf($scope.newItem) == -1) {
    	    $scope.items.push($scope.newItem);
    	    $scope.newItem = "";
        } else {
            $scope.errortext = "Item is already in the list.";
        }
    }

    $scope.removeItem = function(i) {
        $scope.items.splice(i, 1)
    }
    $scope.voteItem = function(i) {
        $http.post('/items/' + $scope.items[i], {})
    }
});

app.controller("scoresController", function($scope, $http, $interval) {
    $scope.scores = [];
    $interval(function() {
        $http.get('/items').then(function(response) {$scope.scores=response.data;});
        },
        1000)
});
