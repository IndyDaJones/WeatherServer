

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherService {
	private static final Logger log = Logger.getLogger( WeatherService.class.getName() );
	
	static String logFile;
	static String sys;
	static String location;
	static int cycle;
	static BaseDBHandler db;
	static Device dev;
	
	public static void main(String[] args) {
		/**
		 * Load WeatherService properties
		 */
		WeatherServiceProperty props = new WeatherServiceProperty();
		String logFile = props.getServiceProperty("LogFileDest");
		sys = props.getServiceProperty("System");
		location = props.getServiceProperty("Location");
		cycle = Integer.parseInt(props.getServiceProperty("Cycletime"));
				
		initLogging();
		log.log(Level.INFO,"call initDB");
		initDB(sys,location);
		log.log(Level.INFO,"Database initiated");
		log.log(Level.INFO,"call initDevice");
		initDevice(sys);
		log.log(Level.INFO,"Device initiated");
		log.log(Level.INFO,"call startWeather()");
		startWeather();
		log.log(Level.INFO,"Database closed!");
		db.closeDB();
		log.log(Level.INFO,"end main");
	}
/**
 * In this method the logging Handler is initialized
 */
	private static void initLogging(){

		
		try {
			/**
			 * Using the setUseParentHandlers = false flag, the program no more writes in the console
			 */
			log.setUseParentHandlers(true);
			/**Logger globalLogger = Logger.getLogger("global");
			Handler[] handlers = globalLogger.getHandlers();
			for(Handler handler : handlers) {
			    globalLogger.removeHandler(handler);
			}*/
			Handler handler = new FileHandler( logFile +"WeatherService.txt" );
			log.addHandler(handler);
			log.log(Level.INFO,"File location of Service:"+logFile);
		} catch (SecurityException e) {
			log.log(Level.SEVERE,e.getLocalizedMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE,e.getLocalizedMessage());
		}
	}
	/**
	 * This method creates a new database instance
	 */
	private static void initDB(String system,String location){
			if (location.equals("loc")){
				db = new LocalDBHandler(system);
			}else{
				db = new RemoteDBHandler(system);
			}
	}
	/**
	 * This method creates a new database instance
	 */
	private static void initDevice(String system){
			dev = new Device(system);
	}
	/**
	 * This method starts the measuring the weather
	 */
	private static void startWeather(){
		while (true){
			try {
				log.log(Level.INFO,"Call dev.getDatafromdevice() from startWeather()");
				dev.getDatafromdevice();
				log.log(Level.INFO,"Call db.makePersistent(<"+dev.getTemperature()+"<,<"+dev.getHumidity()+">) from startWeather()");
				db.makePersistent(dev.getTemperature(), dev.getHumidity());
				log.log(Level.INFO,"Temperature= "+ dev.getTemperature());
				log.log(Level.INFO,"Humidity= "+ dev.getHumidity());
				log.log(Level.INFO,"Start Thread sleep");
				Thread.sleep(cycle);	
				log.log(Level.INFO,"Wake up Thread");
			}catch(InterruptedException e){
				log.log(Level.SEVERE,"Sleep thread interruption "+e.getMessage());
			}catch(Exception e){
				log.log(Level.SEVERE,"SQL Exception caught "+e.getMessage());
				db.closeDB();
			}
		}
	}
	
}