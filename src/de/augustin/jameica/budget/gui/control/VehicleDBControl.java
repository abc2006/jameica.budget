package de.augustin.jameica.budget.gui.control;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.MultiInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class VehicleDBControl extends de.willuhn.jameica.gui.AbstractControl

{
	private de.willuhn.jameica.gui.parts.TablePart ausgeleseneTabelle;
	private de.willuhn.jameica.gui.input.SelectInput vehicle;
	private de.willuhn.jameica.gui.input.TextInput notice; 
	private de.willuhn.jameica.gui.input.TextInput station; 
	private de.willuhn.jameica.gui.input.DateInput datum;
	private de.willuhn.jameica.gui.input.DecimalInput kmtotal;
	private de.willuhn.jameica.gui.input.DecimalInput kmtrip;
	private de.willuhn.jameica.gui.input.DecimalInput priceliter;
	private de.willuhn.jameica.gui.input.DecimalInput pricetotal;
	private de.willuhn.jameica.gui.input.DecimalInput consumption;

	
	public VehicleDBControl(de.willuhn.jameica.gui.AbstractView malwasanderesalsview)
	{
		// wovon ich aber immer noch nicht weis, was das hier jetzt genau macht ... 
		super(malwasanderesalsview);
	}
	
	 /////////////////////Input-Getter

		public de.willuhn.jameica.gui.input.Input getVehiclE() throws RemoteException
		{
		Object[] vh = new Object[ 3 ]; 
		vh[0] = "Motorrad AB-AR 7"; 
		vh[1] = "Auto AB-NV 61"; 
		vh[2] = "Fahrrad";
		
		
			vehicle = new de.willuhn.jameica.gui.input.SelectInput(vh,null);
//			priceliter.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Preis pro Liter"));
			//priceliter.
			vehicle.setName("Fahrzeug");
		    	return vehicle;	
		}
	//################################################################################			
	public de.willuhn.jameica.gui.input.Input getFuelDatE() throws RemoteException
	{
	if (datum != null)
	      return datum;
	    
	    datum = new DateInput(new Date(),de.augustin.jameica.budget.Settings.DATEFORMAT);
	    datum.setName(de.augustin.jameica.budget.Settings.i18n().tr("Tankdatum"));
	    datum.focus();
	    // leicht kontraproduktiv ... 
	    //datum.addListener(new Listener(){public void handleEvent(Event event){datum.setValue(null);}});
	    
	    	return datum;
	}
//################################################################################
	public de.willuhn.jameica.gui.input.Input getKmTotaL() throws RemoteException
	{
	if (kmtotal != null)
	      return kmtotal;
	    
	    kmtotal = new DecimalInput(de.augustin.jameica.budget.Settings.DECIMALFORMAT_0NK);
	    kmtotal.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Gesamtkilometer"));
	    kmtotal.setName("Gesamtkilometer");
	    kmtotal.addListener(new Listener() { // dieser Listener berechnet die Aufwände im Projekt-Details-Fenster
	        public void handleEvent(Event event)
	        {
	          try
	          {
	              Double d = (Double) getKmTotaL().getValue();
	              if (d == null)
	                return;
	              
	              double p = d.doubleValue();
	              if (Double.isNaN(p))
	                return;
	              
	              double sum = 10 * p;
	              
	            getKmTriP().setValue(de.augustin.jameica.budget.Settings.DECIMALFORMAT_0NK.format(sum));
	          }
	          catch (Exception e)
	          {
	            Logger.error("error while setting kmtrip",e);
	            Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr("Error while setting kmtrip: {0}",e.getMessage()),StatusBarMessage.TYPE_ERROR));
	          }
	        }
	      });
	    	return kmtotal;
	}
//################################################################################	
	public de.willuhn.jameica.gui.input.Input getKmTriP() throws RemoteException
	{
	if (kmtrip != null)
	      return kmtrip;
	    
		kmtrip = new DecimalInput(de.augustin.jameica.budget.Settings.DECIMALFORMAT_0NK);
		kmtrip.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Tageskilometer"));
		kmtrip.setName("Tageskilometer");
	    	return kmtrip;
	}
