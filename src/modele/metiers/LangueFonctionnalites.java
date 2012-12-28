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
@Table(name="Langue_Fonctionnalites")
public class LangueFonctionnalites {
	
	@Embeddable
	public static class LangueFonctionnalitesPK implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Langue languePK;
		@ManyToOne
		private Fonctionnalites fonctionnalitePK;
		public LangueFonctionnalitesPK() {
			super();
		}
		public LangueFonctionnalitesPK(Langue languePK,
				Fonctionnalites fonctionnalitePK) {
			super();
			this.languePK = languePK;
			this.fonctionnalitePK = fonctionnalitePK;
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
		public Fonctionnalites getFonctionnalitePK() {
			return fonctionnalitePK;
		}
		public void setFonctionnalitePK(Fonctionnalites fonctionnalitePK) {
			this.fonctionnalitePK = fonctionnalitePK;
		}
	}
	
	@EmbeddedId
	private LangueFonctionnalitesPK id;
	
	@ManyToOne
	@JoinColumn(name="Code_langue",nullable=false)
	private Langue langue;
	
	@ManyToOne
	@JoinColumn(name="code_fonctionnalite", nullable=false)
	private Fonctionnalites fonctionnalite;
	
	@Column(name="Lib_fonctionnalite")
	private String libFonctionnalie;

	public LangueFonctionnalites(Langue langue, Fonctionnalites fonctionnalite,
			String libFonctionnalie) {
		this.id = new LangueFonctionnalitesPK(langue, fonctionnalite);
		this.langue = langue;
		this.fonctionnalite = fonctionnalite;
		this.libFonctionnalie = libFonctionnalie;
	}
	
	public LangueFonctionnalites(Langue langue, Fonctionnalites fonctionnalite) {
		this.id = new LangueFonctionnalitesPK(langue, fonctionnalite);
		this.langue = langue;
		this.fonctionnalite = fonctionnalite;
	}

	public LangueFonctionnalites() {
		this.id = new LangueFonctionnalitesPK();
	}

	public LangueFonctionnalitesPK getId() {
		return id;
	}

	public void setId(LangueFonctionnalitesPK id) {
		this.id = id;
	}

	public Langue getLangue() {
		return langue;
	}

	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	public Fonctionnalites getFonctionnalite() {
		return fonctionnalite;
	}

	public void setFonctionnalite(Fonctionnalites fonctionnalite) {
		this.fonctionnalite = fonctionnalite;
	}

	public String getLibFonctionnalie() {
		return libFonctionnalie;
	}

	public void setLibFonctionnalie(String libFonctionnalie) {
		this.libFonctionnalie = libFonctionnalie;
	}

}
