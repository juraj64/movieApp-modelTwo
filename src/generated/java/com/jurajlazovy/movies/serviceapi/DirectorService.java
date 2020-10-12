package com.jurajlazovy.movies.serviceapi;

import com.jurajlazovy.movies.domain.Director;
import com.jurajlazovy.movies.exception.DirectorNotFoundException;
import com.jurajlazovy.movies.exception.ParseException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service DirectorService.
 */
public interface DirectorService {

	public final static String BEAN_ID = "directorService";

	public List<Director> findDirectorsByCondition(ServiceContext ctx) throws ParseException, java.text.ParseException;

	public List<Director> findDirectorsByNationality(ServiceContext ctx, String nationality);

	public List<Director> findAmericanDirectors(ServiceContext ctx);

	public List<Director> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition);

	public String changeNationality(ServiceContext ctx, String firstName, String lastName);

	public Director findById(ServiceContext ctx, Long id) throws DirectorNotFoundException;

	public List<Director> findAll(ServiceContext ctx);

	public Director save(ServiceContext ctx, Director entity);

	public void delete(ServiceContext ctx, Director entity);

}
