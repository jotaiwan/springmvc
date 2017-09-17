<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<form:form method="POST" modelAttribute="user" action="/manageuser/save" class="form-account-wide">
    <form:input type="hidden" path="id" id="id"/>

    <div class="container-fluid">
        <div class="row">
            <h2 class="text-center" style="color: #f0ad4e;"> <Strong> Signup </Strong></h2> <hr />
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="form-group">
                        <div class="input-group">
                            <label for="firstName" class="control-label">First name</label>
                            <form:input path="firstName" cssClass="form-control" placeholder="e.g. Harris"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="form-group">
                        <div class="input-group">
                            <label for="firstName" class="control-label">Last name</label>
                            <form:input path = "lastName" cssClass="form-control" placeholder="e.g. Potter" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="form-group">
                        <div class="input-group">
                            <label for="firstName" class="control-label">Email address</label>
                            <form:input path="emailAddress" cssClass="form-control" placeholder="e.g. test@test.com" />
                        </div>
                    </div>
                </div>
            </div>


            <hr>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="form-group">
                        <button type="submit" class="btn btn-lg btn-block btn-warning"> Register</button>
                    </div>
                </div>
            </div>
        </div> <!-- row -->
    </div>

    <c:if test="${edit}">
            <span class="well pull-left">
                <a href="<c:url value='/user/add-document-${user.id}' />">Click here to upload/manage your documents</a>
            </span>
    </c:if>
</form:form>