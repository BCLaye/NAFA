package modele.dao;

import java.util.ArrayList;

import modele.metiers.HistoriqueMDP;

public interface IHistoriqueMPD {
	public void save(HistoriqueMDP hpass);
	public void delete(HistoriqueMDP hpass);
	public HistoriqueMDP getHistoriqueMDP(String codeUtil);
	public ArrayList<HistoriqueMDP> allHistoriqueMDP();
}
