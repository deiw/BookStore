<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 21.10.2017
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/fontello.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
</head>
<body style="background-color: #f8f8f8">
<jsp:include page="fragment/header.jspf"/>
<c:url value="/cart" var="addurl">
    <c:param name="action" value="add"/>
    <c:param name="title" value="${book.title}"/>
    <c:param name="price" value="${book.price}"/>
    <c:param name="id" value="${book.id}"/>
</c:url>
<div style="background-color: #f8f8f8">
    <jsp:include page="fragment/search.jspf"/>
<div class="container">
    <section>
    <div class="row bs-callout">
        <div class="row">
        <div class="col-md-5">
    <img class="img-responsive" src="${pageContext.request.contextPath}/images/${requestScope.book.url}" height="400" width="400"><br>
        </div>
            <article>
        <div class="col-md-6">
         <h3><strong><p>${requestScope.book.title}</p></strong></h3>
            <p style="font-size: medium">Author: <a href="/search?author=${requestScope.book.author}">${requestScope.book.author}</a></p>
            <p style="font-size: medium">Genre: <a href="/search?genre=${requestScope.book.genre}">${requestScope.book.genre}</a></p>
            <p style="font-size: medium">Price: ${requestScope.book.price} $</p>
            <p><form method="post" action="${addurl}">
                <input  type="number" name="amount" value="1" required min="1" max="10">
                <input class="btn btn-danger" type="submit" value="Add to cart">
        </form></p>
    <p style="font-size: medium; text-align: justify"><c:out value="${requestScope.book.description}"/></p>
        </div>
            </article>
    </div>
    </div>
    </section>
</div>
    <jsp:include page="fragment/footer.jspf"/>
</div>
</body>
</html>
