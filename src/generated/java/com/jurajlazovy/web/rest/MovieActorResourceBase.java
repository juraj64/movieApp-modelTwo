package com.jurajlazovy.web.rest;

import com.jurajlazovy.movies.domain.MovieActor;
import com.jurajlazovy.movies.exception.MovieActorNotFoundException;
import com.jurajlazovy.movies.serviceapi.MovieActorService;
import java.io.IOException;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.sculptor.framework.context.ServiceContext;
import org.sculptor.framework.context.ServiceContextStore;
import org.sculptor.framework.errorhandling.OptimisticLockingException;
import org.sculptor.framework.errorhandling.SystemException;
import org.sculptor.framework.errorhandling.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Generated base class for implementation of MovieActorResource.
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 * @org.springframework.stereotype.Controller
 * </pre>
 */
public abstract class MovieActorResourceBase {

	public MovieActorResourceBase() {
	}

	protected ServiceContext serviceContext() {
		return ServiceContextStore.get();
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}

	@Autowired
	private MovieActorService movieActorService;

	protected MovieActorService getMovieActorService() {
		return movieActorService;
	}

	@RequestMapping(value = "/movieActor/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) throws MovieActorNotFoundException {
		MovieActor result = movieActorService.findById(serviceContext(), id);
		modelMap.addAttribute("result", result);
		return "movieActor/show";
	}

	@RequestMapping(value = "/movieActor", method = RequestMethod.GET)
	public String showAll(ModelMap modelMap) {
		List<MovieActor> result = movieActorService.findAll(serviceContext());
		modelMap.addAttribute("result", result);
		return "movieActor/showlist";
	}

	@RequestMapping(value = "/movieActor", method = RequestMethod.POST)
	public String create(@RequestBody MovieActor entity) {
		MovieActor result = movieActorService.save(serviceContext(), entity);
		return String.format("redirect:/rest/movieActor/%s", result.getId());
	}

	@RequestMapping(value = "/movieActor/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) throws Exception {
		MovieActor deleteObj = movieActorService.findById(serviceContext(), id);
		movieActorService.delete(serviceContext(), deleteObj);
		return "redirect:/rest/movieActor";
	}

	/*
	 * @org.springframework.web.bind.annotation.RequestMapping(value = "/movieActor/form",
	 * method=org.springframework.web.bind.annotation.RequestMethod.GET) public String createForm(org.springframework.ui.ModelMap
	 * modelMap )
	 */
	public abstract String createForm(ModelMap modelMap);

	/**
	 * This method is needed for form data POST. Delegates to {@link #create}
	 */
	@RequestMapping(value = "/movieActor", method = RequestMethod.POST, headers = "content-type=application/x-www-form-urlencoded")
	public String createFromForm(@ModelAttribute("entity") MovieActor entity) {
		return create(entity);
	}

	@ExceptionHandler
	public void handleException(MovieActorNotFoundException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler
	public void handleException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler
	public void handleException(ValidationException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}

	@ExceptionHandler
	public void handleException(SystemException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage());
	}

	@ExceptionHandler
	public void handleException(OptimisticLockingException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.CONFLICT.value(), e.getMessage());
	}

}
