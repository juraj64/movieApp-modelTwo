package com.jurajlazovy.movies.serviceimpl;

import com.jurajlazovy.movies.domain.MovieRevenue;
import com.jurajlazovy.movies.domain.MovieRevenueRepository;
import com.jurajlazovy.movies.exception.MovieRevenueNotFoundException;
import com.jurajlazovy.movies.serviceapi.MovieRevenueService;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generated base class for implementation of MovieRevenueService.
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 * @org.springframework.stereotype.Service("movieRevenueService")
 * </pre>
 *
 */
public abstract class MovieRevenueServiceImplBase implements MovieRevenueService {

	public MovieRevenueServiceImplBase() {
	}

	@Autowired
	private MovieRevenueRepository movieRevenueRepository;

	protected MovieRevenueRepository getMovieRevenueRepository() {
		return movieRevenueRepository;
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieRevenueRepository#findByCondition}
	 */
	public List<MovieRevenue> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) {
		return movieRevenueRepository.findByCondition(condition);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieRevenueRepository#findById}
	 */
	public MovieRevenue findById(ServiceContext ctx, Long id) throws MovieRevenueNotFoundException {
		return movieRevenueRepository.findById(id);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieRevenueRepository#findAll}
	 */
	public List<MovieRevenue> findAll(ServiceContext ctx) {
		return movieRevenueRepository.findAll();
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieRevenueRepository#save}
	 */
	public MovieRevenue save(ServiceContext ctx, MovieRevenue entity) {
		return movieRevenueRepository.save(entity);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieRevenueRepository#delete}
	 */
	public void delete(ServiceContext ctx, MovieRevenue entity) {
		movieRevenueRepository.delete(entity);
	}

}
