package modele.metiers;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Langue_Profil")
public class LangueProfil {
	
	@Embeddable
	public static class LangueProfilPK implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Langue codeLanguePK;
		@ManyToOne
		private Profil codeProfilPK;
		
		public LangueProfilPK() {
			super();
		}

		public LangueProfilPK(Langue codeLanguePK, Profil codeProfilPK) {
			super();
			this.codeLanguePK = codeLanguePK;
			this.codeProfilPK = codeProfilPK;
		}
		
		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}

		public Langue getCodeLanguePK() {
			return codeLanguePK;
		}

		public void setCodeLanguePK(Langue codeLanguePK) {
			this.codeLanguePK = codeLanguePK;
		}

		public Profil getCodeProfilPK() {
			return codeProfilPK;
		}

		public void setCodeProfilPK(Profil codeProfilPK) {
			this.codeProfilPK = codeProfilPK;
		}
	}
	
	@EmbeddedId
	private LangueProfilPK id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Code_langue")
	private Langue langue;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Code_profil")
	private Profil profil;
	
	@Column(name="Lib_profil")
	private String libelle;

	public LangueProfil() {
		this.id = new LangueProfilPK();
	}

	public LangueProfil(Langue codeLangue, Profil codeProfil, String libelle) {
		this.id = new LangueProfilPK(codeLangue, codeProfil);
		this.langue = codeLangue;
		this.profil = codeProfil;
		this.libelle = libelle;
	}

	public LangueProfilPK getId() {
		return id;
	}

	public void setId(LangueProfilPK id) {
		this.id = id;
	}

	

	public Langue getLangue() {
		return langue;
	}

	public void setLangue(Langue langue) {
		this.id.codeLanguePK = langue;
		this.langue = langue;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.id.codeProfilPK = profil;
		this.profil = profil;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
