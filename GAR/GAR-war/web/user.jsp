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
            <!-- Latest compiled and minified CSS -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
            <!-- Optional theme -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
            <!-- Latest compiled and minified JavaScript -->
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
            <title>Страница с пользователями</title>
        </head>
        <body>
            <br>
		<div class="navbar-header">
    		<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
      			<span class="sr-only">Toggle navigation</span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
    		</button>
  		</div>

  		<!-- default menu -->
  		<div id="navbarCollapse" class="collapse navbar-collapse">
    		<ul class="nav navbar-nav">
      			<li class="active"><a href="#">Home</a></li>
      			<li><a href="#">Products</a></li>
      			<li><a href="#">Contact</a></li>
    		</ul>
  		</div>
            <br>
            <table border="1">           
                <thead>
                    <tr>
                        <th>Login</th>
                    </tr>
                </thead>
                <c:forEach var="item" items="#{administratorBean.all}">
                    <tr>
                        <th><h:outputText value="#{item.login}"></h:outputText></th>
                    </tr>
                </c:forEach>
            </table>  
                
            <br>

        </body>
    </html>
</f:view>
