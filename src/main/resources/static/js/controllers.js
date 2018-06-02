'use strict';

/* Controllers */

var streamAppControllers = angular.module('streamAppControllers', []);

streamAppControllers.controller('SSEDataController', function SSEDataController($scope, SseStream) {

    var data = SseStream.getEventData();
    console.log('Data : ' + data.toString());
    $scope.eventDataObject = JSON.parse(data.toString());
    // $scope.eventDataObject = data;

    // $scope.eventDataObject =
    //     {
    //         id: '1247',
    //         payload: 'Fake payload for 1247'
    //     }
});