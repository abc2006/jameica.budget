// Diese Datei wird durch die Plugin.XML aus der Oberfl�che heraus aufgerufen.
// Sie startet eigentlich erst die Arbeit des Plugins
package de.augustin.jameica.budget.gui.action;

import java.rmi.RemoteException;

import de.augustin.jameica.budget.rmi.DBMetersInterface;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;


public class XMLAufrufMeters implements de.willuhn.jameica.gui.Action // wir implementieren das Interface Action
{
	// weil das Interface Action implementiert ist, m�ssen wir eine funktion/Methode handleAction() definieren
	public void handleAction(Object context) throws ApplicationException
	{
		
		DBMetersInterface i = null;

		// check if the context is a project
  	if (context != null && (context instanceof DBMetersInterface))
  	{
      i = (DBMetersInterface) context;
  	}
		else
		{
			try
			{
			  // create new project
				i = (DBMetersInterface) de.augustin.jameica.budget.Settings.getDBService().createObject(DBMetersInterface.class,null);
			}
			catch (RemoteException e)
			{
				throw new ApplicationException(de.augustin.jameica.budget.Settings.i18n().tr("error while creating new DBMetersObject"),e);
			}
		}
		
		//wegen startVIEW! wird hier nat�rlich auf die Datei unter de.augustin.jameica.neu.gui.view verwiesen
		GUI.startView(de.augustin.jameica.budget.gui.view.IamTheMetersView.class, i);
		
	}
}