/**********************************************************************
 * $Source: /cvsroot/jameica/jameica_exampleplugin/src/de/willuhn/jameica/example/server/ProjectImpl.java,v $
 * $Revision: 1.9 $
 * $Date: 2010-11-09 17:20:16 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/

package de.augustin.jameica.budget.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.augustin.jameica.budget.Settings;
import de.augustin.jameica.budget.rmi.Car;
//import de.willuhn.jameica.example.rmi.Task;
import de.willuhn.logging.Logger;
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
public class CarImpl extends AbstractDBObject implements Car
{

	/**
   * @throws RemoteException
   */
  public CarImpl() throws RemoteException
  {
    super();
  }

  /**
   * We have to return the name of the sql table here.
	 * @see de.willuhn.datasource.db.AbstractDBObject#getTableName()
	 */
	protected String getTableName()
	{
		return "car";
	}

  /**
   * Sometimes you can display only one of the projects attributes (in combo boxes).
   * Here you can define the name of this field.
   * Please don't confuse this with the "primary KEY".
   * @see de.willuhn.datasource.GenericObject#getPrimaryAttribute()
	 */
	public String getPrimaryAttribute() throws RemoteException
	{
    // we choose the total kilometers as primary field.
		return "km_total";
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
    try {
      if (getKm_Total() <= 0 )
        throw new ApplicationException(Settings.i18n().tr("Bitte einen höheren Zählerstand angeben"));

      //if (getStartDate() != null && getEndDate() != null && getStartDate().after(getEndDate()))
      //  throw new ApplicationException(Settings.i18n().tr("start date cannot be after end date"));
      
    }
    catch (RemoteException e)
    {
      Logger.error("insert check of project failed",e);
      throw new ApplicationException(Settings.i18n().tr("unable to store project, please check the system log"));
    }
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
// ######################jetzt kommen die getter, zumindest die, die mir bekannt sind
	/**
	 * @see de.willuhn.jameica.example.rmi.Project#getName()
	 */
	public Date getFuelDate() throws RemoteException
	{
    // When can cast this directly to String, the method getField() knows the
    // meta data of this sql table ;)
		return (Date) getAttribute("fueldate"); // "fueldate" ist the sql field name
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#getDescription()
	 */
	public double getKm_Trip() throws RemoteException
	{
		return (double) getAttribute("km_trip");
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#getEmail()
	 */
	public double getKm_Total() throws RemoteException
	{
    return (double) getAttribute("km_total");
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#getEmail()
	 */
	public double getPrice_Liter() throws RemoteException
	{
    return (double) getAttribute("price_liter");
	}
	
	/**
	 * @see de.willuhn.jameica.example.rmi.Project#getEmail()
	 */
	public double getPrice_Total() throws RemoteException
	{
    return (double) getAttribute("price_total");
	}
	
	/**
	 * @see de.willuhn.jameica.example.rmi.Project#getEmail()
	 */
	public double getConsumption() throws RemoteException
	{
    return (double) getAttribute("consumption");
	}
	
	/**
	 * @see de.willuhn.jameica.example.rmi.Project#getEmail()
	 */
	public String getStation() throws RemoteException
	{
    return (String) getAttribute("station");
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#getEmail()
	 */
	public String getComment() throws RemoteException
	{
    return (String) getAttribute("comment");
	}
	/**
	 * @see de.willuhn.jameica.example.rmi.Project#setName(java.lang.String)
	 */
	public void setFuelDate(Date fueldate) throws RemoteException
	{
    // Please use setField(<fieldname>,<value>) to store the data into the right field.
    setAttribute("fueldate",fueldate);
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#setDescription(java.lang.String)
	 */
	public void setKm_Trip(double km_trip) throws RemoteException
	{
    setAttribute("km_trip",km_trip);
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#setPrice(double)
	 */
	public void setKm_Total(double km_total) throws RemoteException
	{
    // setField() wants to have an object but <prive> is a primitive type.
    // So we have to make a java.lang.Double
    setAttribute("km_total",km_total);
  }

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#setStartDate(java.util.Date)
	 */
	public void setPrice_Liter(double price_liter) throws RemoteException
	{
    setAttribute("price_liter",price_liter);
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#setEndDate(java.util.Date)
	 */
	public void setPrice_Total(double price_total) throws RemoteException
	{
    setAttribute("price_total",price_total);
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#setEndDate(java.util.Date)
	 */
	public void setConsumption(double consumption) throws RemoteException
	{
    setAttribute("consumption",consumption);
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#setEndDate(java.util.Date)
	 */
	public void setStation(String station) throws RemoteException
	{
    setAttribute("station",station);
	}

	/**
	 * @see de.willuhn.jameica.example.rmi.Project#setEndDate(java.util.Date)
	 */
	public void setComment(String comment) throws RemoteException
	{
    setAttribute("comment",comment);
	}
}