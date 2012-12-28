package modele.metiers;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Entite")
public class Entite {
	
	@Id
	@Column(name="Code_entite", length=5, nullable=false, unique=true)
	private String codeEntite;
	
	@OneToMany(mappedBy="entite",cascade=CascadeType.ALL)
	private Collection<LangueEntite> libEntite;
	
	@Column(name="Niv_organisationnel")
	private int niveauOrg;
	
	@Column(name="Util_rattache")
	private char utilRattach;
	
	@OneToOne
	@JoinColumn(name="Entite_rattachemt")
	private Entite entiteRattach;
	
	@OneToOne
	@JoinColumn(name="Util_cree")
	private Utilisateur utilCree;
	
	@Column(name="Date_cree")
	@Temporal(TemporalType.DATE)
	private Date dateCree;
	
	@OneToOne
	@JoinColumn(name="Util_modif")
	private Utilisateur utilModif;
	
	@Column(name="Date_dernier_modif")
	@Temporal(TemporalType.DATE)
	private Date dateDernierModif;
	
	@ManyToMany
	@JoinTable(name="Fonctions_Entites", 
	           joinColumns=@JoinColumn(name="entites"),
	           inverseJoinColumns=@JoinColumn(name="fonctions"))
	private Collection<Fonctions> fonctions;
	
//---------------------------------------------------------------------------------------------
	public Entite() {
		super();
	}

	public Entite(String codeEntite) {
		super();
		this.codeEntite = codeEntite;
	}

	public Entite(String codeEntite, Collection<LangueEntite> libEntite,
			int niveauOrg, char utilRattach, Entite entiteRattach,
			Utilisateur utilCree, Date dateCree) {
		super();
		this.codeEntite = codeEntite;
		this.libEntite = libEntite;
		this.niveauOrg = niveauOrg;
		this.utilRattach = utilRattach;
		this.entiteRattach = entiteRattach;
		this.utilCree = utilCree;
		this.dateCree = dateCree;
	}

	public String getCodeEntite() {
		return codeEntite;
	}

	public void setCodeEntite(String codeEntite) {
		this.codeEntite = codeEntite;
	}

	public Collection<LangueEntite> getLibEntite() {
		return libEntite;
	}

	public void setLibEntite(Collection<LangueEntite> libEntite) {
		this.libEntite = libEntite;
	}

	public int getNiveauOrg() {
		return niveauOrg;
	}

	public void setNiveauOrg(int niveauOrg) {
		this.niveauOrg = niveauOrg;
	}

	public char getUtilRattach() {
		return utilRattach;
	}

	public void setUtilRattach(char utilRattach) {
		this.utilRattach = utilRattach;
	}

	public Entite getEntiteRattach() {
		return entiteRattach;
	}

	public void setEntiteRattach(Entite entiteRattach) {
		this.entiteRattach = entiteRattach;
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

	public Date getDateDernierModif() {
		return dateDernierModif;
	}

	public void setDateDernierModif(Date dateDernierModif) {
		this.dateDernierModif = dateDernierModif;
	}

	public Collection<Fonctions> getFonctions() {
		return fonctions;
	}

	public void setFonctions(Collection<Fonctions> fonctions) {
		this.fonctions = fonctions;
	}
}
