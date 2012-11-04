package de.augustin.jameica.budget.gui.view;


import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.LabelGroup;


public class Summary extends AbstractView
{

	public void bind() throws Exception {
	
		// draw the title
		GUI.getView().setTitle("Summary");
		
	
        LabelGroup group = new LabelGroup(this.getParent(),"welcome");
		
		group.addText("this page intentionally left blank ;)",false);
		
		
		
		
		
	}
	
	
	
}