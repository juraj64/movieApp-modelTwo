package com.jurajlazovy.movies.serviceimpl;

import com.jurajlazovy.movies.domain.Director;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.jurajlazovy.movies.domain.DirectorProperties;
import com.jurajlazovy.movies.domain.DirectorRepository;

import com.jurajlazovy.movies.serviceapi.DirectorService;
import org.hibernate.SessionFactory;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of DirectorService.
 */
@Service("directorService")
public class DirectorServiceImpl extends DirectorServiceImplBase {

    private static final Scanner scanner = new Scanner(System.in);

    public DirectorServiceImpl() {
    }

    @Autowired
    DirectorRepository directorRepository;

    public List<Director> findDirectorsByCondition(ServiceContext ctx) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = sdf.parse("1980-01-01");

        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Director.class)
                .withProperty(DirectorProperties.dateOfBirth()).lessThan(date) // starsi ako
                .orderBy(DirectorProperties.lastName()).build(); // zorad vsetkych podla lastName

        return directorRepository.findByCondition(criteria);
    }

    @Override
    public List<Director> findDirectorsByNationality(ServiceContext ctx, String nationality) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Director.class)
                .withProperty(DirectorProperties.nationality()).eq(nationality).build();

        return directorRepository.findByCondition(criteria);
    }

    @Override
    public List<Director> findAmericanDirectors(ServiceContext ctx) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Director.class)
                .withProperty(DirectorProperties.nationality()).eq("American")
                .orderBy(DirectorProperties.firstName()).build();

        return directorRepository.findByCondition(criteria);
    }

    @Override
    public String changeNationality(ServiceContext ctx, String firstName, String lastName) {

        // directors s danym menom
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Director.class)
                .withProperty(DirectorProperties.firstName()).eq(firstName)
                .withProperty(DirectorProperties.lastName()).eq(lastName).build();
        List<Director> directors = directorRepository.findByCondition(criteria);

        // pociatocne hodnoty
        Director myDirector;
        String oldNationality;
        String newNationality = null;

        // zmena nationality
        if (directors.isEmpty()) {
            System.out.println("No director with this name in DB.");
            return null;
        } else {
            myDirector = directors.get(0);
            oldNationality = myDirector.getNationality();
            // novu hodnotu viem dat iba takto cez konzolu (nie cez curl)
            System.out.println("Enter correct nationality: ");
            newNationality = scanner.next();
            //String newNationality = "Slovak"; // alebo takto na tvrdo do metody
            myDirector.setNationality(newNationality);
        }

        System.out.println("We have changed nationality of the director " + firstName + " " + lastName +
                " from " + oldNationality + " to " + newNationality + ".");

        return newNationality;
    }

}
