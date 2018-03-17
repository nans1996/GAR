<%-- 
    Document   : index
    Created on : 19.11.2017, 19:25:20
    Author     : Vasilisa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tr" uri="http://myfaces.apache.org/trinidad"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Страница с пользователями</title>
        </head>
        <body>
        <br>
        <br>
            <table border="1">           
                <thead>
                    <tr>
                        <th>Login</th>
                    </tr>
                </thead>
                <c:forEach var="item" items="#{userBean.all}">
                    <tr>
                        <th><h:outputText value="#{item.login}"></h:outputText></th>
                        </tr>
                </c:forEach>
            </table>    
            <br>

        </body>
    </html>
</f:view>
