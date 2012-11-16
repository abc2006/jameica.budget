package de.augustin.jameica.budget.server;

import java.rmi.RemoteException;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.util.DateUtil;
import de.willuhn.util.ApplicationException;


/**
 * This is the implementor of the DBMeters interface.
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
public class DBMetersInterfaceImplementation extends AbstractDBObject implements de.augustin.jameica.budget.rmi.DBMetersInterface
{

	/**
   * @throws RemoteException
   */
  public DBMetersInterfaceImplementation() throws RemoteException
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
		return "meters";
	}

  /**
   * Sometimes you can display only one of the projects attributes (in combo boxes).
   * Here you can define the name of this field.
   * Please dont confuse this with the "primary KEY".
   * @see de.willuhn.datasource.GenericObject#getPrimaryAttribute()
	 *///Ich glaube, das hier brauchen wir gar nicht
	public String getPrimaryAttribute() throws RemoteException
	{
    // we choose the buchstabensalat as primary field.
		return "Not Used!";
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
///////////////////////////hier kommen getter und setter, die in der DBMetersInterface.java 
	///////////////////////definiert sind und in der Datenbankkontrolle beim HandleStore
	///////////////////////verwendet werden.
	/////////Die funktionsnamen müssen mit der DBMetersInterface.java übereinstimmen
	//###########################################################################
		public java.util.Date getReadingDate() throws RemoteException
		{
			return (java.util.Date) getAttribute("readingdate"); // "name" ist the sql field name
		}

		public void setReadingDate(java.util.Date readingdate) throws RemoteException
		{
			setAttribute("readingdate",readingdate);
		}
	//#################################################################################
		public double getMeterReading() throws RemoteException
		{
			return (double) getAttribute("meterreading"); // "name" ist the sql field name
		}

		public void setMeterReading(double meterreading) throws RemoteException
		{
			setAttribute("meterreading",meterreading);
		}
	//##################################################################################		
		public boolean getNewMeter() throws RemoteException
		{
			return (boolean) getAttribute("newmeter"); // "name" ist the sql field name
		}

		public void setNewMeter(boolean newmeter) throws RemoteException
		{
			setAttribute("newmeter",newmeter);
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
