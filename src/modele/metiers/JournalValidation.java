package modele.metiers;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Journal_validation")
public class JournalValidation {
	
	@Embeddable
	public static class JournalValidationPK implements Serializable{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Utilisateur utilPK;
		@ManyToOne
		private Fonctionnalites fonctionnalitePK;
		
		public JournalValidationPK() {
			super();
		}
		public JournalValidationPK(Utilisateur utilPK,
				Fonctionnalites fonctionnalitePK) {
			super();
			this.utilPK = utilPK;
			this.fonctionnalitePK = fonctionnalitePK;
		}
		
		public boolean equals (Object obj) {
			//redéfinition equals() obligatoire
			return super.equals(obj);
		}
		
		public int hashCode () {
			//redéfinition hashcode() obligatoire
			return super.hashCode();
		}
		public Utilisateur getUtilPK() {
			return utilPK;
		}
		public void setUtilPK(Utilisateur utilPK) {
			this.utilPK = utilPK;
		}
		public Fonctionnalites getFonctionnalitePK() {
			return fonctionnalitePK;
		}
		public void setFonctionnalitePK(Fonctionnalites fonctionnalitePK) {
			this.fonctionnalitePK = fonctionnalitePK;
		}		
	}
	
	@EmbeddedId
	private JournalValidationPK id;
	
	@ManyToOne
	@JoinColumn(name="Code_util")
	private Utilisateur util;
	
	@ManyToOne
	@JoinColumn(name="Code_fonctionnalite")
	private Fonctionnalites fonctionnalite;
	
	@Column(name="Date_valid")
	@Temporal(TemporalType.DATE)
	private Date dateValid;
	
	@Column(name="Heuree_valid")
	@Temporal(TemporalType.TIME)
	private Date heureValid;
	
	@Column(name="Rang_valid")
	private int rangValid;
	
	@Column(name="num_dossier", length=100)
	private String numDossier;
	
	@Column(name="adress_IP")
	private String adressIP;

	public JournalValidation() {
		super();
	}

	public JournalValidation(Utilisateur util, Fonctionnalites fonctionnalite,
			Date dateValid, Date heureValid, int rangValid, String numDossier,
			String adressIP) {
		super();
		this.util = util;
		this.fonctionnalite = fonctionnalite;
		this.dateValid = dateValid;
		this.heureValid = heureValid;
		this.rangValid = rangValid;
		this.numDossier = numDossier;
		this.adressIP = adressIP;
	}

	public JournalValidationPK getId() {
		return id;
	}

	public void setId(JournalValidationPK id) {
		this.id = id;
	}

	public Utilisateur getUtil() {
		return util;
	}

	public void setUtil(Utilisateur util) {
		this.util = util;
	}

	public Fonctionnalites getFonctionnalite() {
		return fonctionnalite;
	}

	public void setFonctionnalite(Fonctionnalites fonctionnalite) {
		this.fonctionnalite = fonctionnalite;
	}

	public Date getDateValid() {
		return dateValid;
	}

	public void setDateValid(Date dateValid) {
		this.dateValid = dateValid;
	}

	public Date getHeureValid() {
		return heureValid;
	}

	public void setHeureValid(Date heureValid) {
		this.heureValid = heureValid;
	}

	public int getRangValid() {
		return rangValid;
	}

	public void setRangValid(int rangValid) {
		this.rangValid = rangValid;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	public String getAdressIP() {
		return adressIP;
	}

	public void setAdressIP(String adressIP) {
		this.adressIP = adressIP;
	}
	
	
}
