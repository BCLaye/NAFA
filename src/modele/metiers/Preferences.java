package modele.metiers;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Preferences")
public class Preferences {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="themes")
	private String theme;
	
	@Column(name="Couleur")
	private String couleur;
	
	@OneToMany(mappedBy="preference")
	private Collection<Utilisateur> utilisateur;
	
	
//-----------------------------------------------------------------------------------------------
	public Preferences() {
		super();
	}

	public Preferences(String id, String theme, String couleur) {
	super();
	this.id = id;
	this.theme = theme;
	this.couleur = couleur;
}

	public Preferences(String theme, String couleur) {
		super();
		this.theme = theme;
		this.couleur = couleur;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Collection<Utilisateur> getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Collection<Utilisateur> utilisateur) {
		this.utilisateur = utilisateur;
	}
}