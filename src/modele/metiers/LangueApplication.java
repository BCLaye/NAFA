package modele.metiers;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LangueApplication")
public class LangueApplication implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Embeddable
	private static class LangueApplicationPK implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Langue languePK;
		@ManyToOne
		private Application applicationPK;
		
		public LangueApplicationPK() {
			super();
		}

		public LangueApplicationPK(Langue languePK, Application applicationPK) {
			super();
			this.languePK = languePK;
			this.applicationPK = applicationPK;
		}
		
		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}

		@SuppressWarnings("unused")
		public Langue getLanguePK() {
			return languePK;
		}

		@SuppressWarnings("unused")
		public void setLanguePK(Langue languePK) {
			this.languePK = languePK;
		}

		@SuppressWarnings("unused")
		public Application getApplicationPK() {
			return applicationPK;
		}

		@SuppressWarnings("unused")
		public void setApplicationPK(Application applicationPK) {
			this.applicationPK = applicationPK;
		}
	}
	
	@EmbeddedId
	private LangueApplicationPK id;
	
	@ManyToOne
	@JoinColumn(name="Code_langue",nullable=false)
	private Langue langue;
	
	@ManyToOne
	@JoinColumn(name="Code_application",nullable=false)
	private Application application;
	
	@Column(name="lib_application")
	private String libAppli;

	public LangueApplication() {
		super();
		this.id = new LangueApplicationPK();
	}

	public LangueApplication(Langue langue, Application application) {
		super();
		this.id = new LangueApplicationPK(langue, application);
		this.langue = langue;
		this.application = application;
	}

	public LangueApplication(Langue langue, Application application,
			String libAppli) {
		super();
		this.id = new LangueApplicationPK(langue, application);
		this.langue = langue;
		this.application = application;
		this.libAppli = libAppli;
	}

	public LangueApplicationPK getId() {
		return id;
	}

	public void setId(LangueApplicationPK id) {
		this.id = id;
	}

	public Langue getLangue() {
		return langue;
	}

	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getLibAppli() {
		return libAppli;
	}

	public void setLibAppli(String libAppli) {
		this.libAppli = libAppli;
	}
	
	
	

}
