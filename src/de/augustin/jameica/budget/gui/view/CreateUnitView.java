//diese Datei beinhaltet das, was in der View in der GUI dargestellt wird
package de.augustin.jameica.budget.gui.view;


import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;

import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.jameica.gui.util.ColumnLayout;
import de.willuhn.jameica.gui.util.Container;
import de.willuhn.jameica.gui.util.SimpleContainer;
import de.willuhn.jameica.gui.util.TabGroup;
import de.willuhn.util.ApplicationException;



//wir müssen AbstractView erweitern, weil das die Parent-Klasse ist
public class CreateUnitView extends de.willuhn.jameica.gui.AbstractView
{
	// da in der AbstractView die Methode bind() ohne geschweifte Klammern angegeben ist,
	//müssen wir diese nun hier definieren. Sie wird aufgerufen, wenn die View gestartet wird.
	public void bind() throws Exception
	{
		// nun wird der Titel gesetzt
		GUI.getView().setTitle("Vehicle Management");
		
		// ein Control erstellen ... 
		final de.augustin.jameica.budget.gui.control.UnitsDBControl Datenbankkontrollobjekt = new de.augustin.jameica.budget.gui.control.UnitsDBControl(this);
		
		// bei Willuhns werden die INPUT-Felder jetzt anders erstellt, nämlich von der Kontrolle:
		de.willuhn.jameica.gui.util.Container neuercontainerfuerInputfelder = new de.willuhn.jameica.gui.util.SimpleContainer(getParent());  
		
		//
		ColumnLayout top = new ColumnLayout(neuercontainerfuerInputfelder.getComposite(),1);
		Container topcenter = new SimpleContainer(top.getComposite());
		topcenter.addInput(Datenbankkontrollobjekt.getNamE());
		topcenter.addInput(Datenbankkontrollobjekt.getNumberPlatE());
		
		de.willuhn.jameica.gui.parts.ButtonArea buttonsaufdererstenview = new de.willuhn.jameica.gui.parts.ButtonArea();
		buttonsaufdererstenview.addButton(de.augustin.jameica.budget.Settings.i18n().tr("Daten in Datenbank eintragen"), new de.willuhn.jameica.gui.Action()
		{
			public void handleAction(Object context) throws ApplicationException
			{
			Datenbankkontrollobjekt.handleStore();
			//new de.augustin.jameica.budget.gui.view.Ichbindieview()
			GUI.startView(de.augustin.jameica.budget.gui.view.CreateUnitView.class, null);
			}
		},null,true);
		
		buttonsaufdererstenview.addButton(de.augustin.jameica.budget.Settings.i18n().tr("aktualisieren"), new de.augustin.jameica.budget.gui.action.XMLAufrufVehicle(),null,false);
		
		//new de.augustin.jameica.budget.gui.view.Ichbindieview();
		Datenbankkontrollobjekt.getZeigMirDieTabelle().paint(this.getParent());
		
		buttonsaufdererstenview.paint(getParent());
	}
}