//################################################################################		
	public de.willuhn.jameica.gui.input.Input getPriceLiteR() throws RemoteException
	{
	if (priceliter != null)
	      return priceliter;
    
		priceliter = new DecimalInput(de.augustin.jameica.budget.Settings.DECIMALFORMAT_2NK);
		priceliter.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Preis pro Liter"));
		priceliter.setName("Preis pro Liter");
	    	return priceliter;
	}
//################################################################################			
	public de.willuhn.jameica.gui.input.Input getPriceTotaL() throws RemoteException
	{
	if (pricetotal != null)
	      return pricetotal;
	    
		pricetotal = new DecimalInput(de.augustin.jameica.budget.Settings.DECIMALFORMAT_2NK);
		pricetotal.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Preis Gesamt"));
		pricetotal.setName("Gesamtpreis");
	    	return pricetotal;
	}
//################################################################################			
	public de.willuhn.jameica.gui.input.Input getConsumptioN() throws RemoteException
	{
	if (consumption != null)
	      return consumption;
	    
		consumption = new DecimalInput(de.augustin.jameica.budget.Settings.DECIMALFORMAT_2NK);
		consumption.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Verbrauch pro 100km"));
		consumption.setName("Verbrauch l/100km");
	    	return consumption;
	}
//################################################################################				
	public de.willuhn.jameica.gui.input.Input getStatioN() throws RemoteException
	{
	if (station != null)
	      return station;
	    
		station = new TextInput("");
		station.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Tankstelle"));
		station.setName("Tankstelle");
	    	return station;
	}
//################################################################################
	public de.willuhn.jameica.gui.input.Input getNoticE() throws RemoteException
	{
	if (notice != null)
	      return notice;
	    
		notice = new TextInput("");
		notice.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Notizen"));
		notice.setName("Notizen");
	    	return notice;
	}
//################################################################################	
	//////////////////Ende Input-Getter
	

	public void handleStore() throws ApplicationException 
	{
//		Application.getMessagingFactory().sendMessage(new StatusBarMessage("handleStore in Datenbankkontrolle, aufgerufen von Ichbindieview, Inhalt aus falschem zugriff" + notice.getValue() ,StatusBarMessage.TYPE_SUCCESS));
		de.augustin.jameica.budget.rmi.DBVehicleInterface nochnDBVInterface = getDatenbankeintragungsinterface();
		
		try 
		{ 
			
			nochnDBVInterface.setFuelDate((Date) datum.getValue());
			nochnDBVInterface.setKmTotal((double) kmtotal.getValue());
			nochnDBVInterface.setKmTrip((double) kmtrip.getValue());
			nochnDBVInterface.setPriceLiter((double) priceliter.getValue());
			nochnDBVInterface.setPriceTotal((double) pricetotal.getValue());
			nochnDBVInterface.setConsumption((double) consumption.getValue());
			nochnDBVInterface.setStation((String) station.getValue());
			nochnDBVInterface.setNotice((String) notice.getValue());
//	        Application.getMessagingFactory().sendMessage(new StatusBarMessage("Inhalt aus richtigem Zugriff getValuenachher" + (String) notice.getValue(),StatusBarMessage.TYPE_SUCCESS));
			this.view.reload();
			try 
			{
				nochnDBVInterface.store();
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
		//      Object p = event.
				Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr(event.toString()),StatusBarMessage.TYPE_ERROR));
		      // In event.data befindet sich dann direkt das markierte Fachobjekt. Falls mehrere markiert sind, ist o ein Array.
		    }
		  });
		
		
			return ausgeleseneTabelle;
	} //getZeigMirDieTabelle()
	
	
	
	
	private de.augustin.jameica.budget.rmi.DBVehicleInterface Datenbankeintragungsinterface;
	
	private de.augustin.jameica.budget.rmi.DBVehicleInterface getDatenbankeintragungsinterface()
	  {
	    if ( Datenbankeintragungsinterface != null)
	      return Datenbankeintragungsinterface;
	    Datenbankeintragungsinterface = (de.augustin.jameica.budget.rmi.DBVehicleInterface) getCurrentObject();
	    return Datenbankeintragungsinterface;
	  }

}