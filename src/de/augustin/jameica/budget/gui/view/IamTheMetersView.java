package de.augustin.jameica.budget.gui.view;

import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class IamTheMetersView extends de.willuhn.jameica.gui.AbstractView
{
	public void bind() throws Exception
	{
		
		GUI.getView().setTitle("Meters Management");
		final de.augustin.jameica.budget.gui.control.MetersDBControl Datenbankkontrollobjekt = new de.augustin.jameica.budget.gui.control.MetersDBControl(this);
		
		// bei Willuhns werden die INPUT-Felder jetzt anders erstellt, nämlich von der Kontrolle:
		de.willuhn.jameica.gui.util.Container newInputcontainer = new de.willuhn.jameica.gui.util.SimpleContainer(getParent());  
		//wir versuchen uns mal an einem Input-Feld
		// hier werden die getter aus Datenbankkontrolle.java verwendet:
		newInputcontainer.addInput(Datenbankkontrollobjekt.getReadingDatE());
		newInputcontainer.addInput(Datenbankkontrollobjekt.getMeterReadinG());
		newInputcontainer.addInput(Datenbankkontrollobjekt.getNewMeteR());
		newInputcontainer.addInput(Datenbankkontrollobjekt.getConsumptioN());
		newInputcontainer.addInput(Datenbankkontrollobjekt.getNoticE());
		
		de.willuhn.jameica.gui.parts.ButtonArea buttonsaufdererstenview = new de.willuhn.jameica.gui.parts.ButtonArea();
		buttonsaufdererstenview.addButton(new de.willuhn.jameica.gui.internal.buttons.Back());
		buttonsaufdererstenview.addButton(de.augustin.jameica.budget.Settings.i18n().tr("Daten in Datenbank eintragen"), new de.willuhn.jameica.gui.Action()
		{
			public void handleAction(Object context) throws ApplicationException
			{
			Datenbankkontrollobjekt.handleStore();
			//new de.augustin.jameica.budget.gui.view.Ichbindieview()
			GUI.startView(de.augustin.jameica.budget.gui.view.IamTheMetersView.class, null);
			}
		},null,true);
		buttonsaufdererstenview.addButton(de.augustin.jameica.budget.Settings.i18n().tr("aktualisieren"), new de.augustin.jameica.budget.gui.action.XMLAufrufMeters(),null,true);
		
		//new de.augustin.jameica.budget.gui.view.Ichbindieview();
		Datenbankkontrollobjekt.getZeigMirDieTabelle().paint(this.getParent());
		
		buttonsaufdererstenview.paint(getParent());

	}
	
}