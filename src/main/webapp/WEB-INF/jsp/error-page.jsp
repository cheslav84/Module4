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
                Request from ${pageContext.errorData.requestURI} is failed
                <br />
                Servlet name or type: ${pageContext.errorData.servletName}
                <br />
                Status code: ${pageContext.errorData.statusCode}
        </div>
    </body>
</html>


