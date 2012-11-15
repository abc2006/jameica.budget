// diese Datei beinhaltet das, was in der View in der GUI dargestellt wird
package de.augustin.jameica.budget.gui.view;


import de.willuhn.jameica.gui.GUI;



// wir m�ssen AbstractView erweitern, ich weiss nur noch nicht, warum
public class Ichbindieview extends de.willuhn.jameica.gui.AbstractView
{
	// da in der AbstractView die Methode bind() ohne geschweifte Klammern angegeben ist,
	//m�ssen wir diese nun hier definieren. Sie wird aufgerufen, wenn die View gestartet wird.
	public void bind() throws Exception
	{
		// nun wird der Titel gesetzt
		GUI.getView().setTitle("Die View wurde erfolgreich gestartet");
		
		// ein Control erstellen ... 
		final de.augustin.jameica.budget.gui.control.Datenbankkontrolle Datenbankkontrollobjekt = new de.augustin.jameica.budget.gui.control.Datenbankkontrolle(this);
		
		// bei Willuhns werden die INPUT-Felder jetzt anders erstellt, n�mlich von der Kontrolle:
		de.willuhn.jameica.gui.util.Container neuercontainerfuerInputfelder = new de.willuhn.jameica.gui.util.SimpleContainer(getParent());  
		//wir versuchen uns mal an einem Input-Feld
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getDenScheissText());
		neuercontainerfuerInputfelder.addInput(Datenbankkontrollobjekt.getFuelDate());
		
		de.willuhn.jameica.gui.parts.ButtonArea buttonsaufdererstenview = new de.willuhn.jameica.gui.parts.ButtonArea();
		buttonsaufdererstenview.addButton(new de.willuhn.jameica.gui.internal.buttons.Back());
		buttonsaufdererstenview.addButton(de.augustin.jameica.budget.Settings.i18n().tr("Daten in Datenbank eintragen"), new de.willuhn.jameica.gui.Action()
		{
			public void handleAction(Object context)
			{
			Datenbankkontrollobjekt.handleStore();
			//Datenbankkontrollobjekt.getZeigMirDieTabelle().paint(this.view.getParent());
			}
		},null,true);
		
		Datenbankkontrollobjekt.getZeigMirDieTabelle().paint(this.getParent());
		
		buttonsaufdererstenview.paint(getParent());
	}
}