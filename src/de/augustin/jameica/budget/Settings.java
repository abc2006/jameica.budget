package de.augustin.jameica.budget;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;

import de.willuhn.datasource.rmi.DBService;
import de.augustin.jameica.budget.Plugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.I18N;


public class Settings
{
	
	private static I18N i18n;
	
	private final static de.willuhn.jameica.system.Settings settings = Application.getPluginLoader().getPlugin(Plugin.class).getResources().getSettings();
	
	/**
	   * Our DateFormatter.
	   */
	  public final static DateFormat DATEFORMAT = DateFormat.getDateInstance(DateFormat.DEFAULT, Application.getConfig().getLocale());
	  
	  /**
	   * Our decimal formatter.
	   */
	  public final static DecimalFormat DECIMALFORMAT = (DecimalFormat) DecimalFormat.getInstance(Application.getConfig().getLocale());

	  /**
	   * Our currency name.
	   */
	  public final static String CURRENCY = "EUR";

		static
		{
			DECIMALFORMAT.setMinimumFractionDigits(2);
			DECIMALFORMAT.setMaximumFractionDigits(2);
		}
	
		public static I18N i18n()
		{
			if (i18n != null)
				return i18n;
			i18n = Application.getPluginLoader().getPlugin(Plugin.class).getResources().getI18N();
			return i18n; 
		}
}