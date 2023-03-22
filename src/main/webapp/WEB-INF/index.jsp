<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
    <html lang="US">
    <head>
        <title>Items</title>
        <meta content="text/html; charset=UTF-8">
            <link href="view/css/common.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="navigation.jsp" />
        <div>
            <div align="center">
                <h2>Item list:</h2>
                <c:forEach var="item" items="${requestScope.items}">
                    <a href="stats/${item.id}">
                        <div clas="item">Creation date: <c:out value="${item.date}" /></div>
                     </a>
                </c:forEach>
                <form method="post" action="start">
                    <input type="submit" value="Create new item" class="button" />
                </form>
            </div>
        </div>
    </body>
</html>