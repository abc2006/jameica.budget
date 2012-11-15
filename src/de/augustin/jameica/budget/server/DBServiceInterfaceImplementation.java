package de.augustin.jameica.budget.server;

import de.willuhn.jameica.system.Application;

public class DBServiceInterfaceImplementation extends de.willuhn.datasource.db.EmbeddedDBServiceImpl implements de.augustin.jameica.budget.rmi.DBServiceInterface
{
	public DBServiceInterfaceImplementation() throws java.rmi.RemoteException
	{
		super(Application.getPluginLoader().getPlugin(de.augustin.jameica.budget.Plugin.class).getResources().getWorkPath() + "/db/db.conf","neueruser", "neuespassword");
	    // We have to define jameicas classfinder.
	    // otherwise, the db service will not be able to find
	    // implementors by their interfaces.  
	    this.setClassFinder(Application.getClassLoader().getClassFinder());
	}
}