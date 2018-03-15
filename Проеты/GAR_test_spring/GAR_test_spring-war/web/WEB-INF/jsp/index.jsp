<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome!!!!!!</title>
    </head>

    <body>
        <p>Hello!</p>
        
    <spring:url value="Hello/test" var="articleUrl"></spring:url>

    <a href="${articleUrl}">Test!</a>
    </body>
</html>
