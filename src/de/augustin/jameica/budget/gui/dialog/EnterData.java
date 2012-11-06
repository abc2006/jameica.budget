package de.augustin.jameica.budget.gui.dialog;

import java.text.DecimalFormat;

import org.eclipse.swt.widgets.Composite;

import de.augustin.jameica.budget.Plugin;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.input.CheckboxInput;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.input.IntegerInput;
import de.willuhn.jameica.gui.input.LabelInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.jameica.gui.parts.FormTextPart;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;



public class EnterData extends AbstractDialog
{
	public EnterData(int position)
	{
		super(position);
		this.setTitle("Eingabe-Fenster");
		
	}
	protected void paint(Composite parent) throws Exception
	{
		
		DateInput datum = new DateInput();
		datum.setMandatory(true);
		//datum.setTitle("Hier bitte Datum angeben");
		//datum.addListener(null);
		datum.setComment("Datum");
		datum.paint(parent,100);
		
		
		CheckboxInput full = new CheckboxInput(false);
		full.setName("Vollgetankt?");
		full.paint(parent);
		
		
		DecimalFormat Liter = new DecimalFormat("##.##");
		DecimalInput liter = new DecimalInput(Liter);
		liter.setHint("liter");
		
		liter.paint(parent);
		
		IntegerInput km_ges = new IntegerInput();
		km_ges.setHint("Bitte Kilometerstand eintragen");
		km_ges.paint(parent);
		
		IntegerInput km_tour = new IntegerInput();
		km_tour.setHint("Oder gefahrene Kilometer eintragen");
		km_tour.paint(parent);
		
		
		TextInput Tankstelle = new TextInput("panelText");
		Tankstelle.setHint("Bitte Tankstelle angeben");
		Tankstelle.paint(parent);
		
		TextInput Notizen = new TextInput("scheiss hier");
		Notizen.setHint("Sonstiges (�l, Reifen, usw) ");
		Notizen.paint(parent);
		
		
		
		//IntegerInput km_tour = new IntegerInput();
		//km_tour.paint(parent);
		
				
		FormTextPart text = new FormTextPart();
		text.setText("<form>" +
			"<p><b>Jameica example plugin</b></p>" +
			"<br/>Licence: GPL (http://www.gnu.org/copyleft/gpl.html)" +
			"<br/><p>Copyright by Olaf Willuhn [info@jameica.org]</p>" +
			"<p>http://www.jameica.org</p>" +
			"</form>");

		text.paint(parent);

		LabelGroup group = new LabelGroup(parent, " Information ");

		AbstractPlugin p = Application.getPluginLoader().getPlugin(Plugin.class);

		group.addLabelPair("Version",new LabelInput(""+p.getManifest().getVersion()));
		group.addLabelPair("Working directory", new LabelInput(""+p.getResources().getWorkPath()));
		
		ButtonArea buttons = new ButtonArea();
		buttons.addButton("Close",new Action() 
	{
      public void handleAction(Object context) throws ApplicationException
      {
        close();
      }
    },null,true);
		
		
		buttons.addButton("N�chster Eintrag", new Action()
		{
			public void handleAction(Object context) throws ApplicationException
			{
				close(); // hier muss dann nat�rlich in die Datenbank geschrieben werden
				Application.getMessagingFactory().sendMessage(new StatusBarMessage("Task stored successfully",StatusBarMessage.TYPE_SUCCESS));
				//Nun m�chte ich bitte auch aus der Datenbank auslesen und die Anzeige im Hintergrund aktualisieren... Naja, erstmal die Datenbank ... 
				try {
					new de.augustin.jameica.budget.gui.dialog.EnterData(100).open();
					
					} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		},null,true);
		
		buttons.paint(parent);
		getShell().pack();

  }
	
	protected Object getData() throws Exception
	  {
	    return null;
	  }

	
	
}