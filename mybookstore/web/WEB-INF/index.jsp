<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 20.10.2017
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <%-- imports function tags from JSTL, prefix "fn"--%>
<html>
<head>
  <title>BookStore</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/fontello.css" type="text/css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
</head>
<body style="background-color: #f8f8f8">
<div>
  <jsp:include page="fragment/header.jspf"/>
  <jsp:include page="fragment/search.jspf"/>
  <jsp:include page="fragment/categories.jspf"/>
  <div class="container" >
    <c:forEach var="book" items="${requestScope.books}">
      <section>
        <div class="row bs-callout">
          <div class="row">
            <div class="col-md-4">
              <a href="/book?id=${book.id}"><img class="img-responsive"src="${pageContext.request.contextPath}/images/<c:out value="${book.url}"/>" height="280" width="280"></a>
            </div>
            <div class="col-md-6">
              <h4><strong><p><a href="/book?id=${book.id}"><c:out value="${book.title}"/></a></p></strong></h4>
              <p><a href="/search?author=${book.author}"><c:out value="${book.author}"/></a></p>
              <p><a href="/search?genre=${book.genre}"><c:out value="${book.genre}"/></a></p>
              <p><c:out value="${book.price} $"/></p>
              <p style="text-align: justify"><c:out value="${fn:substring(book.description,0 ,300 )}"/>...</p>
              <p><a href="/book?id=${book.id}">Check out for more</a></p>
            </div>
          </div>
        </div>
      </section>
    </c:forEach>
  </div>
  <div class="container">
    <c:if test="${requestScope.page!=1}">
      <a href="?page=${requestScope.page-1}" class="btn btn-danger previous-action"><span class="glyphicon glyphicon-chevron-left"></span></a>
    </c:if>
    <c:if test="${requestScope.size==10}">
      <a href="?page=${requestScope.page+1}" class="btn btn-danger next-action"><span class="glyphicon glyphicon-chevron-right"></span></a>
    </c:if>
  </div>
</div>
<jsp:include page="fragment/footer.jspf"/>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>