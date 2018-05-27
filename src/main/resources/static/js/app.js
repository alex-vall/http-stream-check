var app = angular.module('streamApp', []);

app.controller('HelloWorldController', function HelloWorldController($scope) {
    $scope.name = 'Some name';
})