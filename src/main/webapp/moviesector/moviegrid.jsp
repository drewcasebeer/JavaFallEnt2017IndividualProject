<%--
  Created by IntelliJ IDEA.
  User: drewcasebeer
  Date: 10/25/17
  Time: 9:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="<c:url value="/js/movielist.js" />"></script>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

<link type="text/css" rel="stylesheet" href="<c:url value="/css/movielist.css" />" />
<link type="text/css" rel="stylesheet" href="<c:url value="/css/movieposter.css" />" />
<%@ include file="../navigation.jsp" %>

<div class="list-wrapper">
    <c:forEach items="${searchedMovies}" var="movie">
        <c:set value="${movie}" var="movie" scope="request" />
        <c:import url="movieposter.jsp" />
    </c:forEach>
</div>
