package com.jurajlazovy.movies.domain;

import com.jurajlazovy.movies.domain.MovieActor;
import com.jurajlazovy.movies.exception.MovieActorNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;

/**
 * Generated interface for Repository for MovieActor
 */
public interface MovieActorRepository {

	public final static String BEAN_ID = "movieActorRepository";

	public List<MovieActor> findByCondition(List<ConditionalCriteria> condition);

	public MovieActor findById(Long id) throws MovieActorNotFoundException;

	public List<MovieActor> findAll();

	public MovieActor save(MovieActor entity);

	public void delete(MovieActor entity);

}
