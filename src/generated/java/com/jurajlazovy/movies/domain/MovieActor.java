package com.jurajlazovy.movies.domain;

import com.jurajlazovy.movies.domain.Actor;
import com.jurajlazovy.movies.domain.Movie;
import java.util.Date;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.sculptor.framework.domain.AbstractDomainObject;
import org.sculptor.framework.domain.AuditListener;
import org.sculptor.framework.domain.Auditable;
import org.sculptor.framework.domain.Identifiable;

/**
 * Entity representing MovieActor.
 */

@Entity
@Table(name = "MOVIEACTOR")
@EntityListeners({ AuditListener.class })
public class MovieActor extends AbstractDomainObject implements Auditable, Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "UUID", nullable = false, length = 36, unique = true)
	private String uuid;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDDATE")
	private Date createdDate;
	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTUPDATED")
	private Date lastUpdated;
	@Column(name = "LASTUPDATEDBY", length = 50)
	private String lastUpdatedBy;
	@Version
	@Column(name = "VERSION", nullable = false)
	private Long version;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "MOVIE", nullable = false)
	@ForeignKey(name = "FK_MOVIEACTOR_MOVIE")
	@NotNull
	private Movie movie;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "ACTOR", nullable = false)
	@ForeignKey(name = "FK_MOVIEACTOR_ACTOR")
	@NotNull
	private Actor actor;

	public MovieActor() {
	}

	public Long getId() {
		return id;
	}

	/**
	 * The id is not intended to be changed or assigned manually, but for test purpose it is allowed to assign the id.
	 */
	protected void setId(Long id) {
		if ((this.id != null) && !this.id.equals(id)) {
			throw new IllegalArgumentException("Not allowed to change the id property.");
		}
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;

	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;

	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;

	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;

	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;

	}

	/**
	 * This domain object doesn't have a natural key and this random generated identifier is the unique identifier for this domain
	 * object.
	 */
	public String getUuid() {
		// lazy init of UUID
		if (uuid == null) {
			uuid = UUID.randomUUID().toString();
		}
		return uuid;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@PrePersist
	protected void prePersist() {
		getUuid();
	}

	/**
	 * This method is used by equals and hashCode.
	 * 
	 * @return {{@link #getUuid}
	 */
	public Object getKey() {
		return getUuid();
	}

}
