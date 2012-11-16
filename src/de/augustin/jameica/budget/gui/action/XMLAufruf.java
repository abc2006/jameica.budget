// Diese Datei wird durch die Plugin.XML aus der Oberfläche heraus aufgerufen.
// Sie startet eigentlich erst die Arbeit des Plugins
package de.augustin.jameica.budget.gui.action;

import java.rmi.RemoteException;

import de.augustin.jameica.budget.rmi.DBValueInterface;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;


public class XMLAufruf implements de.willuhn.jameica.gui.Action // wir implementieren das Interface Action
{
	// weil das Interface Action implementiert ist, müssen wir eine funktion/Methode handleAction() definieren
	public void handleAction(Object context) throws ApplicationException
	{
		
		DBValueInterface i = null;

		// check if the context is a project
  	if (context != null && (context instanceof DBValueInterface))
  	{
      i = (DBValueInterface) context;
  	}
		else
		{
			try
			{
			  // create new project
				i = (DBValueInterface) de.augustin.jameica.budget.Settings.getDBService().createObject(DBValueInterface.class,null);
			}
			catch (RemoteException e)
			{
				throw new ApplicationException(de.augustin.jameica.budget.Settings.i18n().tr("error while creating new project"),e);
			}
		}
		
		//wegen startVIEW! wird hier natürlich auf die Datei unter de.augustin.jameica.neu.gui.view verwiesen
		GUI.startView(de.augustin.jameica.budget.gui.view.Ichbindiefahrzeugview.class, i);
		
	}
}