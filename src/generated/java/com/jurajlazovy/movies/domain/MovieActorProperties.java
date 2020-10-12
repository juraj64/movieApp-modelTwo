package com.jurajlazovy.movies.domain;

import com.jurajlazovy.movies.domain.ActorProperties.ActorProperty;
import com.jurajlazovy.movies.domain.MovieActor;
import com.jurajlazovy.movies.domain.MovieProperties.MovieProperty;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in
 * {@link com.jurajlazovy.movies.domain.MovieActor}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class MovieActorProperties {

	private MovieActorProperties() {
	}

	private static final MovieActorPropertiesImpl<MovieActor> sharedInstance = new MovieActorPropertiesImpl<MovieActor>(
			MovieActor.class);

	public static Property<MovieActor> id() {
		return sharedInstance.id();
	}

	public static Property<MovieActor> uuid() {
		return sharedInstance.uuid();
	}

	public static Property<MovieActor> createdDate() {
		return sharedInstance.createdDate();
	}

	public static Property<MovieActor> createdBy() {
		return sharedInstance.createdBy();
	}

	public static Property<MovieActor> lastUpdated() {
		return sharedInstance.lastUpdated();
	}

	public static Property<MovieActor> lastUpdatedBy() {
		return sharedInstance.lastUpdatedBy();
	}

	public static Property<MovieActor> version() {
		return sharedInstance.version();
	}

	public static MovieProperty<MovieActor> movie() {
		return sharedInstance.movie();
	}

	public static ActorProperty<MovieActor> actor() {
		return sharedInstance.actor();
	}

	/**
	 * This class is used for references to {@link com.jurajlazovy.movies.domain.MovieActor}, i.e. nested property.
	 */
	public static class MovieActorProperty<T> extends MovieActorPropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public MovieActorProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class MovieActorPropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		MovieActorPropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		MovieActorPropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> uuid() {
			return new LeafProperty<T>(getParentPath(), "uuid", false, owningClass);
		}

		public Property<T> createdDate() {
			return new LeafProperty<T>(getParentPath(), "createdDate", false, owningClass);
		}

		public Property<T> createdBy() {
			return new LeafProperty<T>(getParentPath(), "createdBy", false, owningClass);
		}

		public Property<T> lastUpdated() {
			return new LeafProperty<T>(getParentPath(), "lastUpdated", false, owningClass);
		}

		public Property<T> lastUpdatedBy() {
			return new LeafProperty<T>(getParentPath(), "lastUpdatedBy", false, owningClass);
		}

		public Property<T> version() {
			return new LeafProperty<T>(getParentPath(), "version", false, owningClass);
		}

		public MovieProperty<T> movie() {
			return new MovieProperty<T>(getParentPath(), "movie", owningClass);
		}

		public ActorProperty<T> actor() {
			return new ActorProperty<T>(getParentPath(), "actor", owningClass);
		}
	}
}
