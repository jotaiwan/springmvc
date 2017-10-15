<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method = "POST" action = "/user/register/save" modelAttribute="login" cssClass="form-account-wide">
    <div class="row">
        <div class="col-xs-12">
            <div class="well" id="registerLogin">
                <form:hidden path="accountJsonId"/>
                <div class="form-group" >
                    <label for="username" class="control-label">Username</label>
                    <form:input cssClass="form-control" path="username" placeholder="User Name"/>
                    <span class="help-block"></span>
                    <form:errors class="text-danger" path="username" />
                </div>
                <div class="form-group" >
                    <label for="password" class="control-label">Password</label>
                    <form:password cssClass="form-control" path="password" placeholder="New Password" />
                    <span class="help-block text-danger" path="Password">
                        <form:errors class="text-danger" path="password" />
                    </span>
                </div>
                <div class="form-group" >
                    <label for="confirmPassword" class="control-label">Confirm Password</label>
                    <form:password cssClass="form-control" path="confirmPassword" placeholder="Confirm Password" />
                    <span class="help-block" path="currentPassword"></span>
                    <form:errors class="text-danger" path="confirmPassword" />
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