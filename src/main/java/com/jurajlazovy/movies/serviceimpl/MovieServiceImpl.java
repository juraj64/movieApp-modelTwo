package com.jurajlazovy.movies.serviceimpl;

import com.jurajlazovy.movies.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


import com.jurajlazovy.movies.serviceapi.MovieService;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.jurajlazovy.movies.domain.Gender.M;
import static java.sql.Date.valueOf;

/**
 * Implementation of MovieService.
 */
@Service("movieService")
public class MovieServiceImpl extends MovieServiceImplBase {

    private static final Scanner scanner = new Scanner(System.in);

    public MovieServiceImpl() {
    }

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    DirectorRepository directorRepository;

    // tato metoda da vyselectovane Movies
    public List<Movie> findMoviesByCondition(ServiceContext ctx) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Movie.class)
                .orderBy(MovieProperties.movieName()).build(); // zorad vsetky podla movieName

        return movieRepository.findByCondition(criteria);
    }

    @Override
    public List<Movie> findMoviesByDirector(ServiceContext ctx, Director director) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Movie.class)
                .withProperty(MovieProperties.director()).eq(director) // filmy directora
                .orderBy(MovieProperties.movieName()).build(); // zoradene podla movieName

        return movieRepository.findByCondition(criteria);
    }

    @Override
    public List<Movie> findMoviesByDirectorName(ServiceContext ctx, String firstName, String lastName) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Movie.class)
                .withProperty(MovieProperties.director().firstName()).eq(firstName) // filmy directora
                .withProperty(MovieProperties.director().lastName()).eq(lastName)
                .orderBy(MovieProperties.movieName()).build(); // zoradene podla movieName

        return movieRepository.findByCondition(criteria);
    }

    @Override
    public List<Movie> findMoviesByLanguages(ServiceContext ctx) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Movie.class)
                .withProperty(MovieProperties.movieLang()).in("Czech", "English")
                .orderBy(MovieProperties.movieName()).build(); // zoradene podla movieName

        return movieRepository.findByCondition(criteria);
    }

    @Override
    public List<Movie> countMoviesByDirectors(ServiceContext ctx) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Movie.class)
                .withProperty(MovieProperties.director()).in("Czech", "English")
                .orderBy(MovieProperties.movieName()).build(); // zoradene podla movieName

        return movieRepository.findByCondition(criteria);
    }

    @Override
    public Movie changeDirector(ServiceContext ctx, String movieName, Date releaseDate) {
        // najdenie daneho movie
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Movie.class)
                .withProperty(MovieProperties.movieName()).eq(movieName)
                .withProperty(MovieProperties.releaseDate()).eq(releaseDate).build();
        List<Movie> movies = movieRepository.findByCondition(criteria);

        // pociatocne hodnoty
        Movie myMovie;
        Director oldDirector;
        Director newDirector = new Director();
        String newFirstName;
        String newLastName;

        // data o novom directorovi
        if (movies.isEmpty()) {
            System.out.println("No movie with this name and/or release date in DB.");
            return null;
        } else {
            myMovie = movies.get(0);
            oldDirector = myMovie.getDirector();
            System.out.println("Enter director new first name: ");
            newFirstName = scanner.next();
            System.out.println("Enter director new last name: ");
            newLastName = scanner.next();

            // najdenie directora podla mena
            List<ConditionalCriteria> criteriaTwo = ConditionalCriteriaBuilder.criteriaFor(Director.class)
                    .withProperty(DirectorProperties.firstName()).eq(newFirstName)
                    .withProperty(DirectorProperties.lastName()).eq(newLastName).build();
            List<Director> directors = directorRepository.findByCondition(criteriaTwo);

            // nasetupovanie noveho directora
            if (directors.isEmpty()) {
                System.out.println("No director with this name in DB.");
                return null;
            } else {
                newDirector = directors.get(0);
                myMovie.setDirector(newDirector);
            }
        }

        System.out.println("We have changed director of the movie " + movieName +
                ", release at " + movies.get(0).getReleaseDate() +
                ", from " + oldDirector.getFirstName() + " " + oldDirector.getLastName()
                + " to " + newDirector.getFirstName() + " " + newDirector.getLastName() + ".");

        return myMovie;
    }

}
