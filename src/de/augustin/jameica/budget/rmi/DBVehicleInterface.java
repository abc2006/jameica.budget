package de.augustin.jameica.budget.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.jameica.gui.input.DecimalInput;

public interface DBVehicleInterface extends de.willuhn.datasource.rmi.DBObject
{
	// Hier werden die Schnittstellen definiert, die dann in DBVehicleInterfaceImplementation ausprogrammiert sind 
	//public int getKmTotal() throws RemoteException; // der name KmTotal() ist der gleiche wie in DBVehicleInterfaceImplementation
	//public void setKmTotal(int kmtotaL);
	
	public int getVehicleId() throws RemoteException;
	public void setVehicleId(int id) throws RemoteException;

	public Date getFuelDate() throws RemoteException;
	public void setFuelDate(Date datum) throws RemoteException;
	
	public double getKmTotal() throws RemoteException;
	public void setKmTotal(double kmtotaL)throws RemoteException;
	
	public double getKmTrip() throws RemoteException;
	public void setKmTrip(double kmtriP) throws RemoteException;
	
	public double getPriceLiter() throws RemoteException;
	public void setPriceLiter(double priceliteR) throws RemoteException;
	
	public double getPriceTotal() throws RemoteException;
	public void setPriceTotal(double pricetotaL) throws RemoteException;
	
	public double getConsumption() throws RemoteException;
	public void setConsumption(double consumptioN) throws RemoteException;
	
	public String getStation() throws RemoteException;
	public void setStation(String statioN) throws RemoteException;
	public String getNotice() throws RemoteException;
	public void setNotice(String noticE) throws RemoteException;
}