package sample.controller;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/movielisttest")
public class MovieList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        TmdbMovies movies = new TmdbApi("67ee7e99d1b70447a3e97c6f3e4c2b88").getMovies();
        MovieDb movie = movies.getMovie(5353, "en");

        MovieResultsPage popularMovies = movies.getPopularMovies("en", 1);
        for(MovieDb mdb : popularMovies.getResults()) {
            out.println(mdb.getTitle());
        }


    }
}
