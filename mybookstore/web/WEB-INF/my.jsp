<%--
  Created by IntelliJ IDEA.
  User: Dawid
  Date: 20.10.2017
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Account</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,900" rel="stylesheet">
</head>
<body style="background-color: #f8f8f8">
<jsp:include page="fragment/header.jspf"/>
<div class="container">
    <h2>Welcome ${sessionScope.user.firstname} ${sessionScope.user.lastname}</h2>
<div style="display: inline">
    <a href="/my?action=history" class="btn btn-danger"><span class="glyphicon glyphicon-ok-circle"></span>History orders</a>
    <a href="/my?action=currentOrders" class="btn btn-danger"><span class="glyphicon glyphicon-flash"></span>Current orders</a>
    <c:if test="${sessionScope.user.email=='majorczyk.dawid@gmail.com'}">
        <a href="/admin" class="btn btn-danger">Admin Panel</a>
    </c:if>
</div>
<div>
    <div class="container">
    <c:if test="${not empty requestScope.activeOrders|| not empty requestScope.historyOrders}">
<c:choose>
    <c:when test="${not empty requestScope.activeOrders}">
      <table class="my-table" border="1">
          <tr>
              <th>Order#</th>
              <th style="text-align: center">Datas</th>
              <th>Total price</th>
          </tr>
          <c:forEach var="order" items="${requestScope.activeOrders}">
              <tr>
                  <td><c:out value="${order.id}"/></td>
                  <td>
                      <table class="my-table" border="1">
                          <tr>
                              <th>Title</th>
                              <th>Amount</th>
                              <th>Price</th>
                          </tr>
                          <c:forEach var="data" items="${requestScope.datas}">
                              <tr>
                                  <td><c:out value="${data.title}"/></td>
                                  <td><c:out value="${data.amount}"/></td>
                                  <td><c:out value="${data.price}"/> $</td>
                              </tr>
                          </c:forEach>
                      </table>
                  </td>
                  <td><c:out value="${order.total}"/> $</td>
              </tr>
          </c:forEach>
      </table>
    </c:when>
    <c:otherwise>
        <table class="my-table" border="1">
            <tr>
                <th>Order#</th>
                <th style="text-align: center">Datas</th>
                <th>Total price $</th>
            </tr>
            <c:forEach var="order" items="${requestScope.historyOrders}">
                <tr>
                    <td><c:out value="${order.id}"/></td>
                    <td>
                        <table class="my-table" border="1">
                        <tr>
                            <th>Title</th>
                            <th>Amount</th>
                            <th>Price</th>
                        </tr>
                        <c:forEach var="data" items="${requestScope.datas}">
                            <tr>
                                <td><c:out value="${data.title}"/></td>
                                <td><c:out value="${data.amount}"/></td>
                                <td><c:out value="${data.price}"/> $</td>
                            </tr>
                        </c:forEach>
                    </table>
                    </td>
                    <td><c:out value="${order.total}"/> $</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
    </c:if>
    </div>
</div>
</div>
<jsp:include page="fragment/footer.jspf"/>
</body>
</html>
