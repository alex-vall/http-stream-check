'use strict';

/* Controllers */

var streamAppControllers = angular.module('streamAppControllers', []);

streamAppControllers.controller('SSEDataController', function SSEDataController($scope) {
    $scope.eventDataObject =
        {
            id: '1247',
            payload: '"Fake payload for 1247'
        }
});