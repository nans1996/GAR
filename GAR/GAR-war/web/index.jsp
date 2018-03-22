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
                        <li><a href="faces/goals.jsp">Цели</a></li>
      			<li><a href="faces/user.jsp">Страница с пользователями</a></li>
      			<li><a href="faces/graf.jsp">Графики</a></li>
                        <li><a href="faces/rating.jsp">Рейтинг</a></li>
                        <p class="navbar-text"> Вы вошли как <a href="#" class="navbar-link">Тимофей</a></p>
                        <a href="faces/authorization.jsp" class="btn btn-default navbar-btn">Войти</a>                         
    		</ul>
  		</div>
             </div>
    </nav>   
        <br>
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
                <br>
                <br>
               <br>
                <br>
                <br>
                <br>
                <p><img src="img/areachart.png" alt="Письма мастера дзен"></p>
                <br>
                <br>
                <br>
                <br>
                <br>             
<footer class="page-footer font-small unique-color-dark pt-0">

    <div style="background-color: #6351ce;">
        <div class="container">
            <!--Grid row-->
            <div class="row py-4 d-flex align-items-center">

                <!--Grid column-->
                <div class="col-md-6 col-lg-5 text-center text-md-left mb-4 mb-md-0">
                    <h6 class="mb-0 white-text">Get connected with us on social networks!</h6>
                </div>
                <!--Grid column-->

                <!--Grid column-->
                <div class="col-md-6 col-lg-7 text-center text-md-right">
                    <!--Facebook-->
                    <a class="fb-ic ml-0">
                        <i class="fa fa-facebook white-text mr-lg-4"> </i>
                    </a>
                    <!--Twitter-->
                    <a class="tw-ic">
                        <i class="fa fa-twitter white-text mr-lg-4"> </i>
                    </a>
                    <!--Google +-->
                    <a class="gplus-ic">
                        <i class="fa fa-google-plus white-text mr-lg-4"> </i>
                    </a>
                    <!--Linkedin-->
                    <a class="li-ic">
                        <i class="fa fa-linkedin white-text mr-lg-4"> </i>
                    </a>
                    <!--Instagram-->
                    <a class="ins-ic">
                        <i class="fa fa-instagram white-text mr-lg-4"> </i>
                    </a>
                </div>
                <!--Grid column-->

            </div>
            <!--Grid row-->
        </div>
    </div>

    <!--Footer Links-->
    <div class="container mt-5 mb-4 text-center text-md-left">
        <div class="row mt-3">

            <!--First column-->
            <div class="col-md-3 col-lg-4 col-xl-3 mb-4">
                <h6 class="text-uppercase font-weight-bold">
                    <strong>Company name</strong>
                </h6>
                <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p>Here you can use rows and columns here to organize your footer content. Lorem ipsum dolor sit
                    amet, consectetur adipisicing elit.</p>
            </div>
            <!--/.First column-->

            <!--Second column-->
            <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
                <h6 class="text-uppercase font-weight-bold">
                    <strong>Products</strong>
                </h6>
                <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p>
                    <a href="#!">MDBootstrap</a>
                </p>
                <p>
                    <a href="#!">MDWordPress</a>
                </p>
                <p>
                    <a href="#!">BrandFlow</a>
                </p>
                <p>
                    <a href="#!">Bootstrap Angular</a>
                </p>
            </div>
            <!--/.Second column-->

            <!--Third column-->
            <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                <h6 class="text-uppercase font-weight-bold">
                    <strong>Useful links</strong>
                </h6>
                <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p>
                    <a href="#!">Your Account</a>
                </p>
                <p>
                    <a href="#!">Become an Affiliate</a>
                </p>
                <p>
                    <a href="#!">Shipping Rates</a>
                </p>
                <p>
                    <a href="#!">Help</a>
                </p>
            </div>
            <!--/.Third column-->

            <!--Fourth column-->
            <div class="col-md-4 col-lg-3 col-xl-3">
                <h6 class="text-uppercase font-weight-bold">
                    <strong>Contact</strong>
                </h6>
                <hr class="deep-purple accent-2 mb-4 mt-0 d-inline-block mx-auto" style="width: 60px;">
                <p>
                    <i class="fa fa-home mr-3"></i> New York, NY 10012, US</p>
                <p>
                    <i class="fa fa-envelope mr-3"></i> info@example.com</p>
                <p>
                    <i class="fa fa-phone mr-3"></i> + 01 234 567 88</p>
                <p>
                    <i class="fa fa-print mr-3"></i> + 01 234 567 89</p>
            </div>
            <!--/.Fourth column-->

        </div>
    </div>
    <!--/.Footer Links-->

    <!-- Copyright-->
    <div class="footer-copyright py-3 text-center">
        © 2018 Copyright:
        <a href="https://mdbootstrap.com/material-design-for-bootstrap/">
            <strong> MDBootstrap.com</strong>
        </a>
    </div>
    <!--/.Copyright -->

</footer>      
        </body>
    </html>
</f:view>
