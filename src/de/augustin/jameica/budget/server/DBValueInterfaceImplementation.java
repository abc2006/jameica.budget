package de.augustin.jameica.budget.server;

import java.rmi.RemoteException;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.util.DateUtil;
import de.willuhn.util.ApplicationException;


/**
 * This is the implemtor of the project interface.
 * You never need to instanciate this class directly.
 * Instead of this, use the dbService to find the right
 * implementor of your interface.
 * Example:
 * 
 * DBService service = (DBService) Application.getServiceFactory().lookup(ExamplePlugin.class,"exampledatabase");
 * 
 * a) create new project
 * Project project = (Project) service.createObject(Project.class,null);
 * 
 * b) load existing project with id "4".
 * Project project = (Project) service.createObject(Project.class,"4");
 * 
 * c) list existing projects
 * DBIterator projects = service.createList(Project.class);
 */
public class DBValueInterfaceImplementation extends AbstractDBObject implements de.augustin.jameica.budget.rmi.DBValueInterface
{

	/**
   * @throws RemoteException
   */
  public DBValueInterfaceImplementation() throws RemoteException
  {
    super();
  }

  /**
   * We have to return the name of the sql table here.
	 * @see de.willuhn.datasource.db.AbstractDBObject#getTableName()
	 */
  // okay, dann geben wir mal den sql-Tabellennamen hier an .. der da lautet ? test
	protected String getTableName()
	{
		return "test";
	}

  /**
   * Sometimes you can display only one of the projects attributes (in combo boxes).
   * Here you can define the name of this field.
   * Please dont confuse this with the "primary KEY".
   * @see de.willuhn.datasource.GenericObject#getPrimaryAttribute()
	 */
	public String getPrimaryAttribute() throws RemoteException
	{
    // we choose the buchstabensalat as primary field.
		return "buchstabensalat";
	}

	/**
   * This method will be called, before delete() is executed.
   * Here you can make some dependency checks.
   * If you dont want to delete the project (in case of failed dependencies)
   * you have to throw an ApplicationException. The message of this
   * one will be shown in users UI. So please translate the text into
   * the users language.
	 * @see de.willuhn.datasource.db.AbstractDBObject#deleteCheck()
	 */
	protected void deleteCheck() throws ApplicationException
	{
  }

	/**
   * This method is invoked before executing insert().
   * So lets check the entered data.
	 * @see de.willuhn.datasource.db.AbstractDBObject#insertCheck()
	 */
	protected void insertCheck() throws ApplicationException
	{
	}

	/**
   * This method is invoked before every update().
	 * @see de.willuhn.datasource.db.AbstractDBObject#updateCheck()
	 */
	protected void updateCheck() throws ApplicationException
	{
    // we simply call the insertCheck here ;)
    insertCheck();
	}

	/**
	 * @see de.willuhn.datasource.db.AbstractDBObject#getForeignObject(java.lang.String)
	 */
	protected Class getForeignObject(String arg0) throws RemoteException
	{
    // We dont have any foreign keys here. Please check TaskImpl.java
    // if you want to see an example.
		return super.getForeignObject(arg0);
	}
///////////////////////////hier kommen getter und setter, die in der DBValueInterface.java 
	///////////////////////definiert sind und in der Datenbankkontrolle beim HandleStore
	///////////////////////verwendet werden.
	/////////Die funktionsnamen m�ssen mit der DBValueinterface.java �bereinstimmen
	//###########################################################################
		public java.util.Date getFuelDate() throws RemoteException
		{
			return (java.util.Date) getAttribute("fueldate"); // "name" ist the sql field name
		}

		public void setFuelDate(java.util.Date fueldate) throws RemoteException
		{
			setAttribute("fueldate",fueldate);
		}
	//#################################################################################
		public double getKmTotal() throws RemoteException
		{
			return (double) getAttribute("kmtotal"); // "name" ist the sql field name
		}

		public void setKmTotal(double kmtotal) throws RemoteException
		{
			setAttribute("km_total",kmtotal);
		}
	//##################################################################################		
		public double getKmTrip() throws RemoteException
		{
			return (double) getAttribute("kmtrip"); // "name" ist the sql field name
		}

		public void setKmTrip(double kmtrip) throws RemoteException
		{
			setAttribute("km_trip",kmtrip);
		}
	//##################################################################################		
		public double getPriceLiter() throws RemoteException
		{
			return (double) getAttribute("priceliter"); // "name" ist the sql field name
		}

		public void setPriceLiter(double priceliter) throws RemoteException
		{
			setAttribute("price_liter",priceliter);
		}
	//##################################################################################
		public double getPriceTotal() throws RemoteException
		{
			return (double) getAttribute("pricetotal"); // "name" ist the sql field name
		}

		public void setPriceTotal(double pricetotal) throws RemoteException
		{
			setAttribute("price_total",pricetotal);
		}
	//##################################################################################		
		public double getConsumption() throws RemoteException
		{
			return (double) getAttribute("consumption"); // "name" ist the sql field name
		}

		public void setConsumption(double consumption) throws RemoteException
		{
			setAttribute("consumption",consumption);
		}
	//##################################################################################			
		public String getStation() throws RemoteException
		{
			return (String) getAttribute("station"); // "name" ist the sql field name
		}

		public void setStation(String station) throws RemoteException
		{
			setAttribute("station",station);
		}
	//###########################################################################
		public String getNotice() throws RemoteException
		{
		return (String) getAttribute("notice"); // "name" ist the sql field name
		}

		public void setNotice(String notice) throws RemoteException
		{
			setAttribute("notice",notice);
		}
	//###########################################################################

	
}
