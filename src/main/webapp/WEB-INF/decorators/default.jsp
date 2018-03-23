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
        <link rel="stylesheet" href="/resources/css/navbar.css">

        <script src="/resources/js/jquery-1.11.1.js"></script>
        <script src="https://unpkg.com/popper.js" ></script>
        <script src="/resources/js/bootstrap.min.js"></script>

        <title><dec:title default="SpringMVC" /></title>

        <script>
            $(document).ready(function(){
                var tabId = $(".tab-content").attr("id");
                $("li[role='presentation']").removeClass("active");
                $("li[id='" + tabId + "']").addClass("active");

                $('a[href="#toggle-search"], .navbar-bootsnipp .bootsnipp-search .input-group-btn > .btn[type="reset"]').on('click', function(event) {
                    event.preventDefault();
                    $('.navbar-bootsnipp .bootsnipp-search .input-group > input').val('');
                    $('.navbar-bootsnipp .bootsnipp-search').toggleClass('open');
                    $('a[href="#toggle-search"]').closest('li').toggleClass('active');

                    if ($('.navbar-bootsnipp .bootsnipp-search').hasClass('open')) {
                        setTimeout(function() {
                            $('.navbar-bootsnipp .bootsnipp-search .form-control').focus();
                        }, 100);
                    }
                });

                $(document).on('keyup', function(event) {
                    if (event.which == 27 && $('.navbar-bootsnipp .bootsnipp-search').hasClass('open')) {
                        $('a[href="#toggle-search"]').trigger('click');
                    }
                });
            });
        </script>

        <dec:head />

    </head>
    <body>
        <!-- layout content -->
        <div class="container">
            <%--
            <jsp:include page="../views/template/navMenuSearch.jsp" />
            --%>
            <jsp:include page="../views/template/navBar.jsp" />

            <div class="container-fluid top-padding">
                <dec:body />
            </div>
        </div>
    </body>
</html>