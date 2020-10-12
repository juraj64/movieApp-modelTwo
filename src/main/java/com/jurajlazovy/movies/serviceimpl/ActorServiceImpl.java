package com.jurajlazovy.movies.serviceimpl;

import com.jurajlazovy.movies.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.accessapi.ConditionalCriteriaBuilder;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.jurajlazovy.movies.domain.Gender.F;
import static com.jurajlazovy.movies.domain.Gender.M;
import static java.sql.Date.valueOf;
import static java.util.Date.*;

/**
 * Implementation of ActorService.
 */
@Service("actorService")
public class ActorServiceImpl extends ActorServiceImplBase {

    private static final Scanner scanner = new Scanner(System.in);

    public ActorServiceImpl() {
    }

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> findActorsByCondition(ServiceContext ctx) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Actor.class)
                .withProperty(ActorProperties.gender()).eq(M) // vsetci muzi
                .orderBy(ActorProperties.firstName()).build(); // zorad vsetkych podla firstName

        return actorRepository.findByCondition(criteria);
    }

    @Override
    public List<Actor> findActorsByBirth(ServiceContext ctx, Date dateOfBirth) {
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Actor.class)
                .withProperty(ActorProperties.dateOfBirth()).lessThan(dateOfBirth) // starsi ako
                .orderBy(ActorProperties.firstName()).build(); // podla firstName

        return actorRepository.findByCondition(criteria);
    }

    @Override
    public Actor changeGenderOrBirth(ServiceContext ctx, String firstName, String lastName) {
        // actors s danym menom
        List<ConditionalCriteria> criteria = ConditionalCriteriaBuilder.criteriaFor(Actor.class)
                .withProperty(ActorProperties.firstName()).eq(firstName)
                .withProperty(ActorProperties.lastName()).eq(lastName).build();
        List<Actor> actors = actorRepository.findByCondition(criteria);

        // nastavenie pociatocnych hodnot
        Actor myActor;
        Gender newGender = M;
        Date newDate = new Date();

        if (actors.isEmpty()) {
            System.out.println("No actor with this name in DB.");
            return null;
        } else {
            // novu hodnotu viem dat iba takto cez konzolu (alebo natvrdo - pozri metodu changeNationality)
            myActor = actors.get(0);
            System.out.println("Enter correct gender (M or F): ");
            try {
                newGender = Gender.valueOf(scanner.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
            myActor.setGender(newGender);

            System.out.println("Enter correct date of birth in format yyyy-MM-dd: ");
            try {
                newDate = valueOf(scanner.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
            myActor.setDateOfBirth(newDate);
        }

        System.out.println("We have edited the gender of the actor " + firstName + " " + lastName +
                " to " + newGender + " and his (her) date of birth to " + newDate + ".");

        return myActor;
    }

}
