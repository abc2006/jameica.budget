package de.augustin.jameica.budget.gui.control;

import java.rmi.RemoteException;

import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class Datenbankkontrolle extends de.willuhn.jameica.gui.AbstractControl

{
	private de.willuhn.jameica.gui.parts.TablePart ausgeleseneTabelle;
	private de.willuhn.jameica.gui.input.TextInput ScheissText; 
	private de.willuhn.jameica.gui.input.DateInput datum;
	
	public Datenbankkontrolle(de.willuhn.jameica.gui.AbstractView malwasanderesalsview)
	{
		// wovon ich aber immer noch nicht weis, was das hier jetzt genau macht ... 
		super(malwasanderesalsview);
	}
	
	 /////////////////////Input-Getter
	public de.willuhn.jameica.gui.input.Input getDenScheissText() throws RemoteException
	{
		ScheissText = new de.willuhn.jameica.gui.input.TextInput(getDatenbankeintragungsinterface().getBuchstabensalat(),255);;
		ScheissText.setHint("ScheissText-Feld Datenbankkontrolle.java");
		return ScheissText;
	}
	
	
	public de.willuhn.jameica.gui.input.DateInput getFuelDate() throws RemoteException
	{
	if (datum != null)
	      return datum;
	    
	    datum = new DateInput(getDatenbankeintragungsinterface().getFuelDate(),de.augustin.jameica.budget.Settings.DATEFORMAT);
	    datum.setName(de.augustin.jameica.budget.Settings.i18n().tr("End date"));
	    return datum;
	}
	
	//////////////////Ende Input-Getter
	

	public void handleStore() 
	{
		Application.getMessagingFactory().sendMessage(new StatusBarMessage("handleStore in Datenbankkontrolle, aufgerufen von Ichbindieview, Inhalt aus falschem zugriff" + ScheissText.getValue() ,StatusBarMessage.TYPE_SUCCESS));
		de.augustin.jameica.neu.rmi.DBValueInterface nochnDBVInterface = getDatenbankeintragungsinterface();
		
		try 
		{ // also ab hier klappt das eintragen. Jetzt wollen wir mal schauen, wies denn weiter aussieht ... 
		//nochnDBVInterface.setBuchstabensalat((String) getDenScheissText().getValue());
	        Application.getMessagingFactory().sendMessage(new StatusBarMessage("Inhalt aus richtigem Zugriff getValuevorher" + (String) ScheissText.getValue(),StatusBarMessage.TYPE_SUCCESS));
			nochnDBVInterface.setBuchstabensalat("Irgendeininhalt");
	        Application.getMessagingFactory().sendMessage(new StatusBarMessage("Inhalt aus richtigem Zugriff getValuenachher" + (String) ScheissText.getValue(),StatusBarMessage.TYPE_SUCCESS));

			try 
			{
			nochnDBVInterface.store();
	        Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr("Eintrag erfolgreich gespeichert"),StatusBarMessage.TYPE_SUCCESS));
			
			}
			catch (ApplicationException e)
		    {
		    Application.getMessagingFactory().sendMessage(new StatusBarMessage(e.getMessage(),StatusBarMessage.TYPE_ERROR));
		    }
		}
		catch (RemoteException e)
		{
		Logger.error("error while storing project",e);
		Application.getMessagingFactory().sendMessage(new StatusBarMessage(de.augustin.jameica.budget.Settings.i18n().tr("Error while storing: {0}",e.getMessage()),StatusBarMessage.TYPE_ERROR));
		}
	}// handleStore()
	
	
	
	
	public de.willuhn.jameica.gui.Part getZeigMirDieTabelle() throws Exception
	{
		//Auf mich macht die ganze sache folgenden eindruck:
		//Mit dem Datenbankservice können wir einen Datenbankinterator erstellen.
		//Mit dem Iterator können wir Daten aus der Datenbank holen.
		//Allerdings beinhaltet dieses Viech keine funktion zum Einfügen.
		//Warum das Einfügen jetzt so verdammt schwer ist... ich weiss es nicht... 
		de.willuhn.datasource.rmi.DBService Datenbankservice = de.augustin.jameica.budget.Settings.getDBService();
		de.willuhn.datasource.rmi.DBIterator Datenbankiterator = Datenbankservice.createList(de.augustin.jameica.neu.rmi.DBValueInterface.class);
		ausgeleseneTabelle = new de.willuhn.jameica.gui.parts.TablePart(Datenbankiterator, new de.willuhn.jameica.gui.Action(){ public void handleAction(Object context){}});
		ausgeleseneTabelle.addColumn(de.augustin.jameica.budget.Settings.i18n().tr("Buchstabensalat ;-)"),"buchstabensalat");
		
		return ausgeleseneTabelle;
	} //getZeigMirDieTabelle()
	
	
	
	
	private de.augustin.jameica.neu.rmi.DBValueInterface Datenbankeintragungsinterface;
	
	private de.augustin.jameica.neu.rmi.DBValueInterface getDatenbankeintragungsinterface()
	  {
	    if ( Datenbankeintragungsinterface != null)
	      return Datenbankeintragungsinterface;
	    Datenbankeintragungsinterface = (de.augustin.jameica.neu.rmi.DBValueInterface) getCurrentObject();
	    return Datenbankeintragungsinterface;
	  }
	
}