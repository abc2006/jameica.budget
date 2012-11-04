package de.augustin.jameica.budget.gui.action;

import de.willuhn.jameica.gui.Action;
import de.willuhn.util.ApplicationException;

public class EnterData implements Action
{
	
	public void handleAction(Object context) throws ApplicationException 
	{
		
		try {
			new de.augustin.jameica.budget.gui.dialog.EnterData(100).open();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}