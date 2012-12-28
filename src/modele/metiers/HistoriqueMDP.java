package modele.metiers;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HistoriqueMDP")
public class HistoriqueMDP {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="Code_util")
	private Utilisateur util;
	
	@Column(name="MDP_crypte")
	private String motPass;
	
	@Column(name="Date_cree")
	@Temporal(TemporalType.DATE)
	private Date dateCree;
	
	@Column(name="Heuree_cree")
	@Temporal(TemporalType.TIME)
	private Date heureCree;

	public HistoriqueMDP() {
		super();
	}

	public HistoriqueMDP(Utilisateur util, String motPass, Date dateCree,
			Date heureCree) {
		super();
		this.util = util;
		this.motPass = motPass;
		this.dateCree = dateCree;
		this.heureCree = heureCree;
	}

	public Utilisateur getUtil() {
		return util;
	}

	public void setUtil(Utilisateur util) {
		this.util = util;
	}

	public String getMotPass() {
		return motPass;
	}

	public void setMotPass(String motPass) {
		this.motPass = motPass;
	}

	public Date getDateCree() {
		return dateCree;
	}

	public void setDateCree(Date dateCree) {
		this.dateCree = dateCree;
	}

	public Date getHeureCree() {
		return heureCree;
	}

	public void setHeureCree(Date heureCree) {
		this.heureCree = heureCree;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
