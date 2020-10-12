package com.jurajlazovy.web.rest;

import com.jurajlazovy.movies.domain.Director;
import com.jurajlazovy.movies.domain.Movie;
import com.jurajlazovy.movies.domain.MovieActor;
import com.jurajlazovy.movies.domain.MovieRevenue;
import com.jurajlazovy.movies.serviceapi.MovieRevenueService;
import com.jurajlazovy.movies.serviceapi.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Implementation of MovieRevenueResource.
 */
@Controller
public class MovieRevenueResource extends MovieRevenueResourceBase {

    public MovieRevenueResource() {
    }

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRevenueService revenueService;

    @RequestMapping(value = "/movieRevenue/form", method = RequestMethod.GET)
    public String createForm(ModelMap modelMap) {
        MovieRevenue entity = new MovieRevenue();
        modelMap.addAttribute("entity", entity);
        return "movieRevenue/create";
    }

    // Doplnena overwritnuta metoda pre vytvorenie noveho movieRevenue
    @RequestMapping(value = "/movieRevenue", method = RequestMethod.POST)
    public String create(@RequestBody MovieRevenue entity) {

        List<MovieRevenue> revenues = revenueService.findAll(serviceContext());

        Long movieId = entity.getMovie().getId(); // moznost nacitat movie podla id

        for (MovieRevenue revenue : revenues) { // kontrola, ci uz neexistuje movie revenue pre toto movie
            if (revenue.getMovie().getId().equals(movieId)) {
                System.out.println("Revenue for movie with id = " + movieId + " has already been set up.");
                return null;
            }
        }

        // moznost nacitat movie podla id
        if (movieId != null) {
            try {
                Movie movie = movieService.findById(serviceContext(), movieId);
                entity.setMovie(movie);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.create(entity);
    }

    // spustenie metody findMovieRevenuesByCondition. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/movieRevenue/find", method = RequestMethod.POST)
    public String find(@RequestBody MovieRevenue entity) {

        List<MovieRevenue> results = revenueService.findMovieRevenuesByCondition(serviceContext());

        System.out.println("All movies in our db ordered by domestic takings: ");
        for (MovieRevenue revenue : results) {
            System.out.println("  " + revenue.getMovie().getMovieName() +
                    ", has domestic takings " + revenue.getDomesticTakings() +
                    " and its international takings are " + revenue.getInternationalTakings());
        }

        return String.format("redirect:/rest/movieRevenue/%s", results);
    }

    // spustenie metody findInternationalMovieRevenues. Dáta pomocou curl prikazu.
    @RequestMapping(value = "/movieRevenue/findinternational", method = RequestMethod.POST)
    public String findinternational(@RequestBody MovieRevenue entity) {

        int international = entity.getInternationalTakings();
        List<MovieRevenue> results = revenueService.findInternationalMovieRevenues(serviceContext(), international);

        System.out.println("All movies in our db with international takings greater than " +
                international + ":");
        for (MovieRevenue revenue : results) {
            System.out.println("  " + revenue.getMovie().getMovieName() +
                    " and its international takings are " + revenue.getInternationalTakings());
        }

        return String.format("redirect:/rest/movieRevenue/%s", results);
    }

}
