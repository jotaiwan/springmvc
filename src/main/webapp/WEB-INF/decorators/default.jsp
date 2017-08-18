<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!doctype html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/layout.css"/>" />
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>" />
<%--
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
              integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
              crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
                integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
                crossorigin="anonymous"></script>
--%>
        <title><dec:title default="SpringMVC" /></title>
        <%--
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        --%>
    </head>
    <body>
        <div class="container">
            <ul id="nav">
                <li><a href="/">Home</a></li>
                <li><a class="hsubs" href="/members">Member</a>
                </li>
                <li><a class="hsubs" href="/login/all">login manager</a>
                    <ul class="subs">
                        <li><a href="/login/add">add</a></li>
                    </ul>
                </li>
                <li><a class="hsubs" href="/employees">employees</a>

                </li>
                <li><a href="/shoppingCart">shoppingcart</a></li>
                <li><a href="/products">products</a></li>
                <li><a href="#" onclick="history.back()">Back</a></li>
            </ul>
        </div>

        <!-- layout content -->
        <div class="main">
            <dec:body />
        </div>
        <%--
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        --%>
    </body>
</html>