package com.rapidus.urlread.lobo;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.lobobrowser.gui.BrowserPanel;
import org.lobobrowser.gui.NavigationAdapter;
import org.lobobrowser.main.PlatformInit;
import org.lobobrowser.ua.NavigationEvent;
import org.lobobrowser.ua.NavigationVetoException;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.html2.HTMLElement;

import com.rapidus.urlread.util.ParseURL;

public class VetoableNavigationDemo extends JFrame {
	private final BrowserPanel browserPanel;
	
	public static void main(String[] args) throws Exception {
		// We'll disable all logging but WARNING.
		Logger.getLogger("org.lobobrowser").setLevel(Level.WARNING);

		// This step is necessary for extensions (including HTML) to work:
		PlatformInit.getInstance().init(false, false);

		// Create window with a specific size.
		JFrame frame = new VetoableNavigationDemo();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}
public void mainFunction(){ 
	Logger.getLogger("org.lobobrowser").setLevel(Level.WARNING);

	// This step is necessary for extensions (including HTML) to work:
	try {
		PlatformInit.getInstance().init(false, false);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	// Create window with a specific size.
	JFrame frame = null;
	try {
		frame = new VetoableNavigationDemo();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setSize(600, 400);
	frame.setVisible(true);
}
	public VetoableNavigationDemo() throws Exception {
		this.setTitle("Lobo Vetoable Navigation Demo");

		// Create a BrowserPanel and set a default home page.
		final BrowserPanel bp = new BrowserPanel();
		this.browserPanel = bp;
		bp.navigate("http://www.google.co.in"); 
		
		// Add a navigation listener.
		bp.addNavigationListener(new LocalNavigationListener());

		// Add top-level components to window.
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.add(bp);

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// This needs to be called in order
				// to inform extensions about the
				// window closing.
				System.out.println("browser closed");
				bp.windowClosing();
			}
		});
	}
	
	private class LocalNavigationListener extends NavigationAdapter {
		@Override
		public void beforeLocalNavigate(NavigationEvent event) throws NavigationVetoException {
			Object linkObject = event.getLinkObject();
			if(linkObject instanceof HTMLElement) {
				HTMLElement linkElement = (HTMLElement) linkObject;
				/*System.out.println("getBaseURI of link clicked: "+linkElement.getBaseURI());
				System.out.println("getNamespaceURI of link clicked: "+linkElement.getNamespaceURI());
				System.out.println("getLocalName of link clicked: "+linkElement.getLocalName());*/
				NamedNodeMap nmap = linkElement.getAttributes();
				ParseURL parseURLObj = new ParseURL();
				URL url = null;
				try {
					url = new URL(nmap.item(3).getNodeValue());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parseURLObj.getParsedURL(url);
				String rel = linkElement.getAttribute("rel");
				if("nofollow".equalsIgnoreCase(rel)) {
					Component dialogParent = event.getOriginatingFrame().getComponent();
					int response = JOptionPane.showConfirmDialog(dialogParent, "This is a rel='nofollow' link. Are you sure you want to continue?", "Please Confirm", JOptionPane.YES_NO_OPTION);
					if(response != JOptionPane.YES_OPTION) {
						throw new NavigationVetoException();
					}
				}
			}
		}		
	}
}
