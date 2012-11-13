package de.augustin.jameica.budget.gui.dialog;

import java.text.DateFormat;
import java.text.DecimalFormat;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import java.util.*;

import de.augustin.jameica.budget.Plugin;
import de.augustin.jameica.budget.Settings;
import de.augustin.jameica.budget.gui.control.SummaryControl;
//import de.willuhn.jameica.example.gui.view.ProjectControl;
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
		DecimalFormat Liter = new DecimalFormat("##.##");
		DecimalInput liter = new DecimalInput(Liter);
		liter.setHint("liter");
		
		liter.paint(parent);
		
		
		final DateInput datum = new DateInput(new Date());
		datum.addListener(new org.eclipse.swt.widgets.Listener()
		{
			public void handleEvent(Event event)
			{
				Date value = (Date) datum.getValue();
				Application.getMessagingFactory().sendMessage(new StatusBarMessage(value.toString(),StatusBarMessage.TYPE_SUCCESS));
			}
		});
		
		datum.paint(parent);
		
		CheckboxInput full = new CheckboxInput(false);
		full.setName("Vollgetankt?");
		full.paint(parent);
		full.addListener(new Listener()
		{
			public void handleEvent(Event event)
			{
				new de.augustin.jameica.budget.gui.action.About();
			}
			
		});
		
		
		
		IntegerInput km_ges = new IntegerInput();
		km_ges.setHint("Bitte Kilometerstand eintragen");
		km_ges.paint(parent);
		
		IntegerInput km_tour = new IntegerInput();
		km_tour.setHint("Oder gefahrene Kilometer eintragen");
		km_tour.paint(parent);
		
		
		TextInput Tankstelle = new TextInput("");
		Tankstelle.setHint("Bitte Tankstelle angeben");
		Tankstelle.paint(parent);
		
		TextInput Notizen = new TextInput("");
		Notizen.setHint("Sonstiges (Öl, Reifen, usw) ");
		Notizen.paint(parent);
		
	    // Instantiate controller
	//    final SummaryControl control = new SummaryControl(AbstractView view);
		
		//IntegerInput km_tour = new IntegerInput();
		//km_tour.paint(parent);
		
				
/*		FormTextPart text = new FormTextPart();
		text.setText("<form>" +
			"<p><b>Jameica example plugin</b></p>" +
			"<br/>Licence: GPL (http://www.gnu.org/copyleft/gpl.html)" +
			"<br/><p>Copyright by Olaf Willuhn [info@jameica.org]</p>" +
			"<p>http://www.jameica.org</p>" +
			"</form>");

		text.paint(parent);
*/
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
		
		
		buttons.addButton("Nächster Eintrag", new Action()
		{
			public void handleAction(Object context) throws ApplicationException
			{
				//new 
				control.handleStore();
				close(); // hier muss dann natürlich in die Datenbank geschrieben werden
				Application.getMessagingFactory().sendMessage(new StatusBarMessage("Task stored successfully",StatusBarMessage.TYPE_SUCCESS));
				//Nun möchte ich bitte auch aus der Datenbank auslesen und die Anzeige im Hintergrund aktualisieren... Naja, erstmal die Datenbank ... 
				try {
					new EnterData(100).open();
					
					} catch (Exception e) {
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