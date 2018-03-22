<%-- 
    Document   : rating
    Created on : 22.03.2018, 17:24:10
    Author     : Nastya Winehouse
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
            <link href="css/style.css" rel="stylesheet"> 
            <title>Рейтинг</title>
        </head>
        <body>
                    <nav class="navbar navbar-default navbar-fixed-top navbar-static-top"/>
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
      			<li><a href="faces/index.jsp">Главная</a> </li>
      			<li><a href="faces/user.jsp">Страница с пользователями</a></li>
      			<li><a href="faces/graf.jsp">Графики</a></li>
                        <li class="active"><a href="#">Рейтинг</a></li>
                        <p class="navbar-text"> Вы вошли как <a href="#" class="navbar-link">Тимофей</a></p>
                         <button  type="button" class="btn btn-default navbar-btn">Войти</button>                         
    		</ul>
  		</div>
             </div>
    </nav>
        <br>
        
         <table class="table table table-striped">
  <thead>
    <tr>
      <th>#</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Username</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
        </body>
    </html>
</f:view>
