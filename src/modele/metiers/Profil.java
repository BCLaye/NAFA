package modele.metiers;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="NAFPROFIL")
public class Profil {
	
	@Id
	@Column(name="Code_profil", nullable = false, unique=true)
	private String codeProfil;
	
	@OneToMany(mappedBy="profil", cascade=CascadeType.ALL)
	private Collection<LangueProfil> libelleProfil;
	
	@OneToOne
	@JoinColumn(name="Code_nom_MDP")
	private NomenclatureMDP nomenclatureMDP; 
	
	@Column(name="Nb_valid_eff")
	private int nbvalidEff;
	
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
	
	@OneToMany(mappedBy="profil", cascade=CascadeType.ALL)
	private Collection<UtilisateurProfil> utilisateur;
	
	@OneToMany(mappedBy="profil", cascade=CascadeType.ALL)
	private Collection<FonctionnalitesProfil> fonctionnalitesProfil;
	
	@OneToMany(mappedBy="profil", cascade=CascadeType.ALL)
	private Collection<ValidationFonctionnalites> fonctionnalitesValide;
	
	@OneToMany(mappedBy="profilValidation", cascade=CascadeType.ALL)
	private Collection<ValidationFonctionnalites> fonctionnalitesVAliderPar; 
	
	@OneToMany(mappedBy="profil", cascade=CascadeType.ALL)
	private Collection<Menus> menus;

//----------------------------------------------------------------------------------------	
	public Profil() {
		super();
	}

	public Profil(String codeProfil, NomenclatureMDP nomenclatureMDP,
			int nbvalidEff, Utilisateur utilCree, Date dateCree) {
		super();
		this.codeProfil = codeProfil;
		this.nomenclatureMDP = nomenclatureMDP;
		this.nbvalidEff = nbvalidEff;
		this.utilCree = utilCree;
		this.dateCree = dateCree;
	}

	public String getCodeProfil() {
		return codeProfil;
	}

	public void setCodeProfil(String codeProfil) {
		this.codeProfil = codeProfil;
	}

	public Collection<LangueProfil> getLibelleProfil() {
		return libelleProfil;
	}

	public void setLibelleProfil(Collection<LangueProfil> libelleProfil) {
		this.libelleProfil = libelleProfil;
	}

	public NomenclatureMDP getNomenclatureMDP() {
		return nomenclatureMDP;
	}

	public void setNomenclatureMDP(NomenclatureMDP nomenclatureMDP) {
		this.nomenclatureMDP = nomenclatureMDP;
	}

	public int getNbvalidEff() {
		return nbvalidEff;
	}

	public void setNbvalidEff(int nbvalidEff) {
		this.nbvalidEff = nbvalidEff;
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

	public Collection<UtilisateurProfil> getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Collection<UtilisateurProfil> utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Collection<ValidationFonctionnalites> getFonctionnalitesValide() {
		return fonctionnalitesValide;
	}

	public void setFonctionnalitesValide(
			Collection<ValidationFonctionnalites> fonctionnalitesValide) {
		this.fonctionnalitesValide = fonctionnalitesValide;
	}

	public Collection<FonctionnalitesProfil> getFonctionnalitesProfil() {
		return fonctionnalitesProfil;
	}

	public void setFonctionnalitesProfil(
			Collection<FonctionnalitesProfil> fonctionnalitesProfil) {
		this.fonctionnalitesProfil = fonctionnalitesProfil;
	}

	public Collection<ValidationFonctionnalites> getFonctionnalitesVAliderPar() {
		return fonctionnalitesVAliderPar;
	}

	public void setFonctionnalitesVAliderPar(
			Collection<ValidationFonctionnalites> fonctionnalitesVAliderPar) {
		this.fonctionnalitesVAliderPar = fonctionnalitesVAliderPar;
	}

	public Collection<Menus> getMenus() {
		return menus;
	}

	public void setMenus(Collection<Menus> menus) {
		this.menus = menus;
	}

}
