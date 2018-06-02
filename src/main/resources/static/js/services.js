'use strict';

/* Services */

var httpStreamServices = angular.module('httpStreamServices', []);

httpStreamServices.factory('SseStream', function () {

    console.log("Service created");

    // var source = new EventSource("/regular/generateStream?count=20");
    var source = new EventSource("/regular/generateStreamReactive?count=20");

    source.oncomplete = function () {
        console.log("complete");
    };

    source.onerror = function () {
        console.log("error");
    };

    return {
        getEventSource: function () {
            return source;
        }
    };
});