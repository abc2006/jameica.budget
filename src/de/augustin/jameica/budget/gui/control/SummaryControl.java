package de.augustin.jameica.budget.gui.control;

import java.rmi.RemoteException;
import java.util.Date;


import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.augustin.jameica.budget.Settings;
import de.augustin.jameica.budget.rmi.Car;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.CurrencyFormatter;
import de.willuhn.jameica.gui.formatter.DateFormatter;
import de.willuhn.jameica.gui.formatter.Formatter;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.TextAreaInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;


public class SummaryControl extends AbstractControl
{
	
	private TablePart vehicleList;
	
	// input fields for the vehicle Attributes
	  // Input fields for the project attributes,
	  private DateInput fueldate;
	  private DecimalInput km_trip;
	  private DecimalInput km_total;
	  private DecimalInput price_trip;
	  private DecimalInput price_total;
	  private DecimalInput consumption;
	  private TextInput station;
	  private TextInput comments;
	// this is the currently opened car
	private Car car;

	  
	public SummaryControl(AbstractView view) 
	{
		super(view);
	}

	  /**
	   * Small helper method to get the current car.
	   * @return
	   */
	  private Car getCar()
	  {
	    if (car != null)
	      return car;
	    car = (Car) getCurrentObject();
	    return car;
	  }
	
	  public Input getFuelDate() throws RemoteException
	  {
	    // if (fueldate != null)
	    //  return fueldate;
	    // "255" is the maximum length for this input field.
	    //name = new TextInput(getProject().getName(),255);
	    //name.setMandatory(true);
	    //name.setName(Settings.i18n().tr("Name"));
	    
	    return fueldate;
	  }
	
	  public Input getKm_Trip() throws RemoteException
	  {
	    //if (km_trip != null)
	      return km_trip;
	  }
	
	  public Input getKm_Total() throws RemoteException
	  {
	    //if (km_trip != null)
	      return km_total;
	  }
	  public Input getPrice_Trip() throws RemoteException
	  {
	    //if (km_trip != null)
	      return price_trip;
	  }
	  public Input getPrice_Total() throws RemoteException
	  {
	    //if (km_trip != null)
	      return price_total;
	  }
	  public Input getConsumption() throws RemoteException
	  {
	    //if (km_trip != null)
	      return consumption;
	  }
	  
	  public Input getStation() throws RemoteException
	  {
	    //if (km_trip != null)
	      return station;
	  }
	  
	  public Input getComments() throws RemoteException
	  {
	    //if (km_trip != null)
	      return comments;
	  }
	public Part getCarTable() throws RemoteException
	  {
		
		// 1) get the dataservice
	    DBService service = Settings.getDBService();
	    
	    // 2) now we can create the project list.
	    //    We do not need to specify the implementing class for
	    //    the interface "Project". Jameicas Classloader knows
	    //    all classes an finds the right implementation automatically. ;)
	    DBIterator cars = service.createList(Car.class);
	    
	    // 4) create the table
		vehicleList = new TablePart(cars, new de.augustin.jameica.budget.gui.action.EnterData());
		
		
		// 5) the following fields are a date fields. So we add a date formatter. 
	    vehicleList.addColumn(Settings.i18n().tr("Tankdatum"),"fueldate",new DateFormatter(Settings.DATEFORMAT));

	    // 6) now we have to add some columns.
	    //Frage: wie kann ich eine Spalte auf changeable /sortierung/align) setzen, wenn man keinen formatter angeben will? 
	    vehicleList.addColumn(Settings.i18n().tr("Trip [km]"),"km_trip"); // "km_trip" is the field name from the sql table.
	    vehicleList.addColumn(Settings.i18n().tr("Gesamt [km]"),"km_total"); // "km_total" is the field name from the sql table.
	    vehicleList.addColumn(Settings.i18n().tr("Preis/l[Euro]"),"price_liter", new CurrencyFormatter(Settings.CURRENCY,Settings.DECIMALFORMAT)); // "name" is the field name from the sql table.
	    vehicleList.addColumn(Settings.i18n().tr("Gesamt [Euro]"),"price_total", new CurrencyFormatter(Settings.CURRENCY, Settings.DECIMALFORMAT)); // "name" is the field name from the sql table.
	    vehicleList.addColumn(Settings.i18n().tr("l/100km[l]"),"consumption"); // "name" is the field name from the sql table.
	    vehicleList.addColumn(Settings.i18n().tr("Tankstelle"),"station"); // "name" is the field name from the sql table.
	    vehicleList.addColumn(Settings.i18n().tr("Notizen"),"comments"); // "name" is the field name from the sql table.
	    


	    // 7) calculated project price (price per hour * hours)
	    //projectList.addColumn(Settings.i18n().tr("Efforts"),"summary", new CurrencyFormatter(Settings.CURRENCY,Settings.DECIMALFORMAT));

		// 8) we are adding a context menu ## später irgendwann .. 
		//projectList.setContextMenu(new ProjectListMenu());
	    return vehicleList;
		
		
		
		
		
	  }
	
	 public void handleStore()
	  {
	    try
	    {

	      // get the current project.
	      Car p = getCar();
	      
	      // invoke all Setters of this project and assign the current values
	      //p.setName((String) getName().getValue());
	      //p.setDescription((String) getDescription().getValue());
	      //p.setFuelDate(to"1.1.2012");
	      p.setKm_Total(1000);
	      p.setKm_Trip(200);
	      p.setPrice_Liter(1.99);
	      p.setPrice_Total(24.25);
	      p.setComment("Kommentar");
	      p.setStation("Total AB");
	      p.setConsumption(6.33);
				// we can cast the return value of date input directly to "java.util.Date".
	//      p.setEndDate((Date) getEndDate().getValue());
	//      p.setStartDate((Date) getStartDate().getValue());

				// the DecimalInput fields returns a Double object
	//      Double d = (Double) getPrice().getValue();
	//      p.setPrice(d == null ? 0.0 : d.doubleValue());

	      // Now, let's store the project
	      // The store() method throws ApplicationExceptions if
	      // insertCheck() or updateCheck() failed.
	      try
	      {
	        p.store();
	        Application.getMessagingFactory().sendMessage(new StatusBarMessage(Settings.i18n().tr("Project stored successfully"),StatusBarMessage.TYPE_SUCCESS));
	      }
	      catch (ApplicationException e)
	      {
	        Application.getMessagingFactory().sendMessage(new StatusBarMessage(e.getMessage(),StatusBarMessage.TYPE_ERROR));
	      }
	    }
	    catch (RemoteException e)
	    {
	      Logger.error("error while storing project",e);
	      Application.getMessagingFactory().sendMessage(new StatusBarMessage(Settings.i18n().tr("Error while storing Project: {0}",e.getMessage()),StatusBarMessage.TYPE_ERROR));
	    }
	  }
	
	
	
} // public class .. 