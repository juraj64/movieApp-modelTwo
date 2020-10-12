package com.jurajlazovy.movies.serviceapi;

import com.jurajlazovy.movies.domain.Actor;
import com.jurajlazovy.movies.domain.Movie;
import com.jurajlazovy.movies.domain.MovieActor;
import com.jurajlazovy.movies.exception.MovieActorNotFoundException;
import java.util.Date;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service MovieActorService.
 */
public interface MovieActorService {

	public final static String BEAN_ID = "movieActorService";

	public List<MovieActor> findMoviesActorsByCondition(ServiceContext ctx);

	public List<MovieActor> findMoviesByActor(ServiceContext ctx, String firstName, String lastName);

	public List<MovieActor> findActorsInMovie(ServiceContext ctx, String movieName, Date releaseDate);

	public List<MovieActor> findMoviesByActorId(ServiceContext ctx, Actor actor);

	public List<MovieActor> findActorsByMovieId(ServiceContext ctx, Movie movie);

	public List<MovieActor> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition);

	public MovieActor changeActor(ServiceContext ctx, Movie movie, Actor actor);

	public MovieActor changeMovie(ServiceContext ctx, String movieName, Date releaseDate, String firstName, String lastName);

	public MovieActor findById(ServiceContext ctx, Long id) throws MovieActorNotFoundException;

	public List<MovieActor> findAll(ServiceContext ctx);

	public MovieActor save(ServiceContext ctx, MovieActor entity);

	public void delete(ServiceContext ctx, MovieActor entity);

}
