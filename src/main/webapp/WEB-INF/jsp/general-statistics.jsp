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
        <div>
            <div align="center">
            <jsp:include page="navigation.jsp" />
            <c:out value="${message}" />
                <h2>General statistics:</h2>
                <c:if test="${ generalStatistics ne null}">
                    <div>
                        Created items: <c:out value="${generalStatistics.itemsAmount}" />
                    </div>
                    <div>
                        Amount of broken microcurcuits: <c:out value="${generalStatistics.brokenMicrocircuits}" />
                    </div>
                    <div>
                        Produced fuel: <c:out value="${generalStatistics.producedFuel}" />
                    </div>
                    <div>
                        Spent fuel: <c:out value="${generalStatistics.spentFuel}" />
                    </div>
                </c:if>
                <c:if test="${ generalStatistics eq null}">
                    <div>
                        There is no items cteated yet.
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>