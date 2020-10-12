package com.jurajlazovy.movies.serviceapi;

/**
 * Definition of test methods to implement.
 */
public interface MovieServiceTestBase {

	public void testFindMoviesByCondition() throws Exception;

	public void testFindMoviesByDirector() throws Exception;

	public void testFindMoviesByDirectorName() throws Exception;

	public void testFindMoviesByLanguages() throws Exception;

	public void testCountMoviesByDirectors() throws Exception;

	public void testFindByCondition() throws Exception;

	public void testChangeDirector() throws Exception;

	public void testFindById() throws Exception;

	public void testFindAll() throws Exception;

	public void testSave() throws Exception;

	public void testDelete() throws Exception;
}
