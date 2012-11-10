package de.augustin.jameica.budget.gui.view;


import de.augustin.jameica.budget.gui.control.SummaryControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ColumnLayout;
import de.willuhn.jameica.gui.util.Container;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.SimpleContainer;


public class Summary extends AbstractView
{

	public void bind() throws Exception {
	
		// draw the title
		GUI.getView().setTitle("Summary");
		
		// LabelGroup ist ein abgetrennter bereich mit 3D-Kante und Überschrift
        //LabelGroup group = new LabelGroup(this.getParent(),"welcome");
		//group.addText("this page intentionally left blank ;)",false);
		
		
		//################################## Ab hier wird eine zweispaltige Tabelle erstellt. Dies 
		// hat nichts mit dem Kopf zu tun ! 
		
	    Container c = new SimpleContainer(getParent());

	    // layout with 2 columns
	    ColumnLayout columns = new ColumnLayout(c.getComposite(),2);
	    	// left side
	    	Container left = new SimpleContainer(columns.getComposite());
	    	left.addHeadline("meine Headline");
		
	    	// right side
	    	Container right = new SimpleContainer(columns.getComposite(),true);
	    	right.addHeadline("Description");
		
	    // Zurück zum Container itself:
	    c.addHeadline("Summary");
	    // ##########leider wird die Tabelle selbst noch nicht ausgegeben
	    // ###########natürlich wird die Tabelle nicht ausgegeben, weil das da nur den Kopfbereich festlegt
	    
	    
	    // hier wird ein neues Control-Object Instantiert und gemalt ^^ ... 
	    SummaryControl control = new SummaryControl(this);
	    // Ich glaube, der Inhalt wird auch im getProjectsTable geholt ... 
		control.getProjectsTable().paint(this.getParent());
	
	
	
		}// public void bind
	
	
	
}