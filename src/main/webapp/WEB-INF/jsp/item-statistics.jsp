<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
    <html lang="US">
    <head>
   <fmt:setLocale value="en_US"/>    
        <title>Items</title>
        <meta content="text/html; charset=UTF-8">
        <link href="view/css/common.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div>
            <div align="center">
            <jsp:include page="navigation.jsp" />
            <c:if test="${requestScope.item ne null}">
                <h2>Item information:</h2>
                <div>
                    Production of item takes <c:out value="${item.productionDuration}" /> seconds.
                </div>
                <div>
                    Production date: 
                    <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${item.productionDate}" />
                </div>
                <div>
                    Amount of broken microcurcuits: <c:out value="${item.brokenMicrocircuits}" />
                </div>
                <div>
                    Produced fuel: <c:out value="${item.producedFuel}" />
                </div>
                <div>
                    Spent fuel: <c:out value="${item.spentFuel}" />
                </div>
            </c:if>
            <c:out value="${errorMessage}" />                
            </div>
        </div>
    </body>
</html> 