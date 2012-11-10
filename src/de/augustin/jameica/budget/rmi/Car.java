package de.augustin.jameica.budget.rmi;


import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBObject;


/**
 * Interface of the business object for projects.
 * According to the SQL table, we define some getter an setter here.
 * <pre>
 CREATE TABLE car (
  id NUMERIC default UNIQUEKEY('car'),
  fueldate date NOT NULL,
  km_trip double,
  km_total double NOT NULL,
  price_liter double,
  price_total double,
  consumption double,
  station text,
  comments text
);
 * </pre>
 * <br>Getters and setters for the primary key (id) are not needed.
 * Every one of the following methods has to throw a RemoteException.
 * <br>
 */




public interface Car extends DBObject
{
	
	  /**
	   * Returns the start date of the project.
	   * @return start date.
	   * @throws RemoteException
	   */
	  public Date getFuelDate() throws RemoteException;
	  
	  
	  /**
	   * Returns the name of the project.
	   * @return name of the project.
	   * @throws RemoteException
	   */
	  public double getKm_Trip() throws RemoteException;
	  
	  /**
	   * Returns the description text of the project.
	   * @return description of the project.
	   * @throws RemoteException
	   */
	  public double getKm_Total() throws RemoteException;
	  
	  /**
	   * Returns the email of the project contact.
	   * @return email of project contact.
	   * @throws RemoteException
	   */
	  public double getPrice_Liter() throws RemoteException;
	  
	  /**
	   * Returns the price per hour for the project.
	   * @return price.
	   * @throws RemoteException
	   */
	  public double getPrice_Total() throws RemoteException;
	  
	  /**
	   * Returns the end date of the project.
	   * @return end date.
	   * @throws RemoteException
	   */
	  public double getConsumption() throws RemoteException;

	  /**
	   * Returns the end date of the project.
	   * @return end date.
	   * @throws RemoteException
	   */
	  public String getStation() throws RemoteException;
	  
	  /**
	   * Returns the end date of the project.
	   * @return end date.
	   * @throws RemoteException
	   */
	  public String getComment() throws RemoteException;
	  
	  /// erstmal die getter angepasst
	  
	  
	  
	  
	  /**
	   * Sets the name of the project.
	   * @param name name of the project.
	   * @throws RemoteException
	   */
//	  public void setName(String name) throws RemoteException;
	  
	  /**
	   * Sets the description  of the project.
	   * @param description description  of the project.
	   * @throws RemoteException
	   */
//	  public void setDescription(String description) throws RemoteException;
	  
	  /**
	   * Sets the price of the project.
	   * @param price price of the project.
	   * @throws RemoteException
	   */
//	  public void setPrice(double price) throws RemoteException;
	  
	  /**
	   * Sets the start date of the project.
	   * @param startDate start date of the project.
	   * @throws RemoteException
	   */
//	  public void setStartDate(Date startDate) throws RemoteException;
	  
	  /**
	   * Sets the end date of the project.
	   * @param endDate end date of the project.
	   * @throws RemoteException
	   */
//	  public void setEndDate(Date endDate) throws RemoteException;

	  /**
	   * Additionally we want to have a method that fetches all tasks from this project.
	   * @return list of all tasks within this project.
	   * @throws RemoteException
	   */
//	  public DBIterator getTasks() throws RemoteException;
	  
	  /**
	   * Returns the effort summary from all tasks of this project. 
	   * @return effort summary from all tasks of this project.
	   * @throws RemoteException
	   */
//	  public double getEfforts() throws RemoteException;
	
}