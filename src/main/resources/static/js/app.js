'use strict';

var app = angular.module('streamApp', []);

app.controller('SSEDataController', function SSEDataController($scope) {
    $scope.eventDataObject =
        {
            id: '1247',
            payload: '"Fake payload for 1247'
        }
});