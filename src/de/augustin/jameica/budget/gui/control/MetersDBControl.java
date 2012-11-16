package de.augustin.jameica.budget.gui.control;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.willuhn.jameica.gui.input.CheckboxInput;
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

public class MetersDBControl extends de.willuhn.jameica.gui.AbstractControl

{
	private de.willuhn.jameica.gui.parts.TablePart dbtable;
	private de.willuhn.jameica.gui.input.SelectInput meter;
	private de.willuhn.jameica.gui.input.TextInput notice; 
	private de.willuhn.jameica.gui.input.DateInput readingdate;
	private de.willuhn.jameica.gui.input.DecimalInput meterreading;
	private de.willuhn.jameica.gui.input.CheckboxInput newmeter;
	private de.willuhn.jameica.gui.input.DecimalInput consumption;

	
	public MetersDBControl(de.willuhn.jameica.gui.AbstractView malwasanderesalsview)
	{
		// wovon ich aber immer noch nicht weiﬂ, was das hier jetzt genau macht ... 
		super(malwasanderesalsview);
	}
	
	 /////////////////////Input-Getter

		public de.willuhn.jameica.gui.input.Input getMeteR() throws RemoteException
		{
		Object[] mr = new Object[ 3 ]; 
		mr[0] = "Gas"; 
		mr[1] = "Strom"; 
		mr[2] = "Wasser";
		
		
			meter = new de.willuhn.jameica.gui.input.SelectInput(mr,null);
			meter.setName("Counter");
		    	return meter;	
		}
	//################################################################################			
	public de.willuhn.jameica.gui.input.Input getReadingDatE() throws RemoteException
	{
	if (readingdate != null)
	      return readingdate;
	    
		readingdate = new DateInput(new Date(),de.augustin.jameica.budget.Settings.DATEFORMAT);
		readingdate.setName(de.augustin.jameica.budget.Settings.i18n().tr("Reading Date"));
		readingdate.focus();
	    // leicht kontraproduktiv ... 
	    //datum.addListener(new Listener(){public void handleEvent(Event event){datum.setValue(null);}});
	    
	    	return readingdate;
	}
//################################################################################
	public de.willuhn.jameica.gui.input.Input getMeterReadinG() throws RemoteException
	{
	if (meterreading != null)
	      return meterreading;
	    
		meterreading = new DecimalInput(de.augustin.jameica.budget.Settings.DECIMALFORMAT_0NK);
		meterreading.setHint(de.augustin.jameica.budget.Settings.i18n().tr("Meter Reading"));
		meterreading.setName("Meter Reading");
	/*	meterreading.addListener(new Listener() { // dieser Listener berechnet die Aufw‰nde im Projekt-Details-Fenster
	        public void handleEvent(Event event)
	        {
	          try
	          {
	              Double d = (Double) getMeterReadinG().getValue();
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
	      */
	    	return meterreading;
	}
//################################################################################	
	public de.willuhn.jameica.gui.input.Input getNewMeteR() throws RemoteException
	{
	if (newmeter != null)
	      return newmeter;
	    
		newmeter = new CheckboxInput(false);
		newmeter.setName("New Counter");
	    	return newmeter;
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
		de.augustin.jameica.budget.rmi.DBMetersInterface DBMInterface = getDBInsertInterface();
		
		try 
		{ 
			
			DBMInterface.setReadingDate((Date) readingdate.getValue());
			DBMInterface.setMeterReading((double) meterreading.getValue());
			DBMInterface.setNewMeter((boolean) newmeter.getValue());
			DBMInterface.setConsumption((double) consumption.getValue());
			DBMInterface.setNotice((String) notice.getValue());
			try 
			{
				DBMInterface.store();
				Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr("reading successfully stored"),StatusBarMessage.TYPE_SUCCESS));
			}
			catch (ApplicationException e)
		    {
				Application.getMessagingFactory().sendMessage(new StatusBarMessage(e.getMessage(),StatusBarMessage.TYPE_ERROR));
		    }
		}
		catch (RemoteException e)
		{
			Logger.error("error while storing reading",e);
			Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr("Error while storing reading: {0}",e.getMessage()),StatusBarMessage.TYPE_ERROR));
		}
	}// handleStore()
	
	
	
	
	public de.willuhn.jameica.gui.Part getZeigMirDieTabelle() throws Exception
	{
		de.willuhn.datasource.rmi.DBService Datenbankservice = de.augustin.jameica.budget.Settings.getDBService();
		de.willuhn.datasource.rmi.DBIterator Datenbankiterator = Datenbankservice.createList(de.augustin.jameica.budget.rmi.DBVehicleInterface.class);
		dbtable = new de.willuhn.jameica.gui.parts.TablePart(Datenbankiterator, new de.willuhn.jameica.gui.Action(){ public void handleAction(Object context){}});
		dbtable.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Reading Date"),"readingdate");
		dbtable.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Meter Reading"),"meterreading");
		dbtable.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("New Meter"),"newmeter");
		dbtable.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Consumption"),"consumption");
		dbtable.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Notice"),"notice");

		
		
			return dbtable;
	} //getZeigMirDieTabelle()
	
	
	
	
	private de.augustin.jameica.budget.rmi.DBMetersInterface DBInsertInterface;
	
	private de.augustin.jameica.budget.rmi.DBMetersInterface getDBInsertInterface()
	  {
	    if ( DBInsertInterface != null)
	      return DBInsertInterface;
	    DBInsertInterface = (de.augustin.jameica.budget.rmi.DBMetersInterface) getCurrentObject();
	    return DBInsertInterface;
	  }

}