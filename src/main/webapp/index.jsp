<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setBundle basename="myLocales" var="lang" />
<fmt:setLocale value="${language}" />
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">    <title>Title</title>
</head>
<body style="background: #b5b9b4">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">ActiUser</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
            <%--<button class="btn btn-primary btn-lg" type="submit" name="locale" value="eng">English</button>
            <button class="btn btn-primary btn-lg" type="submit" name="locale" value="rus">Русский</button>--%>
        <form class="d-flex">
        <select id="language" name="language" onchange="submit()">
                <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
                <option value="rus" ${language == 'rus' ? 'selected' : ''}>Русский</option>
            </select>
        </form>
        <form class="d-flex">
            <button class="btn btn-outline-primary btn-lg" type="submit"><a href="${pageContext.request.contextPath}/login"><fmt:message key="logIn" bundle="${lang}"/></a></button>
            <button class="btn btn-outline-primary btn-lg" type="submit"><a href="${pageContext.request.contextPath}/registration"><fmt:message key="signIn" bundle="${lang}"/></a></button>
        </form>
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



