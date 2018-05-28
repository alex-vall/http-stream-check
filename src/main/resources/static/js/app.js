'use strict';

var app = angular.module('app', []);

app.controller('SSEDataController', function SSEDataController($scope) {
    $scope.eventDataObject =
        {
            id: '1247',
            payload: '"Fake payload for 1247'
        }
});