package modele.metiers;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Module")
public class Module {
	
	@Id
	@Column(name="Code_module")
	private String codeModule;
	
	@OneToMany(mappedBy="module")
	private Collection<Fonctionnalites> fonctionnalites;

//----------------------------------------------------------------------------------------------------	
	public Module() {
		super();
	}

	public Module(String codeModule) {
		super();
		this.codeModule = codeModule;
	}

	public String getCodeModule() {
		return codeModule;
	}

	public void setCodeModule(String codeModule) {
		this.codeModule = codeModule;
	}

	public Collection<Fonctionnalites> getFonctionnalites() {
		return fonctionnalites;
	}

	public void setFonctionnalites(Collection<Fonctionnalites> fonctionnalites) {
		this.fonctionnalites = fonctionnalites;
	}
}
