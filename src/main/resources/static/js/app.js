'use strict';

var app = angular.module('app', []);

app.controller('HelloWorldController', function HelloWorldController($scope) {
    $scope.someUserName = 'Vasily Terkin';
});