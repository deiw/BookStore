<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 20.10.2017
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
</head>
<body style="background-color: #f8f8f8">
<jsp:include page="fragment/header.jspf"/>
<div class="container">
<h2>Administration Panel</h2>
    <div style="display: inline">
<a href="/createbook" class="btn btn-danger"><span class="glyphicon glyphicon-plus"></span>Add Book</a>
    <a href="/admin?action=booksList" class="btn btn-danger"><span class="glyphicon glyphicon-list-alt"></span>Books list</a>
        <a href="/admin?action=usersList" class="btn btn-danger"><span class="glyphicon glyphicon-user"></span>Users list</a>
        <a href="/admin?action=activeOrders" class="btn btn-danger"><span class="glyphicon glyphicon-flash"></span>Active orders</a>
    </div>
<div>
    <c:if test="${not empty requestScope.activeOrders|| not empty requestScope.booksList|| not empty requestScope.usersList}">
    <c:choose>
        <c:when test="${ not empty requestScope.activeOrders}">
<table class="my-table" border="1" >
    <tr>
        <th>Order#</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Street</th>
        <th>City</th>
        <th>E-mail</th>
        <th>Datas</th>
        <th>Total$</th>
        <th>Date</th>
        <th>Status</th>
        <th>Completed</th>
    </tr>
    <c:forEach var="order" items="${requestScope.activeOrders}">
        <tr>
            <td><c:out value="${order.id}"/></td>
            <td><c:out value="${order.user.firstname}"/></td>
            <td><c:out value="${order.user.lastname}"/></td>
            <td><c:out value="${order.user.street}"/></td>
            <td><c:out value="${order.user.city}"/></td>
            <td><c:out value="${order.email}"/></td>
            <td>
             <table border="1">
                 <tr>
                     <th>Title</th>
                     <th>Amount</th>
                     <th>Price</th>
                 </tr>
                 <c:forEach var="data" items="${requestScope.datas}">
                     <tr>
                         <td><c:out value="${data.title}"/></td>
                         <td><c:out value="${data.amount}"/></td>
                         <td><c:out value="${data.price}"/></td>
                     </tr>
                 </c:forEach>
             </table>
             </td>
            <td><c:out value="${order.total}"/>$</td>
            <td><c:out value="${order.odate}"/></td>
            <td><c:choose>
                <c:when test="${order.active==true}">
                    Active
                </c:when>
                <c:otherwise>
                    Complete?
                </c:otherwise>
            </c:choose></td>
            <td>
                <form action="/admin?order=${order.id}" method="post">
                    <input type="submit" value="Done">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
        </c:when>
        <c:when test="${not empty booksList}">
            <table class="table">
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Genre</th>
                    <th>Price</th>
                    <th>Date</th>
                </tr>
                <c:forEach var="book" items="${requestScope.booksList}">
                    <tr>
                        <td><a href="/book?id=${book.id}"><c:out value="${book.title}"/></a></td>
                        <td><c:out value="${book.author}"/></td>
                        <td><c:out value="${book.genre}"/></td>
                        <td><c:out value="${book.price}"/></td>
                        <td><c:out value="${book.bdate}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <table class="table">
                <tr>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>E-mail</th>
                    <th>Street</th>
                    <th>City</th>
                </tr>
                <c:forEach var="user" items="${requestScope.usersList}">
                    <tr>
                        <td><c:out value="${user.firstname}"/></td>
                        <td><c:out value="${user.lastname}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.street}"/></td>
                        <td><c:out value="${user.city}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    </c:if>
</div>
</div>
<jsp:include page="fragment/footer.jspf"/>
</body>
</html>
