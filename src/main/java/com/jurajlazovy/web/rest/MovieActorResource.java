package com.jurajlazovy.web.rest;

import com.jurajlazovy.movies.domain.*;
import com.jurajlazovy.movies.serviceapi.ActorService;
import com.jurajlazovy.movies.serviceapi.MovieActorService;
import com.jurajlazovy.movies.serviceapi.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Implementation of MovieActorResource.
 */
@Controller
public class MovieActorResource extends MovieActorResourceBase {

	public MovieActorResource() {
	}

	@Autowired
	private MovieService movieService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private MovieActorService movieActorService;

	@RequestMapping(value = "/movieActor/form", method = RequestMethod.GET)
	public String createForm(ModelMap modelMap) {
		MovieActor entity = new MovieActor();
		modelMap.addAttribute("entity", entity);
		return "movieActor/create";
	}

	// Doplnena overwritnuta metoda pre vytvorenie noveho movieRevenue
	@RequestMapping(value = "/movieActor", method = RequestMethod.POST)
	public String create(@RequestBody MovieActor entity) {

		List<MovieActor> movieActors = movieActorService.findAll(serviceContext());

		Long movieId = entity.getMovie().getId();
		Long actorId = entity.getActor().getId();

		// kontrola, ci uz neexistuje rovnaka kombinacia movie a actora
		for(MovieActor movAct : movieActors) {
			if(movAct.getMovie().getId().equals(movieId)
					&& movAct.getActor().getId().equals(actorId)) {
				System.out.println("Actor with id = " + actorId +
						" for movie with id = " + movieId + " has already been set up.");
				return null;
			}
		}

		// moznost nacitat movie podla id
		if (movieId != null) {
			try {
				Movie movie = movieService.findById(serviceContext(), movieId);
				entity.setMovie(movie);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// moznost nacitat actor podla id
		if (actorId != null) {
			try {
				Actor actor = actorService.findById(serviceContext(), actorId);
				entity.setActor(actor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return super.create(entity);
	}

	// spustenie metody findMoviesActorsByCondition. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/movieActor/find", method = RequestMethod.POST)
	public String find(@RequestBody MovieActor entity) {

		List<MovieActor> result = movieActorService.findMoviesActorsByCondition(serviceContext());

		System.out.println("All movies in our db with female actors: ");
		for (MovieActor movAct : result) {
			System.out.println(" " + movAct.getMovie().getMovieName() +
					" starring also with " + movAct.getActor().getFirstName() +
					" " + movAct.getActor().getLastName());
		}

		return String.format("redirect:/rest/movieActor/%s", result);
	}

	// spustenie metody findMoviesByActorId. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/movieActor/findmovies", method = RequestMethod.POST)
	public String findMovies(@RequestBody MovieActor entity) {

		Long actorId = entity.getActor().getId(); // nacitat actora podla id

		if (actorId != null) {
			try {
				Actor actor = actorService.findById(serviceContext(), actorId);
				entity.setActor(actor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<MovieActor> result = movieActorService.findMoviesByActorId(serviceContext(), entity.getActor());

		System.out.println("All movies in our db starring with " + entity.getActor().getFirstName() + " "
				+ entity.getActor().getLastName() + ": ");
		for (MovieActor movAct : result) {
			System.out.println(" " + movAct.getMovie().getMovieName() +
					", release at " + movAct.getMovie().getReleaseDate());
		}

		return String.format("redirect:/rest/movieActor/%s", result);
	}

	// spustenie metody findActorsByMovieId. Dáta pomocou curl prikazu.
	@RequestMapping(value = "/movieActor/findactors", method = RequestMethod.POST)
	public String findActors(@RequestBody MovieActor entity) {

		Long movieId = entity.getMovie().getId(); // nacitat movie podla id

		if (movieId != null) {
			try {
				Movie movie = movieService.findById(serviceContext(), movieId);
				entity.setMovie(movie);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<MovieActor> result = movieActorService.findActorsByMovieId(serviceContext(), entity.getMovie());

		System.out.println("Movie " + entity.getMovie().getMovieName() + " stars with ");
		for (MovieActor movAct : result) {
			System.out.println(" " + movAct.getActor().getFirstName() +
					" " + movAct.getActor().getLastName());
		}

		return String.format("redirect:/rest/movieActor/%s", result);
	}

	// zmena actora pre movieActor najdeneho podla movie a actor. Vie to nacitat len podla id (pozri nizsie).
	@RequestMapping(value = "/movieActor/newactor", method = RequestMethod.POST)
	public String newActor(@RequestBody MovieActor entity) {

		Long movieId = entity.getMovie().getId(); // nacitat movie podla id
		if (movieId != null) {
			try {
				Movie movie = movieService.findById(serviceContext(), movieId);
				entity.setMovie(movie);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Long actorId = entity.getActor().getId(); // nacitat movie podla id
		if (actorId != null) {
			try {
				Actor actor = actorService.findById(serviceContext(), actorId);
				entity.setActor(actor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		MovieActor movieActor = movieActorService.changeActor(serviceContext(), entity.getMovie(), entity.getActor());
		return String.format("redirect:/rest/movieActor/%s", movieActor);
	}

	// Snaha o zmenu movie pre movieActor najdeneho podla movie (movieName, relaeaseDate)
	// a actor (firstName, lastName). Takto nevie nacitat hodnoty do enitity
//	@RequestMapping(value = "/movieActor/newmovie", method = RequestMethod.POST)
//	public String newMovie(@RequestBody MovieActor entity) throws ParseException {
//
//		String movieName = entity.getMovie().getMovieName();
//		Date date = entity.getMovie().getReleaseDate();
//		String firstName = entity.getActor().getFirstName();
//		String lastName = entity.getActor().getLastName();
//
//		// transformacia date na potrebny format yyyy-MM-dd, aby to metoda dole vedela precitat
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//		String strDate = sdf.format(date);
//		Date releaseDate = sdf.parse(strDate);
//
//		MovieActor movieActor = movieActorService.changeMovie(serviceContext(), movieName,
//				releaseDate, firstName, lastName);
//
//		return String.format("redirect:/rest/movieActor/%s", movieActor);
//	}

}
