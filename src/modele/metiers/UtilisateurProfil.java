package modele.metiers;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Utilisateur_Profil")
public class UtilisateurProfil {
	
	@Embeddable
	public static class UtilisateurProfilPK implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Utilisateur utilPK;
		@ManyToOne
		private Profil profilPK;
		
		public UtilisateurProfilPK() {
		}

		public UtilisateurProfilPK(Utilisateur codeutilPK, Profil codeProfilPK) {
			this.utilPK = codeutilPK;
			this.profilPK = codeProfilPK;
		}
		
		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}

		public Utilisateur getUtilPK() {
			return utilPK;
		}

		public void setUtilPK(Utilisateur utilPK) {
			this.utilPK = utilPK;
		}

		public Profil getProfilPK() {
			return profilPK;
		}

		public void setProfilPK(Profil profilPK) {
			this.profilPK = profilPK;
		}

		
	}
	
	@EmbeddedId
	private UtilisateurProfilPK id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Code_util")
	private Utilisateur utilisateur;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Code_profil")
	private Profil profil;
	
	@Column(name="Date_deb_aff")
	@Temporal(TemporalType.DATE)
	private Date dateDebAff;
	
	@Column(name="Date_fin_aff")
	@Temporal(TemporalType.DATE)
	private Date dateFinAff;
	
	@Column(name="Etat", nullable=false)  //Etat (Actif, Inactif)
	private String etat;
	
	@Column(name="Date_dernier_etat")
	@Temporal(TemporalType.DATE)
	private Date dateDernierEtat;
	
	@Column(name="Nb_valid_effect")
	private int nbValidEffect;
	
	@OneToOne
	@JoinColumn(name="util_cree")
	private Utilisateur utilCree;
	
	@Column(name="Date_cree")
	@Temporal(TemporalType.DATE)
	private Date dateCree;
	
	@OneToOne
	@JoinColumn(name="util_modif")
	private Utilisateur utilModif;
	
	@Column(name="Date_dernier_modif")
	@Temporal(TemporalType.DATE)
	private Date dateDernierModif;
	
//------------------------------------------------------------------------------------------
	public UtilisateurProfil() {
		this.id = new UtilisateurProfilPK();
	}
	
	public UtilisateurProfil(Utilisateur utilisateur, Profil profil) {
		this.id = new UtilisateurProfilPK(utilisateur, profil); 
		this.utilisateur = utilisateur;
		this.profil = profil;
		this.nbValidEffect = 0;
	}

public UtilisateurProfil(Utilisateur utilisateur, Profil profil,
		Date dateDebAff, Date dateFinAff, String etat, Utilisateur uitlCree,
		Date dateCree) {
	super();	
	this.id = new UtilisateurProfilPK(utilisateur, profil); 
	this.utilisateur = utilisateur;
	this.profil = profil;
	this.dateDebAff = dateDebAff;
	this.dateFinAff = dateFinAff;
	this.etat = etat;
	this.utilCree = uitlCree;
	this.dateCree = dateCree;
	this.nbValidEffect = 0;
}

public UtilisateurProfilPK getId() {
	return id;
}

public void setId(UtilisateurProfilPK id) {
	this.id = id;
}

public Utilisateur getUtilisateur() {
	return utilisateur;
}

public void setUtilisateur(Utilisateur utilisateur) {
	this.id.utilPK = utilisateur;
	this.utilisateur = utilisateur;
}

public Profil getProfil() {
	return profil;
}

public void setProfil(Profil profil) {
	this.id.profilPK = profil;
	this.profil = profil;
}

public Date getDateDebAff() {
	return dateDebAff;
}

public void setDateDebAff(Date dateDebAff) {
	this.dateDebAff = dateDebAff;
}

public Date getDateFinAff() {
	return dateFinAff;
}

public void setDateFinAff(Date dateFinAff) {
	this.dateFinAff = dateFinAff;
}

public String getEtat() {
	return etat;
}

public void setEtat(String etat) {
	this.etat = etat;
}

public Date getDateDernierEtat() {
	return dateDernierEtat;
}

public void setDateDernierEtat(Date dateDernierEtat) {
	this.dateDernierEtat = dateDernierEtat;
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

public void setUtilCree(Utilisateur uitlCree) {
	this.utilCree = uitlCree;
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

public void setUtilModif(Utilisateur uitlModif) {
	this.utilModif = uitlModif;
}

public Date getDateDernierModif() {
	return dateDernierModif;
}

public void setDateDernierModif(Date dateDernierModif) {
	this.dateDernierModif = dateDernierModif;
}
	
	
}
