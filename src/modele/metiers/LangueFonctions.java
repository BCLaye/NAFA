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
@Table(name="Langue_Fonctions")
public class LangueFonctions {
	
	@Embeddable
	public static class langueFonctionsPK implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Langue languePK;
		@ManyToOne
		private Fonctions fonctionsPK;
		
		public langueFonctionsPK() {
			super();
		}

		public langueFonctionsPK(Langue languePK, Fonctions fonctionsPK) {
			super();
			this.languePK = languePK;
			this.fonctionsPK = fonctionsPK;
		}
		
		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}

		public Langue getLanguePK() {
			return languePK;
		}

		public void setLanguePK(Langue languePK) {
			this.languePK = languePK;
		}

		public Fonctions getFonctionsPK() {
			return fonctionsPK;
		}

		public void setFonctionsPK(Fonctions fonctionsPK) {
			this.fonctionsPK = fonctionsPK;
		}
	}
	
	@EmbeddedId
	private langueFonctionsPK id;
	
	@ManyToOne
	@JoinColumn(name="Code_langue")
	private Langue langue;
	
	@ManyToOne
	@JoinColumn(name="Code_fonctions")
	private Fonctions fonction;
	
	@Column(name="Lib_fonction")
	private String libFonctions;

	public LangueFonctions() {
		this.id = new langueFonctionsPK();
	}

	public LangueFonctions(Langue langue, Fonctions fonction,
			String libFonctions) {
		this.id = new langueFonctionsPK(langue,fonction);
		this.langue = langue;
		this.fonction = fonction;
		this.libFonctions = libFonctions;
	}

	public langueFonctionsPK getId() {
		return id;
	}

	public void setId(langueFonctionsPK id) {
		this.id = id;
	}

	public Langue getLangue() {
		return langue;
	}

	public void setLangue(Langue langue) {
		this.id.languePK = langue;
		this.langue = langue;
	}

	public Fonctions getFonction() {
		return fonction;
	}

	public void setFonction(Fonctions fonction) {
		this.id.fonctionsPK = fonction;
		this.fonction = fonction;
	}

	public String getLibFonctions() {
		return libFonctions;
	}

	public void setLibFonctions(String libFonctions) {
		this.libFonctions = libFonctions;
	}
}
