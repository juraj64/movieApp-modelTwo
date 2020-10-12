package com.jurajlazovy.movies.serviceimpl;

import com.jurajlazovy.movies.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.jurajlazovy.movies.domain.Gender.F;
import static com.jurajlazovy.movies.domain.Gender.M;
import static java.sql.Date.valueOf;

/**
 * Implementation of MovieActorService.
 */
@Service("movieActorService")
public class MovieActorServiceImpl extends MovieActorServiceImplBase {

    private static final Scanner scanner = new Scanner(System.in);

    public MovieActorServiceImpl() {
    }

    @Autowired
    MovieActorRepository movieActorRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    MovieRepository movieRepository;

    public List<MovieActor> findMoviesActorsByCondition(ServiceContext ctx) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieActor.class)
                .withProperty(MovieActorProperties.actor().gender()).eq(F).build(); // movies with female actors

        return movieActorRepository.findByCondition(criteria);
    }

    @Override
    public List<MovieActor> findMoviesByActor(ServiceContext ctx, String firstName, String lastName) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieActor.class)
                .withProperty(MovieActorProperties.actor().firstName()).eq(firstName)
                .withProperty(MovieActorProperties.actor().lastName()).eq(lastName)
                .orderBy(MovieActorProperties.movie().releaseDate()).build(); // zoradene podla movieName

        return movieActorRepository.findByCondition(criteria);
    }

    @Override
    public List<MovieActor> findActorsInMovie(ServiceContext ctx, String movieName, Date releaseDate) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieActor.class)
                .withProperty(MovieActorProperties.movie().movieName()).eq(movieName)
                .withProperty(MovieActorProperties.movie().releaseDate()).eq(releaseDate)
                .orderBy(MovieActorProperties.actor().lastName()).build(); // zoradene podla lastName

        return movieActorRepository.findByCondition(criteria);
    }

    @Override
    public List<MovieActor> findMoviesByActorId(ServiceContext ctx, Actor actor) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieActor.class)
                .withProperty(MovieActorProperties.actor()).eq(actor)
                .orderBy(MovieActorProperties.movie().releaseDate()).build(); // zoradene podla movieName

        return movieActorRepository.findByCondition(criteria);
    }

    @Override
    public List<MovieActor> findActorsByMovieId(ServiceContext ctx, Movie movie) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieActor.class)
                .withProperty(MovieActorProperties.movie()).eq(movie)
                .orderBy(MovieActorProperties.actor().lastName()).build(); // zoradene podla lastName

        return movieActorRepository.findByCondition(criteria);
    }

    @Override // Toto nefunguje, lebo nevie nacitat vstupne hodnoty do entity.
    // Pozri metodu newMovie v MovieActorResource
    public MovieActor changeActor(ServiceContext ctx, Movie movie, Actor actor) {
        // najdenie daneho movieActor
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieActor.class)
                .withProperty(MovieActorProperties.movie()).eq(movie)
                .withProperty(MovieActorProperties.actor()).eq(actor).build();
        List<MovieActor> movActs = movieActorRepository.findByCondition(criteria);

        // pociatocne hodnoty
        MovieActor myMovAct;
        Actor oldActor;
        long newActorId;
        Actor newActor;

        // vymena actora
        if (movActs.isEmpty()) {
            System.out.println("There is no such combination of movie and actor in DB.");
            return null;
        } else {
            myMovAct = movActs.get(0);
            oldActor = myMovAct.getActor();
            System.out.println("Enter new actor ID: ");
            try {
                newActorId = Long.parseLong(scanner.next());
                newActor = actorRepository.findById(newActorId);
                myMovAct.setActor(newActor);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        System.out.println("We have changed actor in movie " + myMovAct.getMovie().getMovieName()
                + " from " + oldActor.getFirstName() + " " + oldActor.getLastName()
                + " to " + newActor.getFirstName() + " " + newActor.getLastName() + ".");

        return myMovAct;
    }

    @Override
    public MovieActor changeMovie(ServiceContext ctx, String movieName, Date releaseDate, String firstName, String lastName) {
        // najdenie daneho movieActor
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieActor.class)
                .withProperty(MovieActorProperties.movie().movieName()).eq(movieName)
                .withProperty(MovieActorProperties.movie().releaseDate()).eq(releaseDate)
                .withProperty(MovieActorProperties.actor().firstName()).eq(firstName)
                .withProperty(MovieActorProperties.actor().lastName()).eq(lastName).build();
        List<MovieActor> movActs = movieActorRepository.findByCondition(criteria);

        // pociatocne hodnoty
        MovieActor myMovAct;
        Actor actor;
        Movie oldMovie;
        String newMovieName;
        Date newReleaseDate = new Date();
        Movie newMovie;

        // data o novom movie
        if (movActs.isEmpty()) {
            System.out.println("There is no such combination of movie and actor in DB.");
            return null;
        } else {
            myMovAct = movActs.get(0);
            actor = myMovAct.getActor();
            oldMovie = myMovAct.getMovie();
            System.out.println("Enter new movie name: ");
            newMovieName = scanner.next();

            System.out.println("Enter release date of the new movie in format yyyy-MM-dd: ");
            try {
                newReleaseDate = valueOf(scanner.next());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // najdenie movie podla mena release date
            List<ConditionalCriteria> criteriaTwo = ConditionalCriteriaBuilder.criteriaFor(Movie.class)
                    .withProperty(MovieProperties.movieName()).eq(newMovieName)
                    .withProperty(MovieProperties.releaseDate()).eq(newReleaseDate).build();
            List<Movie> movies = movieRepository.findByCondition(criteriaTwo);

            // nasetupovanie nove movie
            if (movies.isEmpty()) {
                System.out.println("No movie with this name and/or release date in DB.");
                return null;
            } else {
                newMovie = movies.get(0);
                myMovAct.setMovie(newMovie);
            }
        }

        System.out.println("We have changed movie for the actor "
                + actor.getFirstName() + " " + actor.getLastName()
                + " from " + oldMovie.getMovieName() + ", released at " + oldMovie.getReleaseDate()
                + " to " + newMovie.getMovieName() + ", released at " + newMovie.getReleaseDate() + ".");

        return myMovAct;
    }


}
