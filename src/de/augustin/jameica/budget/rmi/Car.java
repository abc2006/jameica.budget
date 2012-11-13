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
	  
	  /**
	   * Sets the name of the project.
	   * @param name name of the project.
	   * @throws RemoteException
	   */
	  public void setFuelDate(Date fueldate) throws RemoteException;
	  
	  /**
	   * Sets the description  of the project.
	   * @param description description  of the project.
	   * @throws RemoteException
	   */
	  public void setKm_Trip(double km_trip) throws RemoteException;
	  
	  /**
	   * Sets the price of the project.
	   * @param price price of the project.
	   * @throws RemoteException
	   */
	  public void setKm_Total(double km_total) throws RemoteException;
	  
	  /**
	   * Sets the start date of the project.
	   * @param startDate start date of the project.
	   * @throws RemoteException
	   */
	  public void setPrice_Liter(double price_liter) throws RemoteException;
	  
	  /**
	   * Sets the end date of the project.
	   * @param endDate end date of the project.
	   * @throws RemoteException
	   */
	  public void setPrice_Total(double price_total) throws RemoteException;

	  
	  /**
	   * Sets the end date of the project.
	   * @param endDate end date of the project.
	   * @throws RemoteException
	   */
	  public void setConsumption(double consumption) throws RemoteException;  
	  
	  
	  /**
	   * Sets the end date of the project.
	   * @param endDate end date of the project.
	   * @throws RemoteException
	   */
	  public void setStation(String station) throws RemoteException;
	  
	  /**
	   * Sets the end date of the project.
	   * @param endDate end date of the project.
	   * @throws RemoteException
	   */
	  public void setComment(String comment) throws RemoteException;
}
