<%--
  Created by IntelliJ IDEA.
  User: alex.volosatov
  Date: 02.03.2018
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="streamApp">
<head>
    <title>Angular JS App</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="js/app.js"/>
    <link rel="stylesheet" href="css/todo.css">
</head>
<body ng-controller="HelloWorldController">
<div class="some-style">
    <p>Hello, {{name}}!</p>
</div>
</body>
</html>
