package modele.metiers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NomenclatureMDP")
public class NomenclatureMDP {
	
	@Id
	@Column(name="Id", nullable = false)
	private String id;
	
	@Column(name="Long_Min_MDP")
	private int longMinMDP;
	
	@Column(name="Nb_min_minusc")
	private int nbMinMinusc;
	
	@Column(name="Nb_min_majusc")
	private int nbMinMajusc;
	
	@Column(name="Nb_min_chiff")
	private int nbMinChiff;
	
	@Column(name="Nb_max_rep_car")
	private int nbMaxRepCar;  
	
	@Column(name="Nb_min_caract_spec")
	private int nbMincarSpec; 
	
	@Column(name="MDP_defaut")
	private String mDPdefaut;

	public NomenclatureMDP() {
		super();
	}
	
	public NomenclatureMDP(String id) {
		this.id = id;
	}

	public NomenclatureMDP(String id, int longMinMDP, int nbMinMinusc,
			int nbMinMajusc, int nbMinChiff, int nbMaxRepCar, int nbMaxRepSpec,
			String mDPdefaut) {
		super();
		this.id = id;
		this.longMinMDP = longMinMDP;
		this.nbMinMinusc = nbMinMinusc;
		this.nbMinMajusc = nbMinMajusc;
		this.nbMinChiff = nbMinChiff;
		this.nbMaxRepCar = nbMaxRepCar;
		this.nbMincarSpec = nbMaxRepSpec;
		this.mDPdefaut = mDPdefaut;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLongMinMDP() {
		return longMinMDP;
	}

	public void setLongMinMDP(int longMinMDP) {
		this.longMinMDP = longMinMDP;
	}

	public int getNbMinMinusc() {
		return nbMinMinusc;
	}

	public void setNbMinMinusc(int nbMinMinusc) {
		this.nbMinMinusc = nbMinMinusc;
	}

	public int getNbMinMajusc() {
		return nbMinMajusc;
	}

	public void setNbMinMajusc(int nbMinMajusc) {
		this.nbMinMajusc = nbMinMajusc;
	}

	public int getNbMinChiff() {
		return nbMinChiff;
	}

	public void setNbMinChiff(int nbMinChiff) {
		this.nbMinChiff = nbMinChiff;
	}

	public int getNbMaxRepCar() {
		return nbMaxRepCar;
	}

	public void setNbMaxRepCar(int nbMaxRepCar) {
		this.nbMaxRepCar = nbMaxRepCar;
	}

	public int getNbMincarSpec() {
		return nbMincarSpec;
	}

	public void setNbMincarSpec(int nbMincarSpec) {
		this.nbMincarSpec = nbMincarSpec;
	}

	public String getmDPdefaut() {
		return mDPdefaut;
	}

	public void setmDPdefaut(String mDPdefaut) {
		this.mDPdefaut = mDPdefaut;
	}

 
}
