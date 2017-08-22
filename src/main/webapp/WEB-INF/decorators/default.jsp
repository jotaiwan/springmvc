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

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <%--
        <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
        --%>
        <link rel="stylesheet" href="/resources/css/layout.css">

        <script src="/resources/js/jquery-1.11.1.js"></script>
        <script src="https://unpkg.com/popper.js" ></script>
        <script src="/resources/js/bootstrap.min.js"></script>

        <title><dec:title default="SpringMVC" /></title>

        <script>
            $(document).ready(function(){
                var tabId = $(".tab-content").attr("id");
                $("li[role='presentation']").removeClass("active");
                $("li[id='" + tabId + "']").addClass("active");
            });
        </script>

        <dec:head />

    </head>
    <body>
        <!-- layout content -->
        <div class="container top-buffer">
            <jsp:include page="../views/template/navMenuSearch.jsp" />

            <div class="container-fluid top-buffer">
                <dec:body />
            </div>
        </div>
    </body>
</html>