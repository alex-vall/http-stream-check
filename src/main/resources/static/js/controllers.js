'use strict';

/* Controllers */

var streamAppControllers = angular.module('streamAppControllers', []);

streamAppControllers.controller('SSEDataController', function SSEDataController($scope, SseStream) {

    var eventSource = SseStream.getEventSource();



    eventSource.onmessage = function(event) {
        $scope.eventDataObject = JSON.parse(event.data.toString());
        $scope.$apply();

        console.log('Event id: ' + event.id);
    };

});