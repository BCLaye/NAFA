package modele.metiers;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="langue_menus")
public class LangueMenus{
	
	@Embeddable
	public static class LangueMenusPK implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		@ManyToOne
		private Langue languePK;		
		@ManyToOne
		private Menus menusPK;		
		
		public LangueMenusPK() {
			super();
		}

		public LangueMenusPK(Langue languePK, Menus menusPK) {
			super();
			this.languePK = languePK;
			this.menusPK = menusPK;
		}

		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}

		public Langue getLanguePK() {
			return languePK;
		}

		public void setLanguePK(Langue languePK) {
			this.languePK = languePK;
		}

		public Menus getMenusPK() {
			return menusPK;
		}

		public void setMenusPK(Menus menusPK) {
			this.menusPK = menusPK;
		}		
	}
	
	@EmbeddedId
	private LangueMenusPK id;
	
	@ManyToOne
	@JoinColumn(name="code_langue")
	private Langue langue;
	
	@ManyToOne
	@JoinColumn(name="code_menus")
	private Menus menus;
	
	@Column(name="libelle_menus")
	private String libMenus;
	
	public LangueMenus(Langue langue, Menus menus, String libMenus) {
		this.id = new LangueMenusPK(langue, menus);
		this.langue = langue;
		this.menus = menus;
		this.libMenus = libMenus;
	}
	
	public LangueMenus(Langue langue, Menus menus) {
		this.id = new LangueMenusPK(langue, menus);
		this.langue = langue;
		this.menus = menus;
	}

	public LangueMenus() {
		this.id = new LangueMenusPK();
	}	

	public Langue getLangue() {
		return langue;
	}

	public void setLangue(Langue langue) {
		this.id.languePK = langue;
		this.langue = langue;
	}

	public Menus getMenus() {
		return menus;
	}

	public void setMenus(Menus menus) {
		this.id.menusPK = menus;
		this.menus = menus;
	}

	public String getLibMenus() {
		return libMenus;
	}

	public void setLibMenus(String libMenus) {
		this.libMenus = libMenus;
	}
	
	
}
