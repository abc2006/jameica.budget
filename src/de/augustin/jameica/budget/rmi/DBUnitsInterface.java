package de.augustin.jameica.budget.rmi;

import java.rmi.RemoteException;


public interface DBUnitsInterface extends de.willuhn.datasource.rmi.DBObject
{
	// Hier werden die Schnittstellen definiert, die dann in DBUnitsInterfaceImplementation ausprogrammiert sind 
	//public int getKmTotal() throws RemoteException; // der name KmTotal() ist der gleiche wie in DBVehicleInterfaceImplementation
	//public void setKmTotal(int kmtotaL);

//	public int getId() throws RemoteException;
//	public void setID(Date datum) throws RemoteException;
	
	public String getName() throws RemoteException;
	public void setName(String namE) throws RemoteException;
	public String getNumberPlate() throws RemoteException;
	public void setNumberPlate(String numberplatE) throws RemoteException;
}