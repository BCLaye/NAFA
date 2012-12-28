package modele.dao;

import java.util.ArrayList;

import modele.metiers.Module;

public interface IModule {
	public void save(Module mod);
	public void delete(Module mod);
	public Module getModule(String id);
	public ArrayList<Module> allModule();
}
