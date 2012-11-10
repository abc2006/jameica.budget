package de.augustin.jameica.budget.gui.control;

import java.rmi.RemoteException;


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
import de.willuhn.jameica.gui.parts.TablePart;









public class SummaryControl extends AbstractControl
{
	public SummaryControl(AbstractView view) {
		super(view);
	}

	private TablePart projectList;
	
	public Part getProjectsTable() throws RemoteException
	  {
		
		// 1) get the dataservice
	    DBService service = Settings.getDBService();
	    
	    // 2) now we can create the project list.
	    //    We do not need to specify the implementing class for
	    //    the interface "Project". Jameicas Classloader knows
	    //    all classes an finds the right implementation automatically. ;)
	    DBIterator cars = service.createList(Car.class);
	    
	    // 4) create the table
		projectList = new TablePart(cars, new de.augustin.jameica.budget.gui.action.EnterData());
		
		
		// 5) the following fields are a date fields. So we add a date formatter. 
	    projectList.addColumn(Settings.i18n().tr("Tankdatum"),"fueldate",new DateFormatter(Settings.DATEFORMAT));

	    // 6) now we have to add some columns.
	    //Frage: wie kann ich eine Spalte auf changeable /sortierung/align) setzen, wenn man keinen formatter angeben will? 
		projectList.addColumn(Settings.i18n().tr("Trip [km]"),"km_trip"); // "km_trip" is the field name from the sql table.
	    projectList.addColumn(Settings.i18n().tr("Gesamt [km]"),"km_total"); // "km_total" is the field name from the sql table.
	    projectList.addColumn(Settings.i18n().tr("Preis/l[Euro]"),"price_liter", new CurrencyFormatter(Settings.CURRENCY,Settings.DECIMALFORMAT)); // "name" is the field name from the sql table.
	    projectList.addColumn(Settings.i18n().tr("Gesamt [Euro]"),"price_total", new CurrencyFormatter(Settings.CURRENCY, Settings.DECIMALFORMAT)); // "name" is the field name from the sql table.
	    projectList.addColumn(Settings.i18n().tr("l/100km[l]"),"consumption"); // "name" is the field name from the sql table.
	    projectList.addColumn(Settings.i18n().tr("Tankstelle"),"station"); // "name" is the field name from the sql table.
	    projectList.addColumn(Settings.i18n().tr("Notizen"),"comments"); // "name" is the field name from the sql table.
	    


	    // 7) calculated project price (price per hour * hours)
	    //projectList.addColumn(Settings.i18n().tr("Efforts"),"summary", new CurrencyFormatter(Settings.CURRENCY,Settings.DECIMALFORMAT));

		// 8) we are adding a context menu ## später irgendwann .. 
		//projectList.setContextMenu(new ProjectListMenu());
	    return projectList;
		
		
		
		
		
	  }
	
	
	
	
	
} // public class .. 