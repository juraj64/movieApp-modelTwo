package com.jurajlazovy.movies.repositoryimpl;

import com.jurajlazovy.movies.domain.MovieActor;
import com.jurajlazovy.movies.domain.MovieActorRepository;
import com.jurajlazovy.movies.exception.MovieActorNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.DeleteAccess;
import org.sculptor.framework.accessapi.FindAllAccess2;
import org.sculptor.framework.accessapi.FindByConditionAccess2;
import org.sculptor.framework.accessapi.FindByIdAccess;
import org.sculptor.framework.accessapi.SaveAccess;
import org.sculptor.framework.accessimpl.jpa.JpaDeleteAccessImpl;
import org.sculptor.framework.accessimpl.jpa.JpaFindAllAccessImplGeneric;
import org.sculptor.framework.accessimpl.jpa.JpaFindByConditionAccessImplGeneric;
import org.sculptor.framework.accessimpl.jpa.JpaFindByIdAccessImpl;
import org.sculptor.framework.accessimpl.jpa.JpaSaveAccessImpl;
import org.springframework.stereotype.Repository;

/**
 * Repository implementation for MovieActor
 */
@Repository("movieActorRepository")
public class MovieActorRepositoryImpl implements MovieActorRepository {

	public MovieActorRepositoryImpl() {
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindByConditionAccess}
	 */
	public List<MovieActor> findByCondition(List<ConditionalCriteria> condition) {
		return findByCondition(condition, getPersistentClass());
	}

	public <R> List<R> findByCondition(List<ConditionalCriteria> condition, Class<R> resultType) {

		FindByConditionAccess2<R> ao = createFindByConditionAccess(resultType);

		ao.setCondition(condition);
		ao.execute();
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindByIdAccess}
	 */
	public MovieActor findById(Long id) throws MovieActorNotFoundException {

		FindByIdAccess<MovieActor, Long> ao = createFindByIdAccess();

		ao.setId(id);
		ao.execute();
		if (ao.getResult() == null) {
			throw new MovieActorNotFoundException("No MovieActor found with id: " + id);
		}
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.FindAllAccess}
	 */
	public List<MovieActor> findAll() {
		return findAll(getPersistentClass());
	}

	public <R> List<R> findAll(Class<R> resultType) {

		FindAllAccess2<R> ao = createFindAllAccess(resultType);

		ao.execute();
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.SaveAccess}
	 */
	public MovieActor save(MovieActor entity) {

		SaveAccess<MovieActor> ao = createSaveAccess();

		ao.setEntity(entity);
		ao.execute();
		return ao.getResult();
	}

	/**
	 * Delegates to {@link org.sculptor.framework.accessapi.DeleteAccess}
	 */
	public void delete(MovieActor entity) {

		DeleteAccess<MovieActor> ao = createDeleteAccess();

		ao.setEntity(entity);
		ao.execute();
	}

	@PersistenceContext(unitName = "MovieAppTwoEntityManagerFactory")
	private EntityManager entityManager;

	/**
	 * Dependency injection
	 */
	@PersistenceContext(unitName = "MovieAppTwoEntityManagerFactory")
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	// convenience method
	protected FindByConditionAccess2<MovieActor> createFindByConditionAccess() {
		return createFindByConditionAccess(getPersistentClass(), getPersistentClass());
	}

	// convenience method
	protected <R> FindByConditionAccess2<R> createFindByConditionAccess(Class<R> resultType) {
		return createFindByConditionAccess(getPersistentClass(), resultType);
	}

	protected <T, R> FindByConditionAccess2<R> createFindByConditionAccess(Class<T> type, Class<R> resultType) {
		JpaFindByConditionAccessImplGeneric<T, R> ao = new JpaFindByConditionAccessImplGeneric<T, R>(type, resultType);
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected FindByIdAccess<MovieActor, Long> createFindByIdAccess() {
		JpaFindByIdAccessImpl<MovieActor, Long> ao = new JpaFindByIdAccessImpl<MovieActor, Long>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	// convenience method
	protected FindAllAccess2<MovieActor> createFindAllAccess() {
		return createFindAllAccess(getPersistentClass(), getPersistentClass());
	}

	// convenience method
	protected <R> FindAllAccess2<R> createFindAllAccess(Class<R> resultType) {
		return createFindAllAccess(getPersistentClass(), resultType);
	}

	protected <T, R> FindAllAccess2<R> createFindAllAccess(Class<T> type, Class<R> resultType) {
		JpaFindAllAccessImplGeneric<T, R> ao = new JpaFindAllAccessImplGeneric<T, R>(type, resultType);
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected SaveAccess<MovieActor> createSaveAccess() {
		JpaSaveAccessImpl<MovieActor> ao = new JpaSaveAccessImpl<MovieActor>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected DeleteAccess<MovieActor> createDeleteAccess() {
		JpaDeleteAccessImpl<MovieActor> ao = new JpaDeleteAccessImpl<MovieActor>(getPersistentClass());
		ao.setEntityManager(getEntityManager());
		return ao;
	}

	protected Class<MovieActor> getPersistentClass() {
		return MovieActor.class;
	}

}
