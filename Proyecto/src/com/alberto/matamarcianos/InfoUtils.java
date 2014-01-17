package com.alberto.matamarcianos;

import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class InfoUtils {
	public final static int RESOLUCIONY = 800;
	public final static int RESOLUCIONX = 480;
	private String version;
	Socket s;
	
	public String version() {
		version = "0.0.3B2";
		/*Calendar calendario = new GregorianCalendar();
		version += calendario.get(Calendar.DATE);
		version += ".";
		version += calendario.get(Calendar.MONTH) + 1;
		version += ".";
		version += calendario.get(Calendar.YEAR);
		version += " ALPHA 0.0.3";*/
		return version;
	}
	
	static public int x() {
		return RESOLUCIONX;
	}
	
	static public int y() {
		return RESOLUCIONY;
	}
	
	public boolean hayConexion() {
		String dirWeb = "www.db4free.net";
		int puerto = 80;
		try{
			s = new Socket(dirWeb, puerto);
			if(s.isConnected()) {
				s.close();
				return true;
			}
				
			else {
				s.close();
				return false;
			}
				
		}
		catch (Exception ex) {
			return false;
		}
	}

}
