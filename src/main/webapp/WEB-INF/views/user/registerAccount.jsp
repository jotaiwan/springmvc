<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method = "POST" action = "/user/register/login" modelAttribute="account" cssClass="form-account-wide">
    <div class="row">
        <div id="registerAccount" class="col-xs-12">
            <div class="well">
                <form:hidden path="id" />
                <div class="form-group">
                    <label for="firstName" class="control-label">First name</label>
                    <form:input path = "firstName" cssClass="form-control is-invalid" placeholder="Enter First Name" />
                    <span class="help-block text-danger">
                        <form:errors path="firstName" class="help-inline text-danger"/>
                    </span>
                </div>
                <div class="form-group">
                    <label for="lastName" class="control-label">Last name</label>
                    <form:input path = "lastName" cssClass="form-control" placeholder="Enter Last Name" />
                    <span class="has-error" class="help-block">
                        <form:errors path="lastName" class="help-inline text-danger"/>
                    </span>
                </div>
                <div class="form-group">
                    <label for="emailAddress" class="control-label">Email Address</label>
                    <form:input path = "emailAddress" cssClass="form-control" placeholder="Enter Email Address" />
                    <span class="has-error" class="help-block">
                        <form:errors path="emailAddress" class="help-inline text-danger"/>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-7 pull-right">
            <p class="pull-right">
                <button id="register-next" type="submit" class="btn btn-primary">Next</button>
            </p>
        </div>
    </div>

</form:form>
