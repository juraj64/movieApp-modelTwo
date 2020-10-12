package com.jurajlazovy.movies.serviceimpl;

import com.jurajlazovy.movies.domain.MovieActor;
import com.jurajlazovy.movies.domain.MovieActorRepository;
import com.jurajlazovy.movies.exception.MovieActorNotFoundException;
import com.jurajlazovy.movies.serviceapi.MovieActorService;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generated base class for implementation of MovieActorService.
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 * @org.springframework.stereotype.Service("movieActorService")
 * </pre>
 *
 */
public abstract class MovieActorServiceImplBase implements MovieActorService {

	public MovieActorServiceImplBase() {
	}

	@Autowired
	private MovieActorRepository movieActorRepository;

	protected MovieActorRepository getMovieActorRepository() {
		return movieActorRepository;
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieActorRepository#findByCondition}
	 */
	public List<MovieActor> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) {
		return movieActorRepository.findByCondition(condition);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieActorRepository#findById}
	 */
	public MovieActor findById(ServiceContext ctx, Long id) throws MovieActorNotFoundException {
		return movieActorRepository.findById(id);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieActorRepository#findAll}
	 */
	public List<MovieActor> findAll(ServiceContext ctx) {
		return movieActorRepository.findAll();
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieActorRepository#save}
	 */
	public MovieActor save(ServiceContext ctx, MovieActor entity) {
		return movieActorRepository.save(entity);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.movies.domain.MovieActorRepository#delete}
	 */
	public void delete(ServiceContext ctx, MovieActor entity) {
		movieActorRepository.delete(entity);
	}

}
