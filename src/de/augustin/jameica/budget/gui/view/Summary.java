package de.augustin.jameica.budget.gui.view;


import de.augustin.jameica.budget.gui.control.SummaryControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.jameica.gui.util.ColumnLayout;
import de.willuhn.jameica.gui.util.Container;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.SimpleContainer;
import de.willuhn.util.ApplicationException;


public class Summary extends AbstractView
{

	public void bind() throws Exception {
	
		// draw the title
		GUI.getView().setTitle("Eintragungsformular");
		
		// LabelGroup ist ein abgetrennter bereich mit 3D-Kante und Überschrift
        //LabelGroup group = new LabelGroup(this.getParent(),"welcome");
		//group.addText("this page intentionally left blank ;)",false);
		// Instantiate controller
	    final SummaryControl control = new SummaryControl(this);
		
		//################################## Ab hier wird eine zweispaltige Tabelle erstellt. Dies 
		// hat nichts mit dem Kopf zu tun ! 
		
	    Container c = new SimpleContainer(getParent());
	    c.addHeadline("Summary");
	    // layout with 2 columns
	    ColumnLayout columns = new ColumnLayout(c.getComposite(),2);
	    	// left side
	 // left side
	    Container left = new SimpleContainer(columns.getComposite());
	    left.addHeadline(de.augustin.jameica.budget.Settings.i18n().tr("linke Seite"));
	    left.addInput(control.getFuelDate());
	    left.addInput(control.getKm_Total());
	    left.addInput(control.getKm_Trip());
	    left.addInput(control.getPrice_Total());

	    	// right side
	    Container right = new SimpleContainer(columns.getComposite());
	    right.addHeadline(de.augustin.jameica.budget.Settings.i18n().tr("rechte Seite"));
	    right.addInput(control.getPrice_Liter());
	    right.addInput(control.getStation());
	    right.addInput(control.getConsumption());
	    right.addInput(control.getComments());
	    
	    ButtonArea buttons = new ButtonArea();
	    buttons.addButton(new Back());
	    //buttons.addButton(Settings.i18n().tr("Delete"),  	new ProjectDelete(),control.getCurrentObject());
	    buttons.addButton(de.augustin.jameica.budget.Settings.i18n().tr("Store"),   	new Action()
	    {
	      public void handleAction(Object context) throws ApplicationException
	      {
	        control.handleStore();
	      }
	    },null,true); // "true" defines this button as the default button

	    // Don't forget to paint the button area
	    buttons.paint(getParent());    
	    
	    
		control.getCarTable().paint(this.getParent());
	
	
	
		}// public void bind
	
	
	
}