package com.jurajlazovy.movies.domain;

import com.jurajlazovy.movies.domain.MovieProperties.MovieProperty;
import com.jurajlazovy.movies.domain.MovieRevenue;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in
 * {@link com.jurajlazovy.movies.domain.MovieRevenue}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class MovieRevenueProperties {

	private MovieRevenueProperties() {
	}

	private static final MovieRevenuePropertiesImpl<MovieRevenue> sharedInstance = new MovieRevenuePropertiesImpl<MovieRevenue>(
			MovieRevenue.class);

	public static Property<MovieRevenue> id() {
		return sharedInstance.id();
	}

	public static Property<MovieRevenue> domesticTakings() {
		return sharedInstance.domesticTakings();
	}

	public static Property<MovieRevenue> internationalTakings() {
		return sharedInstance.internationalTakings();
	}

	public static Property<MovieRevenue> uuid() {
		return sharedInstance.uuid();
	}

	public static Property<MovieRevenue> createdDate() {
		return sharedInstance.createdDate();
	}

	public static Property<MovieRevenue> createdBy() {
		return sharedInstance.createdBy();
	}

	public static Property<MovieRevenue> lastUpdated() {
		return sharedInstance.lastUpdated();
	}

	public static Property<MovieRevenue> lastUpdatedBy() {
		return sharedInstance.lastUpdatedBy();
	}

	public static Property<MovieRevenue> version() {
		return sharedInstance.version();
	}

	public static MovieProperty<MovieRevenue> movie() {
		return sharedInstance.movie();
	}

	/**
	 * This class is used for references to {@link com.jurajlazovy.movies.domain.MovieRevenue}, i.e. nested property.
	 */
	public static class MovieRevenueProperty<T> extends MovieRevenuePropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public MovieRevenueProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class MovieRevenuePropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		MovieRevenuePropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		MovieRevenuePropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> domesticTakings() {
			return new LeafProperty<T>(getParentPath(), "domesticTakings", false, owningClass);
		}

		public Property<T> internationalTakings() {
			return new LeafProperty<T>(getParentPath(), "internationalTakings", false, owningClass);
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
	}
}
