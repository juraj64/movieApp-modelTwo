package com.jurajlazovy.movies.serviceapi;

import com.jurajlazovy.movies.serviceapi.MovieActorService;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class MovieActorServiceTest extends AbstractDbUnitJpaTests implements MovieActorServiceTestBase {

	@Autowired
	protected MovieActorService movieActorService;

	@Test
	public void testFindMoviesActorsByCondition() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindMoviesActorsByCondition not implemented");
	}

	@Test
	public void testFindMoviesByActor() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindMoviesByActor not implemented");
	}

	@Test
	public void testFindActorsInMovie() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindActorsInMovie not implemented");
	}

	@Test
	public void testFindMoviesByActorId() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindMoviesByActorId not implemented");
	}

	@Test
	public void testFindActorsByMovieId() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindActorsByMovieId not implemented");
	}

	@Test
	public void testFindByCondition() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindByCondition not implemented");
	}

	@Test
	public void testChangeActor() throws Exception {
		// TODO Auto-generated method stub
		fail("testChangeActor not implemented");
	}

	@Test
	public void testChangeMovie() throws Exception {
		// TODO Auto-generated method stub
		fail("testChangeMovie not implemented");
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
