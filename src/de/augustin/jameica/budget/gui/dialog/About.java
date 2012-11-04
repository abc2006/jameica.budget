package de.augustin.jameica.budget.gui.dialog;

import org.eclipse.swt.widgets.Composite;

import de.augustin.jameica.budget.Plugin;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.input.LabelInput;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.jameica.gui.parts.FormTextPart;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;

public class About extends AbstractDialog
{
	
	public About(int position)
	{
		super(position);
		this.setTitle("About-Fenster");
		
	}
	
	public void test(int value)
	{
		
	}
	
	
	protected void paint(Composite parent) throws Exception
	{

		FormTextPart text = new FormTextPart();
		text.setText("<form>" +
			"<p><b>Jameica example plugin</b></p>" +
			"<br/>Licence: GPL (http://www.gnu.org/copyleft/gpl.html)" +
			"<br/><p>Copyright by Olaf Willuhn [info@jameica.org]</p>" +
			"<p>http://www.jameica.org</p>" +
			"</form>");

		text.paint(parent);

		LabelGroup group = new LabelGroup(parent, " Information ");

		AbstractPlugin p = Application.getPluginLoader().getPlugin(Plugin.class);

		group.addLabelPair("Version",new LabelInput(""+p.getManifest().getVersion()));
		group.addLabelPair("Working directory", new LabelInput(""+p.getResources().getWorkPath()));
		
		ButtonArea buttons = new ButtonArea();
		buttons.addButton("Close",new Action() {
      public void handleAction(Object context) throws ApplicationException
      {
        close();
      }
    },null,true);
		buttons.paint(parent);
		getShell().pack();

  }
	
	protected Object getData() throws Exception
	  {
	    return null;
	  }

	
	
}

