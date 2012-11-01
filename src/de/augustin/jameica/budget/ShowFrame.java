package de.augustin.jameica.budget;

import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class ShowFrame implements Action
{
	
	public void handleAction(Object context) throws ApplicationException
	{
		
		GUI.startView(de.augustin.jameica.budget.MakeFrame.class.getName(), null);
	}
	
}