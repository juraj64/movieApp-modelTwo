package com.jurajlazovy.movies.serviceapi;

import com.jurajlazovy.movies.serviceapi.MovieService;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class MovieServiceTest extends AbstractDbUnitJpaTests implements MovieServiceTestBase {

	@Autowired
	protected MovieService movieService;

	@Test
	public void testFindMoviesByCondition() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindMoviesByCondition not implemented");
	}

	@Test
	public void testFindMoviesByDirector() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindMoviesByDirector not implemented");
	}

	@Test
	public void testFindMoviesByDirectorName() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindMoviesByDirectorName not implemented");
	}

	@Test
	public void testFindMoviesByLanguages() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindMoviesByLanguages not implemented");
	}

	@Test
	public void testCountMoviesByDirectors() throws Exception {
		// TODO Auto-generated method stub
		fail("testCountMoviesByDirectors not implemented");
	}

	@Test
	public void testFindByCondition() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindByCondition not implemented");
	}

	@Test
	public void testChangeDirector() throws Exception {
		// TODO Auto-generated method stub
		fail("testChangeDirector not implemented");
	}

	@Test
	public void testFindById() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindById not implemented");
	}

	@Test
	public void testFindAll() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindAll not implemented");
	}

	@Test
	public void testSave() throws Exception {
		// TODO Auto-generated method stub
		fail("testSave not implemented");
	}

	@Test
	public void testDelete() throws Exception {
		// TODO Auto-generated method stub
		fail("testDelete not implemented");
	}
}
