<%-- 
    Document   : registration
    Created on : 22.03.2018, 20:43:25
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
            <title>Регистрация</title>
        </head>
        <body>
            <div class="container">
 <div class="row">
      <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal">
 <span class="heading">РЕГИСТРАЦИЯ</span>

  <div class="form-group">
    <label class="control-label col-xs-3" for="lastName">Фамилия:</label>
    <div class="col-xs-9">
      <input type="text" readonly onfocus="this.removeAttribute('readonly')" class="form-control" id="lastName" placeholder="Введите фамилию">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-xs-3" for="firstName">Имя:</label>
    <div class="col-xs-9">
      <input type="text" readonly onfocus="this.removeAttribute('readonly')" class="form-control" id="firstName" placeholder="Введите имя">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-xs-3" for="inputEmail">Email:</label>
    <div class="col-xs-9">
      <input type="email" readonly onfocus="this.removeAttribute('readonly')" class="form-control" id="inputEmail" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-xs-3" for="inputPassword">Пароль:</label>
    <div class="col-xs-9">
      <input type="password" readonly onfocus="this.removeAttribute('readonly')" class="form-control" id="inputPassword" placeholder="Введите пароль">
    </div>
  </div>

  <div class="form-group">
    <label class="control-label col-xs-3" for="phoneNumber">Телефон:</label>
    <div class="col-xs-9">
      <input type="tel" readonly onfocus="this.removeAttribute('readonly')" class="form-control" id="phoneNumber" placeholder="Введите номер телефона">
    </div>
  </div>
  <div class="form-group">
    <div class="col-xs-offset-3 col-xs-9">
      <label class="checkbox-inline">
        <input type="checkbox" value="agree">  Я согласен с <a href="#">условиями</a>.
      </label>
    </div>
  </div>
  <br />
  <div class="form-group">
    <div class="col-xs-offset-3 col-xs-9">
      <input type="submit" class="btn btn-primary" value="Регистрация">
      <input type="reset" class="btn btn-default" value="Очистить форму">
    </div>
  </div>
  </div>
  </div>
    </div>
</form>
        </body>
    </html>
</f:view>
