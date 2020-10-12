package com.jurajlazovy.movies.serviceimpl;

import com.jurajlazovy.movies.domain.*;

import java.util.List;

import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of MovieRevenueService.
 */
@Service("movieRevenueService")
public class MovieRevenueServiceImpl extends MovieRevenueServiceImplBase {

    public MovieRevenueServiceImpl() {
    }

    @Autowired
    MovieRevenueRepository revenueRepository;

    public List<MovieRevenue> findMovieRevenuesByCondition(ServiceContext ctx) {

        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieRevenue.class)
                .orderBy(MovieRevenueProperties.domesticTakings()).descending().build();  // podla domestic takings

        return revenueRepository.findByCondition(criteria);
    }

    @Override
    public List<MovieRevenue> findInternationalMovieRevenues(ServiceContext ctx, int internationalTakings) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(MovieRevenue.class)
                .withProperty(MovieRevenueProperties.internationalTakings()).greaterThan(internationalTakings) // viac ako
                .orderBy(MovieRevenueProperties.internationalTakings()).descending().build();  // podla international

        return revenueRepository.findByCondition(criteria);
    }

}
