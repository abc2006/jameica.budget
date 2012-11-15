package de.augustin.jameica.budget.gui.action;

import de.augustin.jameica.neu.rmi.DBValueInterface;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;

public class Dateneintragen implements de.willuhn.jameica.gui.Action
{
	public void handleAction(Object context)
	{
		
		Application.getMessagingFactory().sendMessage(new StatusBarMessage("handleAction in Dateneintragen",StatusBarMessage.TYPE_SUCCESS));

	}
}