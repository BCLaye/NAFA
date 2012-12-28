package modele.metiers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Utilisateur")
public class Utilisateur {
	
	@OneToOne
	@JoinColumn(name="Code_entite")
	private Entite entite;
	
	@OneToOne
	@JoinColumn(name="Code_fonction")
	private Fonctions fonction;
	
	@Id
	@Column(name="Code_util", nullable = false, unique = true, length= 10)
	private String codeUtil;
	
	@Column(name="nom_util", nullable = false, length = 50)
	private String nomUtil;
	
	@Column(name="Niveau_privileg", length = 2)
	private int niveauPrivileg;
	
	@OneToOne
	@JoinColumn(name="Code_langue")
	private Langue langue;
	
	@Column(name="Mot_D_Pass")
	private String motDPass;
	
	@Column(name="Date_dbut_valid")
	@Temporal(TemporalType.DATE)
	private Date dateDbutValid;
	
	@Column(name="Date_fin_valid")
	@Temporal(TemporalType.DATE)
	private Date dateFinValid;
	
	@Column(name="Etat_util", nullable=false)
	private String etatutil;
	
	@Column(name="date_dernier_etat")
	@Temporal(TemporalType.DATE)
	private Date dateDernierEtat;
	
	@Column(name="Date_dernier_connexion")
	@Temporal(TemporalType.DATE)
	private Date dateDernierConnexion;
	
	@Column(name="Heure_dernier_connexion")
	@Temporal(TemporalType.TIME)
	private Date heureDernierConnexion;
	
	@Column(name="IP_dernier_Connexion")
	private String adressIPDernierConnexion;	
	
	@Column(name="Date_dernier_deconnexion")
	@Temporal(TemporalType.DATE)
	private Date dateDernierDeconnexion;
	
	@Column(name="Heure_dernier_deconnexion")
	@Temporal(TemporalType.TIME)
	private Date heureDernierDeconnexion;
	
	@Column(name="Date_dernier_modif_MDP")
	@Temporal(TemporalType.DATE)
	private Date dateDernierModifMDP;
	
	@Column(name="Heure_dernier_modif_MDP")
	@Temporal(TemporalType.TIME)
	private Date heureDernierModifMDP;
	
	@Column(name="Nb_valid_effect")
	private int nbValidEffect;
	
	@OneToOne
	@JoinColumn(name="util_cree")
	private Utilisateur utilCree;	

	@Column(name="Date_cree")
	@Temporal(TemporalType.DATE)
	private Date dateCree;
	
	@Column(name="Heure_cree")
	@Temporal(TemporalType.TIME)
	private Date heureCree;
	
	@OneToOne
	@JoinColumn(name="util_modif")
	private Utilisateur utilModif;		

	@Column(name="Date_dernier_modif")
	@Temporal(TemporalType.DATE)
	private Date dateDernierModif;
	
	@Column(name="Heure_dernier_Modif")
	@Temporal(TemporalType.TIME)
	private Date heureDernierModif;
	
	@OneToMany(mappedBy="utilisateur",cascade=CascadeType.ALL)
	private Collection<UtilisateurProfil> profils;
	
