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
@Table(name="Langue_Entite")
public class LangueEntite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1229700776508785048L;

	@Embeddable
	public static class LangueEntitePK implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Langue laguePK;
		@ManyToOne
		private Entite entitePK;
		
		public LangueEntitePK() {
			super();
		}

		public LangueEntitePK(Langue laguePK, Entite entitePK) {
			super();
			this.laguePK = laguePK;
			this.entitePK = entitePK;
		}	
		
		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}

		public Langue getLaguePK() {
			return laguePK;
		}

		public void setLaguePK(Langue laguePK) {
			this.laguePK = laguePK;
		}

		public Entite getEntitePK() {
			return entitePK;
		}

		public void setEntitePK(Entite entitePK) {
			this.entitePK = entitePK;
		}
	}
	
	@EmbeddedId
	private LangueEntitePK id;
	
	@ManyToOne
	@JoinColumn(name="Code_langue",nullable=false)
	private Langue langue;
	
	@ManyToOne
	@JoinColumn(name="Code_entite")
	private Entite entite;
	
	@Column(name="Lib_entite")
	private String libEntite;

	public LangueEntite() {
		super();
	}

	public LangueEntite(Langue langue, Entite entite, String libEntite) {
		this.id = new LangueEntitePK(langue, entite);
		this.langue = langue;
		this.entite = entite;
		this.libEntite = libEntite;
	}

	public LangueEntitePK getId() {
		return id;
	}

	public void setId(LangueEntitePK id) {
		this.id = id;
	}

	public Langue getLangue() {
		return langue;
	}

	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public String getLibEntite() {
		return libEntite;
	}

	public void setLibEntite(String libEntite) {
		this.libEntite = libEntite;
	}
	
	
}
