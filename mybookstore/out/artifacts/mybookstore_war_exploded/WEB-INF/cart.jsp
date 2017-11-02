<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 24.10.2017
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div>
    <div class="container">
        <c:if test="${empty sessionScope.cartList}">
            <p>Your cart is empty ${sessionScope.user.firstname} ${sessionScope.user.lastname}!</p>
        </c:if>
    <c:if test="${not empty sessionScope.cartList}">
        <table class="table">
            <tr>
                <th>Tittle</th>
                <th>Amount</th>
                <th>Price</th>
                <th>Remove</th>
            </tr>
            <c:forEach var="cart" items="${sessionScope.cartList}">
                <tr>
                    <td><c:out value="${cart.title}"/></td>
                    <td><c:out value="${cart.amount}"/></td>
                    <td><c:out value="${cart.price}"/> $<br></td>
                    <c:url value="/cart" var="removeUrl">
                        <c:param name="action" value="remove"/>
                        <c:param name="title" value="${cart.title}"/>
                        <c:param name="amount" value="${cart.amount}"/>
                        <c:param name="price" value="${cart.price}"/>
                    </c:url>
                    <td><form method="post" action="${removeUrl}">
                        <input class="btn btn-danger" type="submit" value="x">
                    </form></td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${sessionScope.sum!=0.0}">
           <h4> Total: <strong><c:out value="${sessionScope.sum}"/> $</strong></h4>
        </c:if>
        <c:if test="${not empty sessionScope.cartList}">
            <form method="post" action="/my?action=makeOrder">
                <input class="btn btn-danger" type="submit" value="Make order">
            </form>
        </c:if>
    </div>
    </c:if>
</div>
<jsp:include page="fragment/footer.jspf"/>
</body>
</html>
