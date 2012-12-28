package modele.dao;

import java.util.ArrayList;

import modele.metiers.NomenclatureMDP;

public interface INomenclatureMDP {
	public void save(NomenclatureMDP nomMDP);
	public void delete(NomenclatureMDP nomMDP);
	public NomenclatureMDP getNomenclatureMDP(String id);
	public ArrayList<NomenclatureMDP> allNomenclatureMDP();
}
