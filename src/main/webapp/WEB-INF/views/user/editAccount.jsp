<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form id="user-form" modelAttribute="user" action="/user/account/update/${user.id}" method="post" role="form">
    <div class="form-group">
        <label for="username" class="control-label">First name</label>
        <form:input path = "user.firstName" cssClass="form-control" placeholder="Enter First Name" />
        <span class="help-block"></span>
    </div>
    <div class="form-group">
        <label for="password" class="control-label">Last name</label>
        <form:input path = "user.lastName" cssClass="form-control" placeholder="Enter Last Name" />
        <span class="help-block"></span>
    </div>
    <div class="form-group">
        <label for="password" class="control-label">Email Address</label>
        <form:input path = "user.emailAddress" cssClass="form-control" placeholder="Enter Email Address" />
        <span class="help-block"></span>
    </div>
    <div id="loginErrorMsg" class="alert alert-error hide">Wrong username or password</div>
    <div class="form-group">
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3">
                <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Update">
            </div>
        </div>
    </div>
</form>
