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
                <h2>Item list:</h2>
                <c:if test="${empty requestScope.items}">
                    <div>
                        There is no item cteated yet.
                    </div>
                </c:if>
                <c:forEach var="item" items="${requestScope.items}">
                    <a href="stats/${item.id}" class="item">
                        <div clas="item">Creation date: <c:out value="${item.date}" /></div>
                     </a>
                </c:forEach>
                <form method="post" action="start">
                    <input id="button" onclick="showMessage()" type="submit" value="Create new item" class="button" />
                </form>
          
                <div id="creating-message" class="message hidden">
                    <div>   
                       <img class="loading" src="view/images/loading.gif">  
                    </div>
                    <div>
                        The item is creating. Wait please, it takes a while.
                    </div>
                </div>
            </div>
        </div>

        <script>
            function showMessage() {
                var btn = document.getElementById("button");
                btn.classList.add("hidden");
                var message = document.getElementById("creating-message");
                message.classList.remove("hidden");
            }
    </script>
    </body>
</html>


