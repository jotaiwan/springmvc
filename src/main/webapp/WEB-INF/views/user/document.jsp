<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${not empty alertType}">
    <div class="alert alert-${alertType}">
            ${alertMessage}
    </div>
</c:if>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">${user.firstName} ${user.lastName}'s Documents </span></div>
    <div class="tablecontainer">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>No.</th>
                <th>File Name</th>
                <th>Type</th>
                <th>Description</th>
                <th width="50"></th>
                <th width="50"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${documents}" var="doc" varStatus="counter">
                <tr>
                    <td>${counter.index + 1}</td>
                    <td>${doc.name}</td>
                    <td>${doc.type}</td>
                    <td>${doc.description}</td>
                    <td><a href="<c:url value='/user/download-document-${user.id}-${doc.id}' />" class="btn btn-primary">
                        <span class="glyphicon glyphicon-save"/></a></td>
                    <td><a href="<c:url value='/user/delete-document-${user.id}-${doc.id}' />" class="btn btn-danger">
                        <span class="glyphicon glyphicon-trash"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="panel panel-default">

    <div class="panel-heading"><span class="lead">Upload File</span></div>
    <div class="uploadcontainer  panel-body">
        <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="file">Upload a document</label>
                    <div class="col-md-7">
                        <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="file" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="description">Description</label>
                    <div class="col-md-7">
                        <form:input type="text" path="description" id="description" class="form-control input-sm"/>
                    </div>
                </div>
            </div>

            <div class="form-actions floatRight">
                <input type="submit" value="Upload" class="btn btn-primary btn-sm">
            </div>

        </form:form>
    </div>
</div>
