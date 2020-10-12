package com.jurajlazovy.movies.serviceapi;

/**
 * Definition of test methods to implement.
 */
public interface MovieActorServiceTestBase {

	public void testFindMoviesActorsByCondition() throws Exception;

	public void testFindMoviesByActor() throws Exception;

	public void testFindActorsInMovie() throws Exception;

	public void testFindMoviesByActorId() throws Exception;

	public void testFindActorsByMovieId() throws Exception;

	public void testFindByCondition() throws Exception;

	public void testChangeActor() throws Exception;

	public void testChangeMovie() throws Exception;

	public void testFindById() throws Exception;

	public void testFindAll() throws Exception;

	public void testSave() throws Exception;

	public void testDelete() throws Exception;
}
