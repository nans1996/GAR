<%-- 
    Document   : authorization
    Created on : 22.03.2018, 19:14:35
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
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
            <link href="css/style-authorization.css" rel="stylesheet"> 
            <title>Авторизация</title>
        </head>
        <body>
 <div class="container">
 <div class="row">

 <div class="col-md-offset-3 col-md-6">
 <form class="form-horizontal">
 <span class="heading">АВТОРИЗАЦИЯ</span>
 <div class="form-group">
 <input type="email" class="form-control" id="inputEmail" placeholder="E-mail">
 <i class="fa fa-user"></i>
 </div>
 <div class="form-group help">
 <input type="password" class="form-control" id="inputPassword" placeholder="Password">
 <i class="fa fa-lock"></i>
 <a href="#" class="fa fa-question-circle"></a>
 </div>
 <div class="form-group">
 <div class="main-checkbox">
 <input type="checkbox" value="none" id="checkbox1" name="check"/>
 <label for="checkbox1"></label>
 </div>
 <span class="text">Запомнить</span>
 <a href="faces/index.jsp" class="btn btn-default">ВХОД</a>
 <a href="faces/registration.jsp" class="btn btn-default">Регистрация</a>
 </div>  
 </form>
 </div>

 </div><!-- /.row -->
</div><!-- /.container -->
        </body>
    </html>
</f:view>
