package sample.controller;

import com.google.gson.Gson;
import sample.entity.MoviesEntity;
import sample.persistence.MoviesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeMap;

@WebServlet("/upsertmovie")
public class UpsertMovie extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        TreeMap<String, Object> output = new TreeMap<String, Object>();

        int movieId = Integer.valueOf(request.getParameter("movieId"));
        String movieName = request.getParameter("movieName");
        int score = Integer.valueOf(request.getParameter("score"));
        String year = request.getParameter("year");

        MoviesDao md = new MoviesDao();

        MoviesEntity me = md.getMovie(movieId);

        if(me == null) {
            me = new MoviesEntity();
            me.setId(movieId);
            me.setName(movieName);
            me.setVotes(score);
            me.setYear(year);

            md.insert(me);
            output.put("action", "insert successful");
        } else {
            me = new MoviesEntity();
            me.setId(movieId);
            me.setName(movieName);
            me.setVotes(score);
            me.setYear(year);

            md.update(me);
            output.put("action", "update successful");
        }

        output.put("record", me);

        String json = new Gson().toJson(output);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
