package com.jurajlazovy.web.rest;

import com.jurajlazovy.movies.domain.*;


import com.jurajlazovy.movies.serviceapi.ActorService;
import com.jurajlazovy.movies.serviceapi.DirectorService;
import com.jurajlazovy.movies.serviceapi.MovieActorService;
import com.jurajlazovy.movies.serviceapi.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of MovieResource.
 */
@Controller
public class MovieResource extends MovieResourceBase {

    public MovieResource() {
    }

    @Autowired
    private DirectorService directorService;
    @Autowired
    private ActorService actorService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieActorService movieActorService;

    @RequestMapping(value = "/movie/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        Movie entity = new Movie();
        modelMap.addAttribute("entity", entity);
        return "movie/create";
    }

    // Doplnena overwritnuta metoda pre vytvorenie noveho movie
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public String create(@RequestBody Movie entity) {

        Long directorId = entity.getDirector().getId(); // moznost nacitat directora podla id

        if (directorId != null) {
            try {
                Director director = directorService.findById(serviceContext(), directorId);
                entity.setDirector(director);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.create(entity);
    }

    // spustenie metody findMoviesByCondition. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/movie/find", method = RequestMethod.POST)
    public String find(@RequestBody Movie entity) {

        List<Movie> results = movieService.findMoviesByCondition(serviceContext());

        System.out.println("There are " + results.size() + " movies in our db. In alphabetic order: ");
        for (Movie movie : results) {
            System.out.println("  " + movie.getMovieName() +
                    ", directed by " + movie.getDirector().getFirstName() +
                    " " + movie.getDirector().getLastName());
        }

        return String.format("redirect:/rest/movie/%s", results);
    }

    // spustenie metody findMoviesByDirector (pre jedneho directora). Dáta pomocou curl prikazu.    // Pre jedneho directora
    @RequestMapping(value = "/movie/finddirector", method = RequestMethod.POST)
    public String finddirector(@RequestBody Movie entity) {

        Long directorId = entity.getDirector().getId(); // moznost nacitat directora podla id

        if (directorId != null) {
            try {
                Director director = directorService.findById(serviceContext(), directorId);
                entity.setDirector(director);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Movie> results = movieService.findMoviesByDirector(serviceContext(), entity.getDirector());

        System.out.println("Director " + entity.getDirector().getFirstName() +
                " " + entity.getDirector().getLastName() +
                " has " + results.size() + " movie(s) in our db: ");
        for (Movie movie : results) {
            System.out.println("  " + movie.getMovieName() +
                    ", released at " + movie.getReleaseDate());
        }

        return String.format("redirect:/rest/movie/%s", results);
    }

    // FindMoviesByDirectors (pre vsetkych directors a este zoradenych podla mena).
    // Vyuzijem existujuce metody findMoviesByDirector a findAll
    // Dáta pomocou curl prikazu.
    @RequestMapping(value = "/movie/findmoviesbydirectors", method = RequestMethod.POST)
    public String findMoviesByDirectors(@RequestBody Movie entity) {

        List<Movie> result = new ArrayList<>();
        List<Director> directors = directorService.findAll(serviceContext());

        for(Director director : directors) {
            result = movieService.findMoviesByDirector(serviceContext(), director);

            System.out.println("Director " + director.getFirstName() +
                    " " + director.getLastName() +
                    " has " + result.size() + " movie(s) in our db: ");
        }

        return String.format("redirect:/rest/movie/%s", result);
    }

    // spustenie metody findMoviesByActor. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/movie/findactors", method = RequestMethod.POST)
    public String findActors(@RequestBody Movie entity) throws ParseException {

        String movieName = entity.getMovieName();
        Date date = entity.getReleaseDate();

        // transformacia date na potrebny format yyyy-MM-dd, aby to metoda dole vedela precitat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String strDate = sdf.format(date);
        Date releaseDate = sdf.parse(strDate);

        List<MovieActor> result = movieActorService.findActorsInMovie(serviceContext(), movieName, releaseDate);

        System.out.println("Movie " + movieName + " stars with ");
        for (MovieActor movAct : result) {
            System.out.println(" " + movAct.getActor().getFirstName() +
                    " " + movAct.getActor().getLastName());
        }

        return String.format("redirect:/rest/movie/%s", result);
    }

    // spustenie metody findMoviesByLanguages. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/movie/findbylang", method = RequestMethod.POST)
    public String findByLang(@RequestBody Movie entity) {

        List<Movie> result = movieService.findMoviesByLanguages(serviceContext());

        System.out.println("All movies in selected languages: ");
        for (Movie movie : result) {
            System.out.println(" " + movie.getMovieName()
                            + ", released at " + movie.getReleaseDate()
                    + " in " + movie.getMovieLang() + " language "
                    + ", directed by " + movie.getDirector().getFirstName()
                    + " " + movie.getDirector().getLastName());
        }

        return String.format("redirect:/rest/movie/%s", result);
    }

    // zmena directora (jeho first a lastName) pre movie najdeneho podla nazvu a releaseDate
    @RequestMapping(value = "/movie/newdirector", method = RequestMethod.POST)
    public String newDirector(@RequestBody Movie entity) throws ParseException {

        String movieName = entity.getMovieName();
        Date date = entity.getReleaseDate();

        // transformacia date na potrebny format yyyy-MM-dd, aby to metoda dole vedela precitat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String strDate = sdf.format(date);
        Date releaseDate = sdf.parse(strDate);

        movieService.changeDirector(serviceContext(), movieName, releaseDate);

        return String.format("redirect:/rest/movie/%s", movieName);
    }
}
