package de.augustin.jameica.budget.rmi;

import java.rmi.RemoteException;
import java.util.Date;

public interface DBValueInterface extends de.willuhn.datasource.rmi.DBObject
{
	public String getBuchstabensalat() throws RemoteException;
	public void setBuchstabensalat(String buchstabensalat) throws RemoteException;
	public Date getFuelDate() throws RemoteException;
	public void setFuelDate(Date datum) throws RemoteException;
}