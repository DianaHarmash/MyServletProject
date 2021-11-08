<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="myTag" uri="../WEB-INF/mytags.tld"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setBundle basename="myLocales" var="lang" />
<fmt:setLocale value="${language}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <title>Title</title>
</head>
<body style="background: #b5b9b4">

<h6>Welcome, User</h6>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">ActiUser</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/user"><fmt:message key="getHome" bundle="${lang}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/listOfActivity"><fmt:message key="getList" bundle="${lang}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/myActivities"><fmt:message key="acti" bundle="${lang}"/></a>
                </li>
            </ul>
            <form class="d-flex">
                <button class="btn btn-primary btn-lg disabled"><myTag:customTag/><c:out value="${requestScope.user.login}"/></button>
                <button class="btn btn-outline-primary btn-lg" type="submit"><a href="/logout"><fmt:message key="signOut" bundle="${lang}"/></a></button>
            </form>
        </div>
    </div>
</nav>

<div class="container col-xxl-8 px-4 py-5">
    <div class="row flex-lg-row-reverse align-items-center g-5 py-5">
        <div>
            <h1 class="display-5 fw-bold lh-1 mb-3"><fmt:message key="myMainText" bundle="${lang}"/></h1>
            <h4 class="lead"><fmt:message key="mainText" bundle="${lang}"/></h4>
        </div>
    </div>
</div>

</body>
</html>