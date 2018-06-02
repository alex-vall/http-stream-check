'use strict';

/* Services */

var httpStreamServices = angular.module('httpStreamServices', []);

httpStreamServices.factory('SseStream', function ($rootScope) {

    console.log("Service created");

    var source = new EventSource("/regular/generateStreamReactive?count=20");

    var data = '{"id":1000,"payload":"Fake payload for 1000"}';

    source.onmessage = function(event) {
        // $scope.eventDataObject = JSON.parse(event.data);
        data = event.data;
        $rootScope.$apply();
        console.log(data);
    };

    return {
        getEventData: function () {
            return data;
        }
    };
});