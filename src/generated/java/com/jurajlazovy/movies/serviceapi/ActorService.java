package com.jurajlazovy.movies.serviceapi;

import com.jurajlazovy.movies.domain.Actor;
import com.jurajlazovy.movies.exception.ActorNotFoundException;
import java.util.Date;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service ActorService.
 */
public interface ActorService {

	public final static String BEAN_ID = "actorService";

	public List<Actor> findActorsByCondition(ServiceContext ctx);

	public List<Actor> findActorsByBirth(ServiceContext ctx, Date dateOfBirth);

	public List<Actor> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition);

	public Actor changeGenderOrBirth(ServiceContext ctx, String firstName, String lastName);

	public Actor findById(ServiceContext ctx, Long id) throws ActorNotFoundException;

	public List<Actor> findAll(ServiceContext ctx);

	public Actor save(ServiceContext ctx, Actor entity);

	public void delete(ServiceContext ctx, Actor entity);

}
