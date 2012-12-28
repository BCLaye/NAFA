package modele.metiers;

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
@Table(name="Fonctionnalites")
public class Fonctionnalites {
	
	@Id
	@Column(name="Code_fonctionnalite", length = 15)
	private String codeFonctionnalite;
	
	@OneToMany(mappedBy="fonctionnalite",cascade=CascadeType.ALL)
	private Collection<LangueFonctionnalites> libellefonctionnalite;
	
	@Column(name="Nb_valid_req")
	private int nbValidReq;
	
	@ManyToOne
	@JoinColumn(name="Code_module")
	private Module module;
	
	@ManyToOne
	@JoinColumn(name="Nom_programme")
	private Programme programme;
	
	@OneToOne
	@JoinColumn(name="util_cree")
	private Utilisateur uitlCree;
	
	@Column(name="Date_cree")
	@Temporal(TemporalType.DATE)
	private Date dateCree;
	
	@OneToOne
	@JoinColumn(name="util_modif")
	private Utilisateur uitlModif;
	
	@Column(name="Date_modif")
	@Temporal(TemporalType.DATE)
	private Date dateModif;
	
	@OneToMany(mappedBy="fonctionnalite",cascade=CascadeType.ALL)
	private Collection<FonctionnalitesProfil> fonctionnalitesprofils;  
	
	@OneToMany(mappedBy="fonctionnalite",cascade=CascadeType.ALL)
	private Collection<ValidationFonctionnalites> validationFonctionnalites;
	
	@OneToMany(mappedBy="fonctionnalite", cascade=CascadeType.ALL)
	public Collection<Menus> menus;

//----------------------------------------------------------------------------------------------------	
	public Fonctionnalites() {
		super();
	}

	public Fonctionnalites(String codeFonctionnalite, int nbValidReq,
			Module module, Programme programme, Utilisateur uitlCree,
			Date dateCree, Date dateModif) {
		super();
		this.codeFonctionnalite = codeFonctionnalite;
		this.nbValidReq = nbValidReq;
		this.module = module;
		this.programme = programme;
		this.uitlCree = uitlCree;
		this.dateCree = dateCree;
		this.dateModif = dateModif;
	}

	public String getCodeFonctionnalite() {
		return codeFonctionnalite;
	}

	public void setCodeFonctionnalite(String codeFonctionnalite) {
		this.codeFonctionnalite = codeFonctionnalite;
	}

	public Collection<LangueFonctionnalites> getLibellefonctionnalite() {
		return libellefonctionnalite;
	}

	public void setLibellefonctionnalite(
			Collection<LangueFonctionnalites> libellefonctionnalite) {
		this.libellefonctionnalite = libellefonctionnalite;
	}

	public int getNbValidReq() {
		return nbValidReq;
	}

	public void setNbValidReq(int nbValidReq) {
		this.nbValidReq = nbValidReq;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Programme getProgramme() {
		return programme;
	}

	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	public Utilisateur getUitlCree() {
		return uitlCree;
	}

	public void setUitlCree(Utilisateur uitlCree) {
		this.uitlCree = uitlCree;
	}

	public Date getDateCree() {
		return dateCree;
	}

	public void setDateCree(Date dateCree) {
		this.dateCree = dateCree;
	}

	public Utilisateur getUitlModif() {
		return uitlModif;
	}

	public void setUitlModif(Utilisateur uitlModif) {
		this.uitlModif = uitlModif;
	}

	public Date getDateModif() {
		return dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	public Collection<FonctionnalitesProfil> getFonctionnalitesprofils() {
		return fonctionnalitesprofils;
	}

	public void setFonctionnalitesprofils(
			Collection<FonctionnalitesProfil> fonctionnalitesprofils) {
		this.fonctionnalitesprofils = fonctionnalitesprofils;
	}

	public Collection<ValidationFonctionnalites> getValidationFonctionnalites() {
		return validationFonctionnalites;
	}

	public void setValidationFonctionnalites(
			Collection<ValidationFonctionnalites> validationFonctionnalites) {
		this.validationFonctionnalites = validationFonctionnalites;
	}

	public Collection<Menus> getMenus() {
		return menus;
	}

	public void setMenus(Collection<Menus> menus) {
		this.menus = menus;
	}
	
	
}
