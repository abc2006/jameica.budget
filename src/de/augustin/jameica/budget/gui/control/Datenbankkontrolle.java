package de.augustin.jameica.budget.gui.control;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class Datenbankkontrolle extends de.willuhn.jameica.gui.AbstractControl

{
	private de.willuhn.jameica.gui.parts.TablePart ausgeleseneTabelle;
	private de.willuhn.jameica.gui.input.TextInput notice; 
	private de.willuhn.jameica.gui.input.TextInput station; 
	private de.willuhn.jameica.gui.input.DateInput datum;
	private de.willuhn.jameica.gui.input.DecimalInput kmtotal;
	private de.willuhn.jameica.gui.input.DecimalInput kmtrip;
	private de.willuhn.jameica.gui.input.DecimalInput priceliter;
	private de.willuhn.jameica.gui.input.DecimalInput pricetotal;
	private de.willuhn.jameica.gui.input.DecimalInput consumption;


	
	public Datenbankkontrolle(de.willuhn.jameica.gui.AbstractView malwasanderesalsview)
	{
		// wovon ich aber immer noch nicht weis, was das hier jetzt genau macht ... 
		super(malwasanderesalsview);
	}
	
	 /////////////////////Input-Getter
	public de.willuhn.jameica.gui.input.DateInput getFuelDatE() throws RemoteException
	{
	if (datum != null)
	      return datum;
	    
	    datum = new DateInput(getDatenbankeintragungsinterface().getFuelDate(),de.augustin.jameica.budget.Settings.DATEFORMAT);
	    datum.setName(de.augustin.jameica.budget.Settings.i18n().tr("Tankdatum"));
	    	return datum;
	}
//################################################################################
	public de.willuhn.jameica.gui.input.DecimalInput getKmTotaL() throws RemoteException
	{
	if (kmtotal != null)
	      return kmtotal;
	    
	    kmtotal = new DecimalInput(getDatenbankeintragungsinterface().getKmTotal(),de.augustin.jameica.budget.Settings.DECIMALFORMAT);
	    kmtotal.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Gesamtkilometer"));
	    	return kmtotal;
	}
//################################################################################	
	public de.willuhn.jameica.gui.input.DecimalInput getKmTriP() throws RemoteException
	{
	if (kmtrip != null)
	      return kmtrip;
	    
		kmtrip = new DecimalInput(getDatenbankeintragungsinterface().getKmTotal(),de.augustin.jameica.budget.Settings.DECIMALFORMAT);
		kmtrip.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Tageskilometer"));
	    	return kmtrip;
	}
//################################################################################		
	public de.willuhn.jameica.gui.input.DecimalInput getPriceLiteR() throws RemoteException
	{
	if (priceliter != null)
	      return priceliter;
	    
		priceliter = new DecimalInput(getDatenbankeintragungsinterface().getKmTotal(),de.augustin.jameica.budget.Settings.DECIMALFORMAT);
		priceliter.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Preis pro Liter"));
	    	return priceliter;
	}
//################################################################################			
	public de.willuhn.jameica.gui.input.DecimalInput getPriceTotaL() throws RemoteException
	{
	if (pricetotal != null)
	      return pricetotal;
	    
		pricetotal = new DecimalInput(getDatenbankeintragungsinterface().getKmTotal(),de.augustin.jameica.budget.Settings.DECIMALFORMAT);
		pricetotal.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Preis Gesamt"));
	    	return pricetotal;
	}
//################################################################################			
	public de.willuhn.jameica.gui.input.DecimalInput getConsumptioN() throws RemoteException
	{
	if (consumption != null)
	      return consumption;
	    
		consumption = new DecimalInput(getDatenbankeintragungsinterface().getKmTotal(),de.augustin.jameica.budget.Settings.DECIMALFORMAT);
		consumption.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Verbrauch pro 100km"));
	    	return consumption;
	}
//################################################################################				
	public de.willuhn.jameica.gui.input.TextInput getStatioN() throws RemoteException
	{
	if (station != null)
	      return station;
	    
		station = new DecimalInput(getDatenbankeintragungsinterface().getKmTotal(),de.augustin.jameica.budget.Settings.DECIMALFORMAT);
		station.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Tankstelle"));
	    	return station;
	}
//################################################################################		public de.willuhn.jameica.gui.input.Input getDenScheissText() throws RemoteException
	public de.willuhn.jameica.gui.input.TextInput getNoticE() throws RemoteException
	{
	if (notice != null)
	      return notice;
	    
		notice = new DecimalInput(getDatenbankeintragungsinterface().getKmTotal(),de.augustin.jameica.budget.Settings.DECIMALFORMAT);
		notice.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Notizen"));
	    	return notice;
	}
//################################################################################	
	//////////////////Ende Input-Getter
	

	public void handleStore() 
	{
		Application.getMessagingFactory().sendMessage(new StatusBarMessage("handleStore in Datenbankkontrolle, aufgerufen von Ichbindieview, Inhalt aus falschem zugriff" + notice.getValue() ,StatusBarMessage.TYPE_SUCCESS));
		de.augustin.jameica.budget.rmi.DBValueInterface nochnDBVInterface = getDatenbankeintragungsinterface();
		
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
	        Application.getMessagingFactory().sendMessage(new StatusBarMessage("Inhalt aus richtigem Zugriff getValuenachher" + (String) notice.getValue(),StatusBarMessage.TYPE_SUCCESS));
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
		de.willuhn.datasource.rmi.DBIterator Datenbankiterator = Datenbankservice.createList(de.augustin.jameica.budget.rmi.DBValueInterface.class);
		ausgeleseneTabelle = new de.willuhn.jameica.gui.parts.TablePart(Datenbankiterator, new de.willuhn.jameica.gui.Action(){ public void handleAction(Object context){}});
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Datum:-)"),"fueldate");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Datum:-)"),"km_total");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Datum:-)"),"km_trip");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Datum:-)"),"price liter");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Datum:-)"),"price total");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Datum:-)"),"consumption");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Datum:-)"),"station");
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Datum:-)"),"notice");

		
		
			return ausgeleseneTabelle;
	} //getZeigMirDieTabelle()
	
	
	
	
	private de.augustin.jameica.budget.rmi.DBValueInterface Datenbankeintragungsinterface;
	
	private de.augustin.jameica.budget.rmi.DBValueInterface getDatenbankeintragungsinterface()
	  {
	    if ( Datenbankeintragungsinterface != null)
	      return Datenbankeintragungsinterface;
	    Datenbankeintragungsinterface = (de.augustin.jameica.budget.rmi.DBValueInterface) getCurrentObject();
	    return Datenbankeintragungsinterface;
	  }
	
}