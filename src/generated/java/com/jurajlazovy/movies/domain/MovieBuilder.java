package com.jurajlazovy.movies.domain;

import com.jurajlazovy.movies.domain.Director;
import com.jurajlazovy.movies.domain.Movie;
import java.util.Date;

/**
 * Builder for Movie class.
 */
public class MovieBuilder {

	private String movieName;
	private int movieLength;
	private String movieLang;
	private Date releaseDate;
	private String ageCertificate;
	private Date createdDate;
	private String createdBy;
	private Date lastUpdated;
	private String lastUpdatedBy;

	private Director director;

	/**
	 * Static factory method for MovieBuilder
	 */
	public static MovieBuilder movie() {
		return new MovieBuilder();
	}

	public MovieBuilder() {
	}

	public MovieBuilder movieName(String val) {
		this.movieName = val;
		return this;
	}

	public MovieBuilder movieLength(int val) {
		this.movieLength = val;
		return this;
	}

	public MovieBuilder movieLang(String val) {
		this.movieLang = val;
		return this;
	}

	public MovieBuilder releaseDate(Date val) {
		this.releaseDate = val;
		return this;
	}

	public MovieBuilder ageCertificate(String val) {
		this.ageCertificate = val;
		return this;
	}

	public MovieBuilder createdDate(Date val) {
		this.createdDate = val;
		return this;
	}

	public MovieBuilder createdBy(String val) {
		this.createdBy = val;
		return this;
	}

	public MovieBuilder lastUpdated(Date val) {
		this.lastUpdated = val;
		return this;
	}

	public MovieBuilder lastUpdatedBy(String val) {
		this.lastUpdatedBy = val;
		return this;
	}

	public MovieBuilder director(Director director) {
		this.director = director;
		return this;
	}

	public String getMovieName() {
		return movieName;
	}

	public int getMovieLength() {
		return movieLength;
	}

	public String getMovieLang() {
		return movieLang;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getAgeCertificate() {
		return ageCertificate;
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

	public Director getDirector() {
		return director;
	}

	/**
	 * @return new Movie instance constructed based on the values that have been set into this builder
	 */
	public Movie build() {
		Movie obj = new Movie();
		obj.setMovieName(movieName);
		obj.setMovieLength(movieLength);
		obj.setMovieLang(movieLang);
		obj.setReleaseDate(releaseDate);
		obj.setAgeCertificate(ageCertificate);
		obj.setCreatedDate(createdDate);
		obj.setCreatedBy(createdBy);
		obj.setLastUpdated(lastUpdated);
		obj.setLastUpdatedBy(lastUpdatedBy);
		obj.setDirector(director);

		return obj;
	}
}
