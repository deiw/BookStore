<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 20.10.2017
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
</head>
<body style="background-color: #f8f8f8">
<jsp:include page="fragment/header.jspf"/>
<div class="container" style="margin-top: 3%">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
<form class="form-horizontal" method="post" action="/register">
    <h2>Register</h2>
    <input class="form-control" placeholder="Firstname" name="firstname" required autofocus/><br>
    <input class="form-control" placeholder="Lastname" name="lastname" required><br>
    <input class="form-control" placeholder="Password" type="password" name="password" required/><br>
    <input class="form-control" placeholder="E-mail" type="email" name="email" required/><br>
    <input class="form-control" placeholder="Street" name="street" required/><br>
    <input class="form-control" placeholder="City" name="city" required/><br>
    <input class="btn-danger btn btn-block btn-lg" type="submit" value="Register">
    <p style="margin-top: 5px">Already have account? <a href="/login">Sign in</a></p>
</form>
    <p style="color:red">${requestScope.get("message")}</p>
</div>
</div>
<jsp:include page="fragment/footer.jspf"/>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>
