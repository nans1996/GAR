<%-- 
    Document   : newjsf
    Created on : 19.12.2017, 0:31:21
    Author     : Vasilisa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <br>
        <h:outputText value="Количество посещений страниц: #{namedBean.count} "/>
        <br>
        <body>
            <p>Введите логин и пароль для доступа к приложению</p>
            <form method="post" action="j_security_check">
                <input type="text" name="j_username"/> login
                <br/><br/>
                <input type="password" name="j_password"/> password
                <br/><br/>
                <input type="submit" value="войти"/>
            </form>
        </body>
    </html>
</f:view>
