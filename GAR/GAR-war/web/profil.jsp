<%-- 
    Document   : profil
    Created on : 27.03.2018, 0:47:54
    Author     : Nastya Winehouse
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

            <!-- Latest compiled and minified CSS -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
            <!-- Optional theme -->
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
            <!-- Latest compiled and minified JavaScript -->
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
            <link href="css/style.css" rel="stylesheet">
            <link href="css/profil.css" rel="stylesheet">
            <title>Профиль</title>
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
                    <li class="active"><a href="#">Главная  </a> </li>
                    <li><a href="goal/faces/goals.jsp">Цели</a></li>
                    <li><a href="faces/rating.jsp">Рейтинг</a></li>
                    <p class="navbar-text"> Вы вошли как <a href="faces/profil.jsp" class="navbar-link">Тимофей</a></p>
                    <a href="faces/authorization.jsp" class="btn btn-default navbar-btn">Войти</a>                         
                </ul>
            </div>
        </div>
    </nav>  
    <br>
    <div id="left">
        <p>Левая половина</p>
        <div id="main">


            <div class="row" id="real-estates-detail">
                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <header class="panel-title">
                                <div class="text-center">
                                    <strong>Профиль</strong>
                                </div>
                            </header>
                        </div>
                        <div class="panel-body">
                            <div class="text-center" id="author">
                                <img src="http://cdn.marketplaceimages.windowsphone.com/v8/images/299c44e9-0a3a-4c16-9f49-830348483f80?imageType=ws_icon_large">
                                <h3>Маша Милаш</h3>
                                <small class="label label-warning">Российская Федерация</small>
                                <p>Снимаюсь в мультфильме "Маша и Медведь".</p>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-8 col-xs-12">
                    <div class="panel">
                        <div class="panel-body">
                            <ul id="myTab" class="nav nav-pills">
                                <li class="active"><a href="#detail" data-toggle="tab">Мои данные</a></li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <hr>
                                <div class="tab-pane fade active in" id="detail">
                                    <h4>История профиля</h4>
                                    <table class="table table-th-block">
                                        <tbody>
                                            <tr><td class="active">Зарегистрирован:</td><td>12-06-2016</td></tr>
                                            <tr><td class="active">Последняя активность:</td><td>12-06-2016 / 09:11</td></tr>
                                            <tr><td class="active">Страна:</td><td>Россия</td></tr>
                                            <tr><td class="active">Город:</td><td>Воладимир</td></tr>
                                            <tr><td class="active">Пол:</td><td>Женский</td></tr>
                                            <tr><td class="active">Полных лет:</td><td>10</td></tr>
                                            <tr><td class="active">Рейтинг пользователя:</td><td><i class="fa fa-star" style="color:red"></i> <i class="fa fa-star" style="color:red"></i> <i class="fa fa-star" style="color:red"></i> <i class="fa fa-star" style="color:red"></i> 4/5</td></tr>
                                            <tr><td class="active">Плагин рейтинга:</td><td><a href="http://bootstraptema.ru/stuff/plugins_bootstrap/improvement/bootstrap_star_rating/12-1-0-73" target="_blank">http://goo.gl/bGGXuw</a></td></tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- /.main -->

    <div id="right">
        <p>Правая половина</p>
        <br>

        <ul class="nav">
            <li class="nav-header">Мои цели</li>
            <li class="active"><a href="goal/goal_user.xhtml">Бегать по утрам</a></li>
            <li><a href="#">Ходить на йогу</a></li>
            <li><a href="#">Ложиться в 21.00</a></li>
            <li><a href="#">Бросить курить</a></li>
        </ul>
    </div>
</body>
</html>
</f:view>
