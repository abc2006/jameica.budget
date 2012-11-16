package de.augustin.jameica.budget.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.jameica.gui.input.DecimalInput;

public interface DBMetersInterface extends de.willuhn.datasource.rmi.DBObject
{
	// Hier werden die Schnittstellen definiert, die dann in DBMeterInterfaceImplementation ausprogrammiert sind 
	//public int getKmTotal() throws RemoteException; // der name KmTotal() ist der gleiche wie in DBVAlueInterfaceImplementation
	//public void setKmTotal(int kmtotaL);

	public Date getReadingDate() throws RemoteException;
	public void setReadingDate(Date readingdatE) throws RemoteException;
	
	public double getMeterReading() throws RemoteException;
	public void setMeterReading(double meterreadinG)throws RemoteException;
	
	public boolean getNewMeter() throws RemoteException;
	public void setNewMeter(boolean newmeteR) throws RemoteException;
	
	public double getConsumption() throws RemoteException;
	public void setConsumption(double consumptioN) throws RemoteException;
	
	public String getNotice() throws RemoteException;
	public void setNotice(String noticE) throws RemoteException;
}