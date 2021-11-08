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

<section class = "vh-100" style = "background-color: #b5b9b4;">
    <div class = "container h-100">
        <div class = "row d-flex justify-content-center align-items-center h-100">
            <div class = "col-lg-12 col-xl-11">
                <div class = "card text-black" style = "border-radius: 25px;">
                    <div class = "card-body p-md-5"> <div class = "row justify-content-center">
                        <div class = "col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                            <p class = "text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"><fmt:message key="edit" bundle="${lang}"/></p>
                            <form action="/admin/listOfActivity/edit?id=${requestScope.activity.id}" method="post" class = "mx-1 mx-md-4">
                                <div class = "d-flex flex-row align-items-center mb-4">
                                    <i class = "fas fa-user fa-lg me-3 fa-fw"> </i>
                                </div>
                                <div class = "d-flex flex-row align-items-center mb-4">
                                    <i class = "fas fa-envelope fa-lg me-3 fa-fw"> </i>
                                    <div class = "form-outline flex-fill mb-0">
                                        <input type = "text" id = "form3Example3c" name="name" value="<c:out value="${requestScope.activity.activity}"/>" class = "form-control" />
                                        <label class = "form-label" for = "form3Example3c"><fmt:message key="name" bundle="${lang}"/></label>
                                    </div>
                                </div>
                                <div class = "d-flex flex-row align-items-center mb-4">
                                    <i class = "fas fa-lock fa-lg me-3 fa-fw"> </i>
                                    <div class = "form-outline flex-fill mb-0">
                                        <input type = "password" id = "form3Example4c" name="category" value="<c:out value="${requestScope.activity.category}"/>" class = "form-control" />
                                        <label class = "form-label" for = "form3Example4c"><fmt:message key="category" bundle="${lang}"/></label>
                                    </div>
                                </div>
                                <div class = "d-flex flex-row align-items-center mb-4">
                                    <i class = "fas fa-lock fa-lg me-3 fa-fw"> </i>
                                    <div class = "form-outline flex-fill mb-0">
                                        <button class="btn btn-outline-success" type="submit"><fmt:message key="edit" bundle="${lang}"/></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>