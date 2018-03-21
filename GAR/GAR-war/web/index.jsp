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
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <link href="css/style.css" rel="stylesheet">
            <link href="css/my-slider.css" rel="stylesheet">
            <script src="js/ism-2.2.min.js"></script>

            <title>Главная страница</title>
        </head>
        <body>
           
            <nav class="navbar navbar-default navbar-fixed-top navbar-static-top">
  <div class="container">
            <div class="navbar-header">
    		<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
      			<span class="sr-only">Toggle navigation</span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
    		</button> 
                <a class="navbar-brand" href="#">GAR</a>
            </div>
             <!-- default menu -->
             
  		<div id="navbarCollapse" class="collapse navbar-collapse">
                    
    		<ul class="nav navbar-nav navbar-right">
      			<li class="active"><a href="#">Главная  </a> </li>
      			<li><a href="faces/user.jsp">Страница с пользователями</a></li>
      			<li><a href="faces/graf.jsp">Графики</a></li>
                        <p class="navbar-text"> Вы вошли как <a href="#" class="navbar-link">Тимофей</a></p>
                         <button type="button" class="btn btn-default navbar-btn">Войти</button>
    		</ul>
  		</div>
             </div>
       <div class="ism-slider" id="my-slider">
  <ol>
    <li>
      <img src="ism/image/slides/flower-729514_1280.jpg">
      <div class="ism-caption ism-caption-0">My slide caption text</div>
    </li>
    <li>
      <img src="ism/image/slides/beautiful-701678_1280.jpg">
      <div class="ism-caption ism-caption-0">My slide caption text</div>
    </li>
    <li>
      <img src="ism/image/slides/summer-192179_1280.jpg">
      <div class="ism-caption ism-caption-0">My slide caption text</div>
    </li>
  </ol>
</div>
        </body>
    </html>
</f:view>
