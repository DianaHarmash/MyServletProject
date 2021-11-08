<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">    <title>Title</title>
</head>
<body style="margin-left: 20px; margin-top: 16px">
    <h1 class="text">403</h1>
    <i class="text">Error <%= exception %></i>
    <h4 class="text" <%--th:text="${locale.getLocale().getString('forbidden')}"--%>></h4>
    <a class="link" href="${pageContext.request.contextPath}/index.jsp">Index</a>
</body>
</html>