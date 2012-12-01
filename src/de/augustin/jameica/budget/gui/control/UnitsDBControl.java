package de.augustin.jameica.budget.gui.control;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.augustin.jameica.budget.Settings;
import de.augustin.jameica.budget.rmi.DBUnitsInterface;
import de.augustin.jameica.budget.rmi.DBVehicleInterface;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class UnitsDBControl extends de.willuhn.jameica.gui.AbstractControl

{
	private de.willuhn.jameica.gui.parts.TablePart ausgeleseneTabelle;
	private de.willuhn.jameica.gui.input.TextInput name; 
	private de.willuhn.jameica.gui.input.TextInput numberplate; 

	
	public UnitsDBControl(de.willuhn.jameica.gui.AbstractView malwasanderesalsview)
	{
		// wovon ich aber immer noch nicht weis, was das hier jetzt genau macht ... 
		super(malwasanderesalsview);
	}
	///////////// ich weiss noch nicht genau, was das macht:
	 // private Task getTask()
	//	{
	//		if (task != null)
	//			return task;
	//		task = (Task) getCurrentObject();
	//		return task;
	//	}
	
		 /////////////////////Input-Getter
//################################################################################				
	public de.willuhn.jameica.gui.input.Input getNamE() throws RemoteException
	{
	if (name != null)
	      return name;
	    
		name = new TextInput("");
		name.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Name"));
		name.setName("Name des Fahrzeugs");
	    	return name;
	}
//################################################################################
	public de.willuhn.jameica.gui.input.Input getNumberPlatE() throws RemoteException
	{
	if (numberplate != null)
	      return numberplate;
	    
		numberplate = new TextInput("");
		numberplate.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Kennzeichen"));
		numberplate.setName("Kennzeichen des Fahrzeugs");
	    	return numberplate;
	}
//################################################################################	
	//////////////////Ende Input-Getter
	

	public void handleStore() throws ApplicationException 
	{
//		Application.getMessagingFactory().sendMessage(new StatusBarMessage("handleStore in Datenbankkontrolle, aufgerufen von Ichbindieview, Inhalt aus falschem zugriff" + notice.getValue() ,StatusBarMessage.TYPE_SUCCESS));
		de.augustin.jameica.budget.rmi.DBUnitsInterface nochnDBUInterface = getDBUnitsInterface();
		
		try 
		{ 
			
			nochnDBUInterface.setName((String) name.getValue());
			nochnDBUInterface.setNumberPlate((String) numberplate.getValue());
//	        Application.getMessagingFactory().sendMessage(new StatusBarMessage("Inhalt aus richtigem Zugriff getValuenachher" + (String) notice.getValue(),StatusBarMessage.TYPE_SUCCESS));
			this.view.reload();
			try 
			{
				nochnDBUInterface.store();
				Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr("Eintrag erfolgreich gespeichert"),StatusBarMessage.TYPE_SUCCESS));
			}
			catch (ApplicationException e)
		    {
				Application.getMessagingFactory().sendMessage(new StatusBarMessage(e.getMessage(),StatusBarMessage.TYPE_ERROR));
		    }
		}
		catch (RemoteException e)
		{
			Logger.error("error while storing project",e);
			Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr("Error while storing: {0}",e.getMessage()),StatusBarMessage.TYPE_ERROR));
		}
	}// handleStore()
	
	
	
	
	public de.willuhn.jameica.gui.Part getZeigMirDieTabelle() throws Exception
	{
		de.willuhn.datasource.rmi.DBService Datenbankservice = de.augustin.jameica.budget.Settings.getDBService();
		de.willuhn.datasource.rmi.DBIterator Datenbankiterator = Datenbankservice.createList(de.augustin.jameica.budget.rmi.DBVehicleInterface.class);
		ausgeleseneTabelle = new de.willuhn.jameica.gui.parts.TablePart(Datenbankiterator, new de.willuhn.jameica.gui.Action(){ public void handleAction(Object context){}});
		ausgeleseneTabelle.setMulti(true);
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Tankdatum"),"fueldate");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Gesamtkilometer"),"km_total");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Tageskilometer"),"km_trip");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Preis pro Liter"),"price_liter");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Preis Gesamt"),"price_total");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Verbrauch"),"consumption");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Tankstelle"),"station");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Notiz"),"notice");
		ausgeleseneTabelle.addSelectionListener(new Listener() {
		    public void handleEvent(Event event)
		    {
		      Object o = event.data;
		      String s = (String) ausgeleseneTabelle.getSelection().toString();
		//      Object p = event.
				Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr(s),StatusBarMessage.TYPE_ERROR));
		      // In event.data befindet sich dann direkt das markierte Fachobjekt. Falls mehrere markiert sind, ist o ein Array.
		    }
		  });
		
		
			return ausgeleseneTabelle;
	} //getZeigMirDieTabelle()
	
	
	
	
	private de.augustin.jameica.budget.rmi.DBUnitsInterface Datenbankeintragungsinterface;
	
	private de.augustin.jameica.budget.rmi.DBUnitsInterface getDBUnitsInterface()
	  {
	    if ( Datenbankeintragungsinterface != null)
	      return Datenbankeintragungsinterface;
	    Datenbankeintragungsinterface = (de.augustin.jameica.budget.rmi.DBUnitsInterface) getCurrentObject();
	    return Datenbankeintragungsinterface;
	  }

}