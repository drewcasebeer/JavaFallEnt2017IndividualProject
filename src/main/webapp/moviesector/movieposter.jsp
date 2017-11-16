<%--
  Created by IntelliJ IDEA.
  User: drewcasebeer
  Date: 10/25/17
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div data-movieid="${requestScope.movie.id}" data-year="${requestScope.movie.releaseDate}" class="movie-wrapper">
    <img class="movie-poster" src="https://image.tmdb.org/t/p/w640/${requestScope.movie.posterPath}">
    <div class="movie-controls-top">${requestScope.movie.title}</div>
    <div class="movie-controls-bottom">
        <span class="movie-score" data-value=0>0</span>
        <i class="fa fa-thumbs-o-down" aria-hidden="true"></i>
        <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
    </div>
</div>
