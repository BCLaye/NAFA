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
@Table(name="Fonctionnalites_Profil")
public class FonctionnalitesProfil {
	
	@Embeddable
	public static class FonctionnalitesProfilPK implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Fonctionnalites fonctionnalitePK;
		@ManyToOne
		private Profil profilPK;
		
		public FonctionnalitesProfilPK() {
			super();
		}

		public FonctionnalitesProfilPK(Fonctionnalites fonctionnalitePK,
				Profil profilPK) {
			super();
			this.fonctionnalitePK = fonctionnalitePK;
			this.profilPK = profilPK;
		}
		
		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}

		public Fonctionnalites getFonctionnalitePK() {
			return fonctionnalitePK;
		}

		public void setFonctionnalitePK(Fonctionnalites fonctionnalitePK) {
			this.fonctionnalitePK = fonctionnalitePK;
		}

		public Profil getProfilPK() {
			return profilPK;
		}

		public void setProfilPK(Profil profilPK) {
			this.profilPK = profilPK;
		}		
	}
	
	@EmbeddedId
	private FonctionnalitesProfilPK id;
	
	@ManyToOne
	@JoinColumn(name="Code_fonctionnalite")
	private Fonctionnalites fonctionnalite;
	
	@ManyToOne
	@JoinColumn(name="Code_profil")
	private Profil profil;
	
	@Column(name="Nb_valid_req")
	private int nbValidReq;
	
	@Column(name="Nb_valid_effect")
	private int nbValidEffect;
	
	@Column(name="fontionnalite_effective")
	private int fonctionnaliteEffective;
	
	@OneToOne
	@JoinColumn(name="Util_cree")
	private Utilisateur utilCree;
	
	@Column(name="Date_cree")
	@Temporal(TemporalType.DATE)
	private Date dateCree;
	
	@OneToOne
	@JoinColumn(name="Util_modif")
	private Utilisateur utilModif;
	
	@Column(name="Date_modif")
	@Temporal(TemporalType.DATE)
	private Date dateModif;
	

//----------------------------------------------------------------------------------------------------	
	public FonctionnalitesProfil() {
		this.id = new FonctionnalitesProfilPK();
		this.fonctionnaliteEffective = 0;
	}

public FonctionnalitesProfil(Fonctionnalites fonctionnalite, Profil profil,
		int nbValidReq, int nbValidEffect, Utilisateur utilCree, Date dateCree) {
	this.id = new FonctionnalitesProfilPK(fonctionnalite, profil);
	this.fonctionnalite = fonctionnalite;
	this.profil = profil;
	this.nbValidReq = nbValidReq;
	this.nbValidEffect = nbValidEffect;
	this.utilCree = utilCree;
	this.dateCree = dateCree;
}

public FonctionnalitesProfilPK getId() {
	return id;
}

public void setId(FonctionnalitesProfilPK id) {
	this.id = id;
}

public void setId(Fonctionnalites fonctionnalite, Profil profil) {
	this.id = new FonctionnalitesProfilPK(fonctionnalite, profil);
}

public Fonctionnalites getFonctionnalite() {
	return fonctionnalite;
}

public void setFonctionnalite(Fonctionnalites fonctionnalite) {
	this.fonctionnalite = fonctionnalite;
}

public Profil getProfil() {
	return profil;
}

public void setProfil(Profil profil) {
	this.profil = profil;
}

public int getNbValidReq() {
	return nbValidReq;
}

public void setNbValidReq(int nbValidReq) {
	this.nbValidReq = nbValidReq;
}

public int getNbValidEffect() {
	return nbValidEffect;
}

public void setNbValidEffect(int nbValidEffect) {
	this.nbValidEffect = nbValidEffect;
}

public Utilisateur getUtilCree() {
	return utilCree;
}

public void setUtilCree(Utilisateur utilCree) {
	this.utilCree = utilCree;
}

public Date getDateCree() {
	return dateCree;
}

public void setDateCree(Date dateCree) {
	this.dateCree = dateCree;
}

public Utilisateur getUtilModif() {
	return utilModif;
}

public void setUtilModif(Utilisateur utilModif) {
	this.utilModif = utilModif;
}

public Date getDateModif() {
	return dateModif;
}

public void setDateModif(Date dateModif) {
	this.dateModif = dateModif;
}

public int getFonctionnaliteEffective() {
	return fonctionnaliteEffective;
}

public void setFonctionnaliteEffective(int fonctionnaliteEffective) {
	this.fonctionnaliteEffective = fonctionnaliteEffective;
}

}
