package com.jurajlazovy.web.rest;

import com.jurajlazovy.movies.domain.Actor;
import com.jurajlazovy.movies.domain.ActorRepository;
import com.jurajlazovy.movies.domain.Director;
import com.jurajlazovy.movies.domain.MovieActor;

import com.jurajlazovy.movies.serviceapi.ActorService;
import com.jurajlazovy.movies.serviceapi.DirectorService;
import com.jurajlazovy.movies.serviceapi.MovieActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of ActorResource.
 */
@Controller
public class ActorResource extends ActorResourceBase {

	public ActorResource() {
	}

	@Autowired
	private ActorService actorService;
	@Autowired
	private MovieActorService movieActorService;
	@Autowired
	private DirectorService directorService;

	@RequestMapping(value = "/actor/form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		Actor entity = new Actor();
		modelMap.addAttribute("entity", entity);
		return "actor/create";
	}

	// spustenie metody findActorsByCondition. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/actor/find", method = RequestMethod.POST)
	public String find(@RequestBody Actor entity) {

		List<Actor> result = actorService.findActorsByCondition(serviceContext());
		System.out.println("size = " + result.size());

		System.out.println("All male actor in our db: ");
		for (Actor actor : result) {
			System.out.println(" " + actor.getFirstName() +
					" " + actor.getLastName());
		}

		return String.format("redirect:/rest/actor/%s", result);
	}

	// spustenie metody findActorsByBirth. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/actor/findbirth", method = RequestMethod.POST)
	public String findBirth(@RequestBody Actor entity) {

		Date date = entity.getDateOfBirth();
		List<Actor> result = actorService.findActorsByBirth(serviceContext(), date);

		System.out.println("All actors in our db born before " + date + ": ");
		for (Actor actor : result) {
			System.out.println(" " + actor.getFirstName() +
					" " + actor.getLastName() + " born in " + actor.getDateOfBirth());
		}

		return String.format("redirect:/rest/actor/%s", result);
	}

	// spustenie metody findMoviesByActor. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/actor/findmovies", method = RequestMethod.POST)
	public String findMovies(@RequestBody Actor entity) {

		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();

		List<MovieActor> result = movieActorService.findMoviesByActor(serviceContext(),firstName, lastName);

		System.out.println("All movies in our db starring with " + firstName + " " + lastName + ": ");
		for (MovieActor movAct : result) {
			System.out.println(" " + movAct.getMovie().getMovieName() +
					", release at " + movAct.getMovie().getReleaseDate());
		}

		return String.format("redirect:/rest/actor/%s", result);
	}

	// Union - US directors a vsetci herci narodeni pred 1.1.1980
	// Vyuzijeme existujucu metodu findActorsByBirth a novu findAmericanDirectors
	// (neslo vyuzit aj dalsiu existujucu findDirectorsByNationality, lebo neviem dat premenne z dvoch beanov)
	// Dáta pomocou curl prikazu.
	@RequestMapping(value = "/actor/finddirectorsandactors", method = RequestMethod.POST)
	public String findDirectorsAndActors(@RequestBody Actor entity) {

		List<Director> directors = directorService.findAmericanDirectors(serviceContext());

		Date date = entity.getDateOfBirth();
		List<Actor> result = actorService.findActorsByBirth(serviceContext(), date);

		System.out.println("Union of American directors and all actors born before " + date + ": ");

		for (Director director : directors) {
			System.out.println(" " + director.getFirstName() +
					" " + director.getLastName() + " born in " + director.getDateOfBirth());
		}

		for (Actor actor : result) {
			System.out.println(" " + actor.getFirstName() +
					" " + actor.getLastName() + " born in " + actor.getDateOfBirth());
		}

		return String.format("redirect:/rest/actor/%s", result);
	}

	// zmena parametru gender a/alebo dateOfBirth pre actora najdeneho podla mena
	@RequestMapping(value = "/actor/newgenderorbirth", method = RequestMethod.POST)
	public String newGenderOrBirth(@RequestBody Director entity) {

		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();

		actorService.changeGenderOrBirth(serviceContext(), firstName, lastName);

		//List<Director> result = directorService.findAll(serviceContext());
		//return String.format("redirect:/rest/director/%s", result);
		// ako ma presne vyzerat return neviem. Ide to aj tak, aj tak
		return String.format("redirect:/rest/actor/%s", lastName);
	}

}
