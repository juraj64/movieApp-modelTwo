package com.jurajlazovy.movies.serviceapi;

import com.jurajlazovy.movies.domain.MovieRevenue;
import com.jurajlazovy.movies.exception.MovieRevenueNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service MovieRevenueService.
 */
public interface MovieRevenueService {

	public final static String BEAN_ID = "movieRevenueService";

	public List<MovieRevenue> findMovieRevenuesByCondition(ServiceContext ctx);

	public List<MovieRevenue> findInternationalMovieRevenues(ServiceContext ctx, int internationalTakings);

	public List<MovieRevenue> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition);

	public MovieRevenue findById(ServiceContext ctx, Long id) throws MovieRevenueNotFoundException;

	public List<MovieRevenue> findAll(ServiceContext ctx);

	public MovieRevenue save(ServiceContext ctx, MovieRevenue entity);

	public void delete(ServiceContext ctx, MovieRevenue entity);

}
