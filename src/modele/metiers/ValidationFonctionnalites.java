package modele.metiers;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Validation_Fonctionnalites")
public class ValidationFonctionnalites {
	
	@Embeddable
	public static class ValidationFonctionnalitesPK implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Profil profilPK;
		@ManyToOne
		private Fonctionnalites fonctionnalitePK;
		@ManyToOne
		private Profil profilValidPK;
		
		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}
		
		public ValidationFonctionnalitesPK(Profil profilPK,
				Fonctionnalites fonctionnalitePK, Profil profilValidPK) {
			super();
			this.profilPK = profilPK;
			this.fonctionnalitePK = fonctionnalitePK;
			this.profilValidPK = profilValidPK;
		}

		public ValidationFonctionnalitesPK() {
			super();
		}

		public Profil getProfilPK() {
			return profilPK;
		}

		public void setProfilPK(Profil profilPK) {
			this.profilPK = profilPK;
		}

		public Fonctionnalites getFonctionnalitePK() {
			return fonctionnalitePK;
		}

		public void setFonctionnalitePK(Fonctionnalites fonctionnalitePK) {
			this.fonctionnalitePK = fonctionnalitePK;
		}

		public Profil getProfilValidPK() {
			return profilValidPK;
		}

		public void setProfilValidPK(Profil profilValidPK) {
			this.profilValidPK = profilValidPK;
		}

		
	}
	
	@EmbeddedId
	private ValidationFonctionnalitesPK id;
	
	@ManyToOne
	@JoinColumn(name="Code_profil")
	private Profil profil;
	
	@ManyToOne
	@JoinColumn(name="Code_fonctionnalite")
	private Fonctionnalites fonctionnalite;
	
	@ManyToOne
	@JoinColumn(name="Code_profil_vali")
	private Profil profilValidation;
	
	@Column(name="Rang_profil_valid")
	private int rangProfilValid;
	
	@Column(name="Nb_valid_effect")
	private int nbValidEffect;
	
	@OneToOne
	@JoinColumn(name="Util_valid")
	private Utilisateur utilValid;
	
	@Column(name="Date_valid")
	@Temporal(TemporalType.DATE)
	private Date dateValid;
	
	@OneToOne
	@JoinColumn(name="Util_modif")
	private Utilisateur utilModif;
	
	@Column(name="Date_dernier_modif")
	@Temporal(TemporalType.DATE)
	private Date dateDernierModif;
	
//-------------------------------------------------------------------------------------------------------------
	
	public ValidationFonctionnalites() {
		this.id = new ValidationFonctionnalitesPK();
	}

public ValidationFonctionnalites(Profil profil, Fonctionnalites fonctionnalite,
		Profil profilValidation, int rangProfilValid, int nbValidEffect,
		Utilisateur utilValid, Date dateValid, Utilisateur utilModif,
		Date dateDernierModif) {
	
	this.id = new ValidationFonctionnalitesPK(profil, fonctionnalite, profilValidation);
	this.profil = profil;
	this.fonctionnalite = fonctionnalite;
	this.profilValidation = profilValidation;
	this.rangProfilValid = rangProfilValid;
	this.nbValidEffect = nbValidEffect;
	this.utilValid = utilValid;
	this.dateValid = dateValid;
	this.utilModif = utilModif;
	this.dateDernierModif = dateDernierModif;
	}

public ValidationFonctionnalitesPK getId() {
	return id;
}

public void setId(Profil profil, Fonctionnalites fonctionnalite,Profil profilValidation) {
	this.id = new ValidationFonctionnalitesPK(profil, fonctionnalite, profilValidation);
}

public Profil getProfil() {
	return profil;
}

public void setProfil(Profil profil) {
	this.profil = profil;
}

public Fonctionnalites getFonctionnalite() {
	return fonctionnalite;
}

public void setFonctionnalite(Fonctionnalites fonctionnalite) {
	this.fonctionnalite = fonctionnalite;
}

public Profil getProfilValidation() {
	return profilValidation;
}

public void setProfilValidation(Profil profilValidation) {
	this.profilValidation = profilValidation;
}

public int getRangProfilValid() {
	return rangProfilValid;
}

public void setRangProfilValid(int rangProfilValid) {
	this.rangProfilValid = rangProfilValid;
}

public int getNbValidEffect() {
	return nbValidEffect;
}

public void setNbValidEffect(int nbValidEffect) {
	this.nbValidEffect = nbValidEffect;
}

public Utilisateur getUtilValid() {
	return utilValid;
}

public void setUtilValid(Utilisateur utilValid) {
	this.utilValid = utilValid;
}

public Date getDateValid() {
	return dateValid;
}

public void setDateValid(Date dateValid) {
	this.dateValid = dateValid;
}

public Utilisateur getUtilModif() {
	return utilModif;
}

public void setUtilModif(Utilisateur utilModif) {
	this.utilModif = utilModif;
}

public Date getDateDernierModif() {
	return dateDernierModif;
}

public void setDateDernierModif(Date dateDernierModif) {
	this.dateDernierModif = dateDernierModif;
}
	
	
	
	}
