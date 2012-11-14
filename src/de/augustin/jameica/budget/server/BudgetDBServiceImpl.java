package de.augustin.jameica.budget.server;

import java.rmi.RemoteException;

import de.willuhn.datasource.db.EmbeddedDBServiceImpl;
import de.augustin.jameica.budget.Plugin;
import de.augustin.jameica.budget.rmi.BudgetDBService;
import de.willuhn.jameica.system.Application;

/**
 * this is our database service which can work over RMI.
 */
public class BudgetDBServiceImpl extends EmbeddedDBServiceImpl implements BudgetDBService
{
  /**
   * ct.
   * @throws RemoteException
   */
  public BudgetDBServiceImpl() throws RemoteException
  {
    super(Application.getPluginLoader().getPlugin(Plugin.class).getResources().getWorkPath() + "/db/db.conf",
    			"budgetuser", "budgetpassword");

    // We have to define jameicas classfinder.
    // otherwise, the db service will not be able to find
    // implementors by their interfaces.  
    this.setClassFinder(Application.getClassLoader().getClassFinder());
  }
}