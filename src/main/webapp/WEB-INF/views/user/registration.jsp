<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link rel="stylesheet" href="/resources/css/register.css">

    <script>
        $(document).ready(function() {
            // Nav Tab stuff
            $('.nav-tabs > li > a').click(function() {
                if($(this).hasClass('disabled')) {
                    return false;
                } else {
                    var linkIndex = $(this).parent().index() - 1;
                    $('.nav-tabs > li').each(function(index, item) {
                        $(this).attr('rel-index', index - linkIndex);
                    });
                }
            });

            $('.nav-tabs > li:nth-of-type(0) > a').removeClass('disabled').click();

            var view = "<c:out value="${mode}" />";
            if (view == 'registerAccount') {
                enableStepIcon(1);
            } else if (view == 'registerLogin') {
                enableStepIcon(2);
            } else if (view == 'registerDone') {
                enableStepIcon(3);
                disableStepIcon(1);
            }
        });

        function enableStepIcon(i) {
            $('.nav-tabs > li:nth-of-type(' + i + ')').addClass('active');
            $('.nav-tabs > li:nth-of-type(' + i + ') > a').removeClass('disabled');
        }

        function disableStepIcon(i) {
            $('.nav-tabs > li:nth-of-type(' + i + ')').removeClass('active');
            $('.nav-tabs > li:nth-of-type(' + i + ') > a').addClass('disabled');
        }

    </script>
</head>

<body>

    <div class="form-signin">
        <div class="board">
            <ul class="nav nav-tabs">
                <div class="liner"></div>
                <li rel-index="<c:out value="${mode eq 'registerAccount' ? '0' : '1'}"/>" class="active">
                    <a href="#step-1" class="btn disabled" aria-controls="registerAccount" role="tab" data-toggle="tab">
                        <span><i class="glyphicon glyphicon-user"></i></span>
                    </a>
                </li>
                <li rel-index="<c:out value="${mode eq 'registerLogin' ? '0' : '1'}"/>">
                    <a href="#step-2" class="btn disabled" aria-controls="registerLogin" role="tab" data-toggle="tab">
                        <span><i class="glyphicon glyphicon-lock"></i></span>
                    </a>
                </li>
                <li rel-index="<c:out value="${mode eq 'registerDone' ? '0' : '2'}"/>">
                    <a href="#step-3" class="btn disabled" aria-controls="step-3" role="tab" data-toggle="tab">
                        <span><i class="glyphicon glyphicon-ok"></i></span>
                    </a>
                </li>
            </ul>
        </div>

        <div id="tabpanel" class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title btn btn-success btn-lg" id="myModalLabel">
                        <c:choose>
                            <c:when test="${mode == 'registerAccount'}">
                                <span class="glyphicon glyphicon-user"></span> User Detail Form
                            </c:when>
                            <c:when test="${mode == 'registerLogin'}">
                                <span class="glyphicon glyphicon-lock"></span> User Login Form
                            </c:when>
                            <c:when test="${mode == 'registerDone'}">
                                <span class="glyphicon glyphicon-thumbs-up"></span> Thank you
                            </c:when>
                        </c:choose>
                    </h3>
                </div>

                <div class="modal-body">
                    <c:if test="${not empty alertType}">
                        <div class="alert alert-${alertType}">
                                ${alertMessage}
                        </div>
                    </c:if>
                    <c:choose>
                        <c:when test="${mode == 'registerAccount'}">
                            <c:import url="/WEB-INF/views/user/registerAccount.jsp" />
                        </c:when>
                        <c:when test="${mode == 'registerLogin'}">
                            <c:import url="/WEB-INF/views/user/registerLogin.jsp" />
                        </c:when>
                        <c:when test="${mode == 'registerDone'}">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="well" id="registerLogin">
                                    The registration process is completed. Thank you.
                                </div>
                            </div>
                        </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>

    </div>
</body>
</html>