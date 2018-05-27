<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SSE example</title>

<script>
    function sendRequest(){
        if(typeof(EventSource) !== "undefined") {
            var source = new EventSource("/regular/generateStreamReactive?count=20");
            source.onmessage = function(event) {
                document.getElementById("sseDiv").innerHTML = event.data;
            };
        } else {
            document.getElementById("sseDiv").innerHTML =
                    "Your browser does not support server-sent events.";
        }
    }
</script>

</head>
<body>

<a href="#" onclick="sendRequest()">Send Request</a>
<div id="sseDiv"></div>





</body>
</html>