package com.jurajlazovy.movies.domain;

import com.jurajlazovy.movies.domain.Actor;
import com.jurajlazovy.movies.domain.Movie;
import com.jurajlazovy.movies.domain.MovieActor;
import java.util.Date;

/**
 * Builder for MovieActor class.
 */
public class MovieActorBuilder {

	private Date createdDate;
	private String createdBy;
	private Date lastUpdated;
	private String lastUpdatedBy;

	private Movie movie;
	private Actor actor;

	/**
	 * Static factory method for MovieActorBuilder
	 */
	public static MovieActorBuilder movieActor() {
		return new MovieActorBuilder();
	}

	public MovieActorBuilder() {
	}

	public MovieActorBuilder createdDate(Date val) {
		this.createdDate = val;
		return this;
	}

	public MovieActorBuilder createdBy(String val) {
		this.createdBy = val;
		return this;
	}

	public MovieActorBuilder lastUpdated(Date val) {
		this.lastUpdated = val;
		return this;
	}

	public MovieActorBuilder lastUpdatedBy(String val) {
		this.lastUpdatedBy = val;
		return this;
	}

	public MovieActorBuilder movie(Movie movie) {
		this.movie = movie;
		return this;
	}

	public MovieActorBuilder actor(Actor actor) {
		this.actor = actor;
		return this;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public Movie getMovie() {
		return movie;
	}

	public Actor getActor() {
		return actor;
	}

	/**
	 * @return new MovieActor instance constructed based on the values that have been set into this builder
	 */
	public MovieActor build() {
		MovieActor obj = new MovieActor();
		obj.setCreatedDate(createdDate);
		obj.setCreatedBy(createdBy);
		obj.setLastUpdated(lastUpdated);
		obj.setLastUpdatedBy(lastUpdatedBy);
		obj.setMovie(movie);
		obj.setActor(actor);

		return obj;
	}
}
