package de.augustin.jameica.budget.gui.dialog;

import org.eclipse.swt.widgets.Composite;

import de.augustin.jameica.budget.Plugin;
import de.augustin.jameica.budget.Settings;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.LabelInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.jameica.gui.parts.FormTextPart;
import de.willuhn.jameica.gui.util.Container;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.SimpleContainer;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;
import de.augustin.jameica.budget.gui.control.UnitsDBControl;

/**
 * Our "About..." dialog.
 */
public class CreateUnitDialog extends AbstractDialog
{

  /**
   * ct.
   * @param position
   */
  public CreateUnitDialog(int position)
  {
    super(position,true);
    this.setTitle(Settings.i18n().tr("About..."));
  }

  
  protected void paint(Composite parent) throws Exception
  {
		Container group = new SimpleContainer(parent);
		group.addHeadline("Überschrift des CreateTableDialogs");
		
		
		Input kennzeichen = new TextInput("");
		kennzeichen.setComment("Kennzeichen");
		group.addInput(kennzeichen);
		
		
		Input Name = new TextInput("");
		kennzeichen.setComment("Name");
		group.addInput(Name);
		
		// Jetzt habe ich zwei Inputs
		
		ButtonArea buttons = new ButtonArea();
		buttons.addButton(Settings.i18n().tr("Anlegen"),new Action() {
      public void handleAction(Object context) throws ApplicationException
      {
    	  //Speichere die Eingaben als neuen Eintrag in der "Verfügbare Fahrzeuge" Datenbank
    	  Datenbankkontrollobject.handleStore(); // oder so ähnlich ...
    	  
      }
    },null,true);
		
		
	buttons.addButton(Settings.i18n().tr("Abbrechen"),new Action() {
	  public void handleAction(Object context) throws ApplicationException
	  {
	        close();
	  }
	 },null,true);
		
		
		buttons.paint(parent);
		getShell().pack();

  }

  /**
   * @see de.willuhn.jameica.gui.dialogs.AbstractDialog#getData()
   */
  protected Object getData() throws Exception
  {
    return null;
  }

}


/**********************************************************************
 * $Log: About.java,v $
 * Revision 1.1  2010-11-09 17:20:16  willuhn
 * @N Beispiel-Plugin auf aktuellen Stand gebracht. Code-Cleanup und Beispiel-Implementierung fuer Search-API hinzugefuegt
 *
 **********************************************************************/