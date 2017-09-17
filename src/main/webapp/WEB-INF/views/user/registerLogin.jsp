<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method = "POST" action = "/manageuser/register/save" modelAttribute="login" cssClass="form-account-wide">
    <div class="row">
        <div class="col-xs-12">
            <div class="well" id="registerLogin">
                <form:hidden path="accountJsonId"/>
                <div class="form-group" >
                    <label for="currentPassword" class="control-label">Login name</label>
                    <form:input cssClass="form-control" path="username" placeholder="User Name"/>
                    <span class="help-block"></span>
                    <form:errors path="currentPassword" />
                </div>
                <div class="form-group" >
                    <label for="password" class="control-label">Password</label>
                    <form:password cssClass="form-control" path="password" placeholder="New Password" />
                    <span class="help-block"></span>
                </div>
                <div class="form-group" >
                    <label for="confirmPassword" class="control-label">Confirm Password</label>
                    <form:password cssClass="form-control" path="confirmPassword" placeholder="Confirm Password" />
                    <span class="help-block"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-7 pull-right">
            <p class="pull-right">
                <button type="submit" class="btn btn-primary">Register</button>
            </p>
        </div>
    </div>
</form:form>