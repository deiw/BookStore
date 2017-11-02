<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 21.10.2017
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
</head>
<body style="background-color: #f8f8f8">
<jsp:include page="fragment/header.jspf"/>
<jsp:include page="fragment/search.jspf"/>
<jsp:include page="fragment/categories.jspf"/>
<div>
<div>
    <div>
        <c:forEach var="book" items="${requestScope.books}">
            <div class="row bs-callout">
                <div class="row">
                    <div class="col-md-4">
                        <a href="/book?id=${book.id}"><img class="img-responsive"src="${pageContext.request.contextPath}/images/<c:out value="${book.url}"/>" height="280" width="280"></a>
                    </div>
                    <div class="col-md-6">
                        <article>
                        <h4><strong><p><a href="/book?id=${book.id}"><c:out value="${book.title}"/></a></p></strong></h4>
                        <p><a href="/search?author=${book.author}"><c:out value="${book.author}"/></a></p>
                        <p><a href="/search?genre=${book.genre}"><c:out value="${book.genre}"/></a></p>
                        <p><c:out value="${book.price} $"/></p>
                        <p><c:out value="${fn:substring(book.description,0 ,300 )}"/>...</p>
                        <p><a href="/book?id=${book.id}">Check out for more</a></p>
                        </article>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</div>
<jsp:include page="fragment/footer.jspf"/>
</body>
</html>
