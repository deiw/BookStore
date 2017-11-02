<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 21.10.2017
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add book</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
</head>
<body style="background-color: #f8f8f8">
<jsp:include page="fragment/header.jspf"/>
<div style="background-color: #f8f8f8">
<div class="container">
    <div class="row book-callout">
        <div class="row">
    <form method="post" action="/createbook" enctype="multipart/form-data">
        <input class="form-control"  placeholder="Tittle" type="text" name="title" required><br>
        <input class="form-control"  placeholder="Author" type="text" name="author" required><br>
        <select class="form-control" name="genre" required>
            <option name="horror">Horror</option>
            <option name="fantasy">Fantasy</option>
            <option name="s-f">S-f</option>
            <option name="adult">Adult</option>
            <option name="thriller">Thriller</option>
            <option name="romance">Romance</option>
        </select><br>
        <input class="form-control" placeholder="Price" type="number" name="price" step="0.01" required><br>
        <textarea class="form-control" placeholder="Set description here" type="text" name="description" cols="70" rows="20" required></textarea><br>
        <input type="file" name="image" accept=".jpg"><br>
        <input class="btn btn-lg btn-danger" type="submit" value="Add"/>
        ${requestScope.get("message")}
    </form>
        </div>
</div>
</div>
</div>
<jsp:include page="fragment/footer.jspf"/>
</body>
</html>
