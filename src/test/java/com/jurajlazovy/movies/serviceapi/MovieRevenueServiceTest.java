package com.jurajlazovy.movies.serviceapi;

import com.jurajlazovy.movies.serviceapi.MovieRevenueService;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class MovieRevenueServiceTest extends AbstractDbUnitJpaTests implements MovieRevenueServiceTestBase {

	@Autowired
	protected MovieRevenueService movieRevenueService;

	@Test
	public void testFindMovieRevenuesByCondition() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindMovieRevenuesByCondition not implemented");
	}

	@Test
	public void testFindInternationalMovieRevenues() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindInternationalMovieRevenues not implemented");
	}

	@Test
	public void testFindByCondition() throws Exception {
		// TODO Auto-generated method stub
		fail("testFindByCondition not implemented");
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
