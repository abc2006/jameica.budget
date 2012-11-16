// diese Datei beinhaltet das, was in der View in der GUI dargestellt wird
package de.augustin.jameica.budget.gui.view;


import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;

import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.jameica.gui.util.TabGroup;
import de.willuhn.util.ApplicationException;



// wir müssen AbstractView erweitern, ich weiss nur noch nicht, warum
public class Ichbindiefahrzeugview extends de.willuhn.jameica.gui.AbstractView
{
	// da in der AbstractView die Methode bind() ohne geschweifte Klammern angegeben ist,
	//müssen wir diese nun hier definieren. Sie wird aufgerufen, wenn die View gestartet wird.
	public void bind() throws Exception
	{
		// nun wird der Titel gesetzt
		GUI.getView().setTitle("Die View wurde erfolgreich gestartet");
		
		
		// ein Control erstellen ... 
		final de.augustin.jameica.budget.gui.control.Datenbankkontrolle Datenbankkontrollobjekt = new de.augustin.jameica.budget.gui.control.Datenbankkontrolle(this);
		
		// bei Willuhns werden die INPUT-Felder jetzt anders erstellt, nämlich von der Kontrolle:
		de.willuhn.jameica.gui.util.Container neuercontainerfuerInputfelder = new de.willuhn.jameica.gui.util.SimpleContainer(getParent());  
		//wir versuchen uns mal an einem Input-Feld
		// hier werden die getter aus Datenbankkontrolle.java verwendet:
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getFuelDatE());
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getKmTotaL());
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getKmTriP());
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getPriceLiteR());
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getPriceTotaL());
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getConsumptioN());
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getStatioN());
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getNoticE());
		
		de.willuhn.jameica.gui.parts.ButtonArea buttonsaufdererstenview = new de.willuhn.jameica.gui.parts.ButtonArea();
		buttonsaufdererstenview.addButton(new de.willuhn.jameica.gui.internal.buttons.Back());
		buttonsaufdererstenview.addButton(de.augustin.jameica.budget.Settings.i18n().tr("Daten in Datenbank eintragen"), new de.willuhn.jameica.gui.Action()
		{
			public void handleAction(Object context) throws ApplicationException
			{
			Datenbankkontrollobjekt.handleStore();
			//new de.augustin.jameica.budget.gui.view.Ichbindieview()
			GUI.startView(de.augustin.jameica.budget.gui.view.Ichbindiefahrzeugview.class, null);
			}
		},null,true);
		buttonsaufdererstenview.addButton(de.augustin.jameica.budget.Settings.i18n().tr("aktualisieren"), new de.augustin.jameica.budget.gui.action.XMLAufruf(),null,true);
		
		//new de.augustin.jameica.budget.gui.view.Ichbindieview();
		Datenbankkontrollobjekt.getZeigMirDieTabelle().paint(this.getParent());
		
		buttonsaufdererstenview.paint(getParent());
	}
}