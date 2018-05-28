<%--
  Created by IntelliJ IDEA.
  User: alex.volosatov
  Date: 02.03.2018
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<html lang="en" ng-app="app" class="no-js">
<%--<html ng-app>--%>
<head>
    <title>Angular JS App</title>
    <meta charset="utf-8">
    <script src="${pageContext.request.contextPath}/webjars/angularjs/1.4.9/angular.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angularjs/1.4.9/angular-resource.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angularjs/1.4.9/angular-route.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angular-ui-bootstrap/2.2.0/ui-bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/angular-ui-bootstrap/2.2.0/ui-bootstrap-tpls.js"></script>
    <script src="${pageContext.request.contextPath}js/app.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/css/bootstrap.css">

</head>
<body ng-controller="HelloWorldController">
<%--<body>--%>

<p>Nothing here {{'yet' + '!'}}</p>

<div>
    <p>Hello, {{someUserName}}!</p>
</div>

</body>
</html>