	@ManyToOne
	@JoinColumn(name="preference")
	private Preferences preference;

//-----------------------------------------------------------------------------------------	
	public Utilisateur() {
		super();
	}

public Utilisateur(Entite entite, Fonctions fonction, String codeUtil,
		String nomUtil, int niveauPrivileg, Langue langue, String motDPass,
		Date dateDbutValid, Date dateFinValid, String etatutil,
		Utilisateur uitlCree, Date dateCree, Date heureCree) {
	super();
	this.entite = entite;
	this.fonction = fonction;
	this.codeUtil = codeUtil;
	this.nomUtil = nomUtil;
	this.niveauPrivileg = niveauPrivileg;
	this.langue = langue;
	this.motDPass = motDPass;
	this.dateDbutValid = dateDbutValid;
	this.dateFinValid = dateFinValid;
	this.etatutil = etatutil;
	this.utilCree = uitlCree;
	this.dateCree = dateCree;
	this.heureCree = heureCree;
}

public Entite getEntite() {
	return entite;
}

public void setEntite(Entite entite) {
	this.entite = entite;
}

public Fonctions getFonction() {
	return fonction;
}

public void setFonction(Fonctions fonction) {
	this.fonction = fonction;
}

public String getCodeUtil() {
	return codeUtil;
}

public void setCodeUtil(String codeUtil) {
	this.codeUtil = codeUtil;
}

public String getNomUtil() {
	return nomUtil;
}

public void setNomUtil(String nomUtil) {
	this.nomUtil = nomUtil;
}

public int getNiveauPrivileg() {
	return niveauPrivileg;
}

public void setNiveauPrivileg(int niveauPrivileg) {
	this.niveauPrivileg = niveauPrivileg;
}

public Langue getLangue() {
	return langue;
}

public void setLangue(Langue langue) {
	this.langue = langue;
}

public String getMotDPass() {
	return motDPass;
}

public void setMotDPass(String motDPass) {
	if((motDPass != null) && (motDPass.trim().length()>0))
	this.motDPass = cryptageMDP(motDPass);
	else
		this.motDPass = motDPass;
}

public Date getDateDbutValid() {
	return dateDbutValid;
}

public void setDateDbutValid(Date dateDbutValid) {
	this.dateDbutValid = dateDbutValid;
}

public Date getDateFinValid() {
	return dateFinValid;
}

public void setDateFinValid(Date dateFinValid) {
	this.dateFinValid = dateFinValid;
}

public String getEtatutil() {
	return etatutil;
}

public void setEtatutil(String etatutil) {
	this.etatutil = etatutil;
}

public Date getDateDernierEtat() {
	return dateDernierEtat;
}

public void setDateDernierEtat(Date dateDernierEtat) {
	this.dateDernierEtat = dateDernierEtat;
}

public Date getDateDernierConnexion() {
	return dateDernierConnexion;
}

public void setDateDernierConnexion(Date dateDernierConnexion) {
	this.dateDernierConnexion = dateDernierConnexion;
}

public Date getHeureDernierConnexion() {
	return heureDernierConnexion;
}

public void setHeureDernierConnexion(Date heureDernierConnexion) {
	this.heureDernierConnexion = heureDernierConnexion;
}

public Date getDateDernierModifMDP() {
	return dateDernierModifMDP;
}

public void setDateDernierModifMDP(Date dateDernierModifMDP) {
	this.dateDernierModifMDP = dateDernierModifMDP;
}

public Date getHeureDernierModifMDP() {
	return heureDernierModifMDP;
}

public void setHeureDernierModifMDP(Date heureDernierModifMDP) {
	this.heureDernierModifMDP = heureDernierModifMDP;
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

public Date getHeureCree() {
	return heureCree;
}

public void setHeureCree(Date heureCree) {
	this.heureCree = heureCree;
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

public Date getHeureDernierModif() {
	return heureDernierModif;
}

public void setHeureDernierModif(Date heureDernierModif) {
	this.heureDernierModif = heureDernierModif;
}

public Collection<UtilisateurProfil> getProfils() {
	return profils;
}

public void setProfils(Collection<UtilisateurProfil> profils) {
	this.profils = profils;
}

public Preferences getPreference() {
	return preference;
}

public void setPreference(Preferences preference) {
	this.preference = preference;
}

public Date getDateDernierDeconnexion() {
	return dateDernierDeconnexion;
}

public void setDateDernierDeconnexion(Date dateDernierDeconnexion) {
	this.dateDernierDeconnexion = dateDernierDeconnexion;
}

public Date getHeureDernierDeconnexion() {
	return heureDernierDeconnexion;
}

public void setHeureDernierDeconnexion(Date heureDernierDeconnexion) {
	this.heureDernierDeconnexion = heureDernierDeconnexion;
}

public String getAdressIPDernierConnexion() {
	return adressIPDernierConnexion;
}

public void setAdressIPDernierConnexion(String adress) {
	this.adressIPDernierConnexion = adress;
}

public String cryptageMDP(String password){
	
	MessageDigest md = null;
	
	try {
		md = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    md.update(password.getBytes());

    byte byteData[] = md.digest();
  //convert the byte to hex format method 2
    StringBuffer hexString = new StringBuffer();
	for (int i=0;i<byteData.length;i++) {
		String hex=Integer.toHexString(0xff & byteData[i]);
	     	if(hex.length()==1) hexString.append('0');
	     	hexString.append(hex);
	}
	return (hexString.toString());
}
}
