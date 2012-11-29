
package de.augustin.jameica.budget.gui.action;

import de.augustin.jameica.budget.Settings;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class XMLAufrufCreateTableDialog implements Action
{

	public void handleAction(Object context) throws ApplicationException
  {
  	try
  	{
			new de.augustin.jameica.budget.gui.dialog.CreateTableDialog(AbstractDialog.POSITION_CENTER).open();
  	}
    catch (ApplicationException ae)
    {
      throw ae;
    }
  	catch (Exception e)
  	{
  		Logger.error("error while opening CreateTable dialog",e);
  		throw new ApplicationException(Settings.i18n().tr("Error while opening the CreateTable dialog"));
  	}
  }

}