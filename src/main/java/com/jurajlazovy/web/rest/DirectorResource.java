package com.jurajlazovy.web.rest;

import com.jurajlazovy.movies.domain.*;

import com.jurajlazovy.movies.serviceapi.ActorService;
import com.jurajlazovy.movies.serviceapi.DirectorService;
import com.jurajlazovy.movies.serviceapi.MovieActorService;
import com.jurajlazovy.movies.serviceapi.MovieService;
import org.omg.IOP.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of DirectorResource.
 */
@Controller
public class DirectorResource extends DirectorResourceBase {

	public DirectorResource() {
	}

	@Autowired
	private DirectorService directorService;
	@Autowired
	private MovieService movieService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private MovieActorService movieActorService;

	@Autowired
	DirectorRepository directorRepository;

	@RequestMapping(value = "/director/form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		Director entity = new Director();
		modelMap.addAttribute("entity", entity);
		return "director/create";
	}

	// spustenie metody findDirectorsByCondition. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/director/find", method = RequestMethod.POST)
	public String find(@RequestBody Director entity) throws ParseException, com.jurajlazovy.movies.exception.ParseException {

		List<Director> result = directorService.findDirectorsByCondition(serviceContext());
		System.out.println("size = " + result.size());

		for (Director director : result) {
			System.out.println(director.getFirstName() + " " + director.getLastName() +
					" was born in " + director.getDateOfBirth());
		}

		return String.format("redirect:/rest/director/%s", result);
	}

	// spustenie metody findDirectorsByNationality. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/director/findbynationality", method = RequestMethod.POST)
	public String findbynationality(@RequestBody Director entity) {

		String nationality = entity.getNationality();
		List<Director> result = directorService.findDirectorsByNationality(serviceContext(), nationality);

		System.out.println("There are " + result.size() + " director(s) of " + nationality +
				" nationality in our db: ");
		for (Director director : result) {
			System.out.println("   " + director.getFirstName() + " " + director.getLastName());
		}

		return String.format("redirect:/rest/director/%s", result);
	}

	// spustenie metody findMoviesByDirectorName. Dáta pomocou curl prikazu.
	// aj ked hladam movie, musim to spustat cez directora, aby nacital premenne firstName a lastName
	@RequestMapping(value = "/director/findmoviesbyname", method = RequestMethod.POST)
	public String findmoviesbyname(@RequestBody Director entity) {

		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();

		List<Movie> result = movieService.findMoviesByDirectorName(serviceContext(), firstName, lastName);

		System.out.println("All movies in our db directed by " + firstName + " " + lastName + ": ");
		for (Movie movie : result) {
			System.out.println("  " + movie.getMovieName() +
					", released at " + movie.getReleaseDate());
		}

		return String.format("redirect:/rest/director/%s", result);
	}

	// Hladanie actors vo vsetkych movies daneho directora (podla mena)
	// Vyuzijem uz existujuce metody findMoviesByDirectorName a findActorsInMovieId
	// Aj ked hladam actors, musim to spustat cez directora, aby nacital premenne firstName a lastName
	@RequestMapping(value = "/director/findactors", method = RequestMethod.POST)
	public String findActorsInDirectorMovies(@RequestBody Director entity) {

		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();

		// movies podla mena directora
		List<Movie> movies = movieService.findMoviesByDirectorName(serviceContext(), firstName, lastName);

		Set<Actor> actors = new HashSet<>(); // aby nebola duplicita

		for (Movie movie : movies) { // hladanie actorov v movies pomocou MovieActor
			List<MovieActor> movActs = movieActorService.findActorsByMovieId(serviceContext(), movie);
				for(MovieActor movAct : movActs) {
					actors.add(movAct.getActor());
			}
		}

		System.out.println("All actors starring in movies in our db directed by "
				+ firstName + " " + lastName + ": ");
		for (Actor actor : actors) {
			System.out.println("  " + actor.getFirstName() + " " + actor.getLastName());
		}

		return String.format("redirect:/rest/director/%s", actors);
	}


	// zmena parametru nationality pre directora najdeneho podla mena
	@RequestMapping(value = "/director/newnationality", method = RequestMethod.POST)
	public String newNationality(@RequestBody Director entity) {

		String firstName = entity.getFirstName();
		String lastName = entity.getLastName();

		String newNationality = directorService.changeNationality(serviceContext(), firstName, lastName);

		//List<Director> result = directorService.findAll(serviceContext());
		//return String.format("redirect:/rest/director/%s", result);
		// ako ma presne vyzerat return neviem. Ide to aj tak, aj tak
		return String.format("redirect:/rest/director/%s", newNationality);
	}

}
