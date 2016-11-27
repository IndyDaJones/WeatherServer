

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeviceProperty {
	private static final Logger log = Logger.getLogger( WeatherService.class.getName() );
	private Properties properties = new Properties();
	public DeviceProperty(){
		BufferedInputStream stream;
		try {
			//stream = new BufferedInputStream(new FileInputStream("/home/jonas/workspace/WeatherService/src/device.property"));
			stream = new BufferedInputStream(new FileInputStream("device.property"));
			log.log(Level.INFO,"call properties.load(stream)");
			properties.load(stream);
			stream.close();
		} catch (FileNotFoundException e) {
			log.log(Level.SEVERE,e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE,e.getMessage());
		}
	}
	public String getDeviceProperty(String key){
		log.log(Level.INFO,"call properties.getProperty("+key+")");
		return properties.getProperty(key);
	}
}
