<%--
  Created by IntelliJ IDEA.
  User: alex.volosatov
  Date: 02.03.2018
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<html lang="en" ng-app="streamApp" class="no-js">
<head>
    <title>Angular JS App</title>
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/webjars/angularjs/1.4.9/angular.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angularjs/1.4.9/angular-resource.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angularjs/1.4.9/angular-route.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angular-ui-bootstrap/2.5.0/ui-bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.js"></script>
    <script src="${pageContext.request.contextPath}js/app.js"></script>
    <script src="${pageContext.request.contextPath}js/controllers.js"></script>
    <script src="${pageContext.request.contextPath}js/services.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/4.1.0/css/bootstrap.css">

</head>
<body ng-controller="SSEDataController">

<p>SSE object</p>

<div>
    <p>Object ID: {{eventDataObject.id}}</p>
    <p>Object Payload: {{eventDataObject.payload}}</p>
</div>

<div>
    <label>
        <input ng-model="eventsCount">
    </label>
</div>

<div>
    <button ng-click="onClick()">
        Send
    </button>
</div>

<%--<table>--%>
    <%--<tr><th>Row number</th></tr>--%>
    <%--<tr ng-repeat="i in [0, 1, 2, 3, 4, 5, 6, 7]"><td>{{i}}</td></tr>--%>
<%--</table>--%>

</body>
</html>
