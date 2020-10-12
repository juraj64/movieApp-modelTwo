package com.jurajlazovy.movies.domain;

import com.jurajlazovy.movies.domain.MovieRevenue;
import com.jurajlazovy.movies.exception.MovieRevenueNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;

/**
 * Generated interface for Repository for MovieRevenue
 */
public interface MovieRevenueRepository {

	public final static String BEAN_ID = "movieRevenueRepository";

	public List<MovieRevenue> findByCondition(List<ConditionalCriteria> condition);

	public MovieRevenue findById(Long id) throws MovieRevenueNotFoundException;

	public List<MovieRevenue> findAll();

	public MovieRevenue save(MovieRevenue entity);

	public void delete(MovieRevenue entity);

}
