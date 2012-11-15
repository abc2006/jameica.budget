// dies ist die initiale Plugin-Datei. Sie wird von Jameica aufgerufen, um das Plugin zu starten

package de.augustin.jameica.budget;



// diese Datei initialisiert das Plugin. Sie ruft init, install und/oder clean auf. Danach ist diese Datei ohne funktion.
import java.io.File;

import de.willuhn.datasource.db.EmbeddedDatabase;
import de.willuhn.jameica.plugin.AbstractPlugin; 
import de.willuhn.jameica.plugin.Manifest;
import de.willuhn.jameica.plugin.PluginResources;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;
import de.augustin.jameica.budget.Plugin;

public class Plugin extends AbstractPlugin
{
	  public void install() throws ApplicationException
	  {

			// If we are running in client/server mode and this instance
			// is the client, we do not need to create a database.
			// Instead of this we will get our objects via RMI from
			// the server
			if (Application.inClientMode())
				return;

	    try {

				// Let's create an embedded Database
				PluginResources res = Application.getPluginLoader().getPlugin(Plugin.class).getResources();
				Manifest mf = Application.getPluginLoader().getManifest(Plugin.class);
				EmbeddedDatabase db = new EmbeddedDatabase(res.getWorkPath() + "/db","neueruser","neuespassword");

				// create the sql tables.
	      db.executeSQLScript(new File(mf.getPluginDir() + File.separator + "sql","create.sql"));

				// That's all. Database installed and tables created ;)
	    }
	    catch (Exception e)
	    {
	    	throw new ApplicationException("error while installing plugin",e);
	    }
	  }

}