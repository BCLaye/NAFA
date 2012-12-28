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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Menus")
public class Menus {
	
	@Id
	@Column(name="Code_menus", nullable = false, unique=true)
	private String codeMenus;
	
	@OneToMany(mappedBy="menus",cascade=CascadeType.ALL)
	private Collection<LangueMenus> libelles;
	
	@ManyToOne
	@JoinColumn(name="fonctionnalite")
	private Fonctionnalites fonctionnalite;
	
	@ManyToOne
	@JoinColumn(name="Profil")
	private Profil profil;
	
	//si le menus est rattaché au profil ou non
	@Column(name="Profil_effectif")
	private int profilEffectif;
	
	@ManyToMany
	@JoinTable(name="menus_menus", joinColumns=@JoinColumn(name="menusPere"),
    inverseJoinColumns=@JoinColumn(name="menusFils"))
	private Collection<Menus> menus;
	
	@ManyToMany
	@JoinTable(name="menus_menus", joinColumns=@JoinColumn(name="menusFils"),
    inverseJoinColumns=@JoinColumn(name="menusPere"))
	private Collection<Menus> listmenus;
	
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

	public Menus() {
		super();
	}

	public Menus(String codeMenus, Fonctionnalites fonctionnalite, Profil profil) {
		this.codeMenus = codeMenus;
		this.fonctionnalite = fonctionnalite;
		this.profil = profil;
		this.profilEffectif = 0;
	}

	public String getCodeMenus() {
		return codeMenus;
	}

	public void setCodeMenus(String codeMenus) {
		this.codeMenus = codeMenus;
	}

	public Collection<LangueMenus> getLibelles() {
		return libelles;
	}

	public void setLibelles(Collection<LangueMenus> libelles) {
		this.libelles = libelles;
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

	public int getProfilEffectif() {
		return profilEffectif;
	}

	public void setProfilEffectif(int profilEffectif) {
		this.profilEffectif = profilEffectif;
	}

	public Collection<Menus> getMenus() {
		return menus;
	}

	public void setMenus(Collection<Menus> menus) {
		this.menus = menus;
	}

	public Collection<Menus> getListmenus() {
		return listmenus;
	}

	public void setListmenus(Collection<Menus> listmenus) {
		this.listmenus = listmenus;
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

}
