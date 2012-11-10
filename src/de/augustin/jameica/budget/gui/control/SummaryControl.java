package de.augustin.jameica.budget.gui.control;

import java.rmi.RemoteException;


import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.augustin.jameica.budget.Settings;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.CurrencyFormatter;
import de.willuhn.jameica.gui.formatter.DateFormatter;
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
	    //DBService service = Settings.getDBService();
	    
	    // 2) now we can create the project list.
	    //    We do not need to specify the implementing class for
	    //    the interface "Project". Jameicas Classloader knows
	    //    all classes an finds the right implementation automatically. ;)
	    //DBIterator projects = service.createList(Project.class);
	    
	    // 4) create the table
		projectList = new TablePart(new de.augustin.jameica.budget.gui.action.EnterData());
		
		// 5) now we have to add some columns.
	    projectList.addColumn(Settings.i18n().tr("Name of project"),"name"); // "name" is the field name from the sql table.

	    // 6) the following fields are a date fields. So we add a date formatter. 
	    projectList.addColumn(Settings.i18n().tr("Start date"),"startdate",new DateFormatter(Settings.DATEFORMAT));


	    // 7) calculated project price (price per hour * hours)
	    //projectList.addColumn(Settings.i18n().tr("Efforts"),"summary", new CurrencyFormatter(Settings.CURRENCY,Settings.DECIMALFORMAT));

		// 8) we are adding a context menu ## später irgendwann .. 
		//projectList.setContextMenu(new ProjectListMenu());
	    return projectList;
		
		
		
		
		
	  }
	
	
	
	
	
} // public class .. 