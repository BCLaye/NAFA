package modele.dao;

import java.util.ArrayList;

import modele.metiers.JournalValidation;

public interface IJournalValidation {
	public void save(JournalValidation jv);
	public void delete(JournalValidation jv);
	public JournalValidation getJournalValidation(String codeUtil, String codeFct);
	public ArrayList<JournalValidation> allJournalValidation();
}
