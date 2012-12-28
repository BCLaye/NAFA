package modele.metiers;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Programme")
public class Programme {
	
	@Id
	@Column(name="Code_programme", nullable=false, unique=true)
	private String codeProgramme;
	
	@OneToMany(mappedBy="programme")
	private Collection<Fonctionnalites> fonctionnalites;

//------------------------------------------------------------------------------------------------------	
	public Programme() {
		super();
	}

	public Programme(String codeProgramme) {
		this.codeProgramme = codeProgramme;
	}

	public String getCodeProgramme() {
		return codeProgramme;
	}

	public void setCodeProgramme(String codeProgramme) {
		this.codeProgramme = codeProgramme;
	}

	public Collection<Fonctionnalites> getFonctionnalites() {
		return fonctionnalites;
	}

	public void setFonctionnalites(Collection<Fonctionnalites> fonctionnalites) {
		this.fonctionnalites = fonctionnalites;
	}
	
}
