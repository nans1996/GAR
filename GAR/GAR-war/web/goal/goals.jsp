<%-- 
    Document   : goals
    Created on : 22.03.2018, 17:21:26
    Author     : Admin
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
            <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap-flex.min.css" rel="stylesheet" >
            <link href="../css/style.css" rel="stylesheet">
            <link href="../css/my-slider.css" rel="stylesheet">
            <link href="../css/goals.css" rel="stylesheet">
            <script src="js/ism-2.2.min.js"></script>

            <title>Цели</title>
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
                    <li><a href="faces/index.jsp">Главная  </a> </li>
                    <li class="active"><a href="#">Цели</a></li>
                    <li><a href="faces/user.jsp">Страница с пользователями</a></li>
                    <li><a href="faces/graf.jsp">Графики</a></li>
                    <li><a href="faces/rating.jsp">Рейтинг</a></li>
                    <p class="navbar-text"> Вы вошли как <a href="faces/profil.jsp" class="navbar-link">Тимофей</a></p>
                    <a href="faces/authorization.jsp" class="btn btn-default navbar-btn">Войти</a>                         
                </ul>
            </div>
        </div>
    </nav>  
    <br>
    <br>
    <br>
    <form class="well form-search">
        <input type="text" class="input-medium search-query">
        <button type="submit" class="btn">Поиск</button>
    </form>
    <h:form>
        <div class="row">
            <a href="#" class="mask">
                <div class="col-xs"><img src="https://cdn.lifehacker.ru/wp-content/uploads/2014/10/01_1441803818-1140x547.png" alt="image" class="img-responsive zoom-img" />
            </a>
            <h3>Бегать по утрам</h3>
            <div class="links">
                <ul>
                    <li><i class="fa blog-icon fa-calendar"> </i><span>Март 27, 2018</span></li>
                    <li><i class="fa blog-icon fa-user"> </i><span>Admin</span></li>
                    <li><i class="fa blog-icon fa-comment"> </i><a href="#"><span>Без комментариев</span></a></li>
                </ul>
            </div>
            <p>Все еще обещаешь себе начать заниматься спортом? Утренняя пробежка – отличная привычка, благодаря которой можно решить множество проблем, с которыми все чаще сталкивается все население планеты.</p>
            <a href="#" class="btn1 btn-8 btn-8c">Добавить</a></div>
        <a href="#" class="mask">
            <div class="col-xs"><img src="https://estet-portal.com/images/article/main/polza-yogi-6-vesomyh-argumentov-vstat-na-kovrik-1513156626.jpeg" alt="image" class="img-responsive zoom-img"/>
        </a>
        <h3>Ходить на йогу</h3>
        <div class="links">
            <ul>
                <li><i class="fa blog-icon fa-calendar"> </i><span>Март 27, 2018</span></li>
                <li><i class="fa blog-icon fa-user"> </i><span>Admin</span></li>
                <li><i class="fa blog-icon fa-comment"> </i><a href="#"><span>Без комментариев</span></a></li>
            </ul>
        </div>
        <p>Польза йоги в первую очередь касается тела, ведь доказано — практика асан развивает гибкость, приводит мышцы в тонус, оздоравливает все системы организма. Но кроме этого, занятия йогой положительно сказываются и на когнитивных способностях и психологическом состоянии человека.</p>
        <a href="#" class="btn1 btn-8 btn-8c">Добавить</a></div>
    <div class="col-xs">.col-xs</div>
</div>
</h:form>
</body>
</html>
</f:view>
