'use strict';

/* Services */

var httpStreamServices = angular.module('httpStreamServices', []);

httpStreamServices.factory('SseStream', function () {

    console.log("Service created");

    var isBusy = false;

    return {
        acceptEvents: function (count, onEvent) {

            console.log("getEventSource called");

            var source = new EventSource("/regular/generateStreamReactive?count=" + count);

            source.onmessage = onEvent;

            source.oncomplete = function () {
                console.log("complete");
                source.close();
                isBusy = false;
            };

            source.onerror = function () {
                console.log("error");
                source.close();
                isBusy = false;
            };

            isBusy = true;

            return source;
        },
        isEventsInProgress: function () {
            return isBusy;
        }
    };
});