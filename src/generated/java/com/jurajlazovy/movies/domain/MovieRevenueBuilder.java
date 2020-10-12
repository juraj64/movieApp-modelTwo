package com.jurajlazovy.movies.domain;

import com.jurajlazovy.movies.domain.Movie;
import com.jurajlazovy.movies.domain.MovieRevenue;
import java.util.Date;

/**
 * Builder for MovieRevenue class.
 */
public class MovieRevenueBuilder {

	private int domesticTakings;
	private int internationalTakings;
	private Date createdDate;
	private String createdBy;
	private Date lastUpdated;
	private String lastUpdatedBy;

	private Movie movie;

	/**
	 * Static factory method for MovieRevenueBuilder
	 */
	public static MovieRevenueBuilder movieRevenue() {
		return new MovieRevenueBuilder();
	}

	public MovieRevenueBuilder() {
	}

	public MovieRevenueBuilder domesticTakings(int val) {
		this.domesticTakings = val;
		return this;
	}

	public MovieRevenueBuilder internationalTakings(int val) {
		this.internationalTakings = val;
		return this;
	}

	public MovieRevenueBuilder createdDate(Date val) {
		this.createdDate = val;
		return this;
	}

	public MovieRevenueBuilder createdBy(String val) {
		this.createdBy = val;
		return this;
	}

	public MovieRevenueBuilder lastUpdated(Date val) {
		this.lastUpdated = val;
		return this;
	}

	public MovieRevenueBuilder lastUpdatedBy(String val) {
		this.lastUpdatedBy = val;
		return this;
	}

	public MovieRevenueBuilder movie(Movie movie) {
		this.movie = movie;
		return this;
	}

	public int getDomesticTakings() {
		return domesticTakings;
	}

	public int getInternationalTakings() {
		return internationalTakings;
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

	/**
	 * @return new MovieRevenue instance constructed based on the values that have been set into this builder
	 */
	public MovieRevenue build() {
		MovieRevenue obj = new MovieRevenue();
		obj.setDomesticTakings(domesticTakings);
		obj.setInternationalTakings(internationalTakings);
		obj.setCreatedDate(createdDate);
		obj.setCreatedBy(createdBy);
		obj.setLastUpdated(lastUpdated);
		obj.setLastUpdatedBy(lastUpdatedBy);
		obj.setMovie(movie);

		return obj;
	}
}
