<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setBundle basename="myLocales" var="lang" />
<fmt:setLocale value="${language}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">    <title>Title</title>
    <title>Login Page</title>
</head>
<body style="background: #b5b9b4">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">ActiUser</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/admin"><fmt:message key="getHome" bundle="${lang}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/listOfActivity"><fmt:message key="getList" bundle="${lang}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/listOfRequest"><fmt:message key="listOfRequest" bundle="${lang}"/></a>
                </li>
            </ul>

            <form class="d-flex">
                <button class="btn btn-primary btn-lg disabled"><c:out value="${requestScope.user.login}"/></button>
                <button class="btn btn-outline-primary btn-lg" type="submit"><a href="/logout"><fmt:message key="signOut" bundle="${lang}"/></a></button>
            </form>
        </div>
    </div>
</nav>

<div class="container">
    <button class="btn btn-outline-primary btn-lg"><a href="/admin/listOfActivity/addAnActivity"><fmt:message key="addNewActivity" bundle="${lang}"/></a></button>
</div>
</div>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><a href="/admin/listOfActivity"><fmt:message key="id" bundle="${lang}"/></a></th>
            <th scope="col"><a href="/admin/listOfActivity/sortByName"><fmt:message key="name" bundle="${lang}"/></a></th>
            <th scope="col"><a href="/admin/listOfActivity/sortByCategory"><fmt:message key="category" bundle="${lang}"/></a></th>
            <th scope="col"><fmt:message key="users" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="delete" bundle="${lang}"/></th>
            <th scope="col"><fmt:message key="edit" bundle="${lang}"/></th>
        </tr>
        </thead>
        <tbody><c:forEach var="activity" items="${requestScope.activities}">
        <tr>
            <th scope="row"><c:out value="${activity.id}"/></th>
            <td><c:out value="${activity.activity}"/></td>
            <td><c:out value="${activity.category}"/></td>
            <td>
                <c:forEach var="userActivity" items="${requestScope.users}">
                    <c:if test="${activity.activity.equals(userActivity.name)}">
                        <c:if test="${userActivity.users != null}">
                            <c:forEach var="theUser" items="${userActivity.users}">
                                <c:out value="${theUser}"/>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
            <td><button class="btn btn-outline-secondary"><a href="/admin/listOfActivity/delete?id=${activity.id}"><fmt:message key="delete" bundle="${lang}"/></a></button></td>
            <td><button class="btn btn-outline-success"><a href="/admin/listOfActivity/edit?id=${activity.id}"><fmt:message key="edit" bundle="${lang}"/></a></button></td>
        </tr>
        </c:forEach></tbody>
    </table>
</div>
<%--
<form action="/admin/listOfActivity" method="GET">
    <button class="btn btn-primary btn-lg" type="submit"><fmt:message key="next" bundle="${lang}"/></button>
</form>
--%>

<nav class="navbar navbar-expand-lg">
<div class="container-fluid">
    <form class="d-flex">
    <button class="btn btn-outline-primary"><a href="/admin/listOfActivity?page=prev">Prev</a></button>
    </form>
    <button class="btn btn-outline-primary"><a href="/admin/listOfActivity?page=next"><fmt:message key="next" bundle="${lang}"/></a></button>
    <!--form class="d-flex"-->
    <!--/form-->
</div>
</nav>

<%--<table>
    <thead>
    <tr>
        <th scope="col"><a href="/admin/listOfActivity?page=1">1</a></th>
        <th scope="col"><a href="/admin/listOfActivity?page=2">2</a></th>
        <th scope="col"><a href="/admin/listOfActivity?page=3">3</a></th>
    </tr>
    </thead>
</table>--%>

</body>
</html>