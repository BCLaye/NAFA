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
@Table(name="Fonction")
public class Fonctions {
	
	@Id
	@Column(name="Code_fonction")
	private String code_fonction;
	
	@ManyToMany
	@JoinTable(name="Fonctions_Entites", 
	           joinColumns=@JoinColumn(name="fonctions"),
	           inverseJoinColumns=@JoinColumn(name="entites"))
	private Collection<Entite> entite;
	
	@OneToMany(mappedBy="fonction", cascade=CascadeType.ALL)
	private Collection<LangueFonctions> libelleFonctions;
	
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
	
//----------------------------------------------------------------------------------------------
	public Fonctions() {
		super();
	}

	public Fonctions(String code_fonction,
			Collection<LangueFonctions> libelleFonctions, Utilisateur utilCree,
			Date dateCree) {
		super();
		this.code_fonction = code_fonction;
		this.libelleFonctions = libelleFonctions;
		this.utilCree = utilCree;
		this.dateCree = dateCree;
	}

	public Fonctions(String code_fonction) {
		super();
		this.code_fonction = code_fonction;
	}

	public String getCode_fonction() {
		return code_fonction;
	}

	public void setCode_fonction(String code_fonction) {
		this.code_fonction = code_fonction;
	}

	public Collection<LangueFonctions> getLibelleFonctions() {
		return libelleFonctions;
	}

	public void setLibelleFonctions(Collection<LangueFonctions> libelleFonctions) {
		this.libelleFonctions = libelleFonctions;
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

	public Collection<Entite> getEntite() {
		return entite;
	}

	public void setEntite(Collection<Entite> entite) {
		this.entite = entite;
	}

}
