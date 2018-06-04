'use strict';

/* Controllers */

var streamAppControllers = angular.module('streamAppControllers', []);

streamAppControllers.controller('SSEDataController', function SSEDataController($scope, SseStream) {

    $scope.eventDataObject = {
        id : '0',
        payload: ''
    };

    $scope.eventsCount = 20;

   $scope.onClick = function () {
       if (!SseStream.isEventsInProgress()) {
           SseStream.acceptEvents($scope.eventsCount,
               function(event) {
                   console.log('Event with id: ' + event.id);
                   $scope.eventDataObject = JSON.parse(event.data.toString());
                   $scope.$apply();
               });
       }
   }

});