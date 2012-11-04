package de.augustin.jameica.budget.gui.action;

import de.willuhn.jameica.gui.Action;
import de.willuhn.util.ApplicationException;

public class About implements Action
{
	
	public void handleAction(Object context) throws ApplicationException
	{
		try {
			new de.augustin.jameica.budget.gui.dialog.About(100).open();
			//new de.augustin.jameica.budget.gui.dialog.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}