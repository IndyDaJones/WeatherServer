

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Device {
	private static final Logger log = Logger.getLogger( WeatherService.class.getName() );
	static String sys;
	//private static String source;
	private static String srcCommand;
	private static String CommandScpt;
	private static String Device;
	private static String GPIO;
	static double temperature;
	static double humidity;
	//private String Device_return = "Temp=23.9*  Humidity=37.3%";
	private String device_return = "Temp=3.9*  Humidity=7.3%";
	//private String device_return;
	
	public Device(String sys){
		this.sys = sys;
		setSource();
	}
	private void setSource(){
		DeviceProperty props = new DeviceProperty();
		//source = props.getDeviceProperty(this.sys+"."+"Source");
		srcCommand = props.getDeviceProperty(this.sys+"."+"CommandSrc");
		CommandScpt = props.getDeviceProperty(this.sys+"."+"CommandScpt");
		Device = props.getDeviceProperty(this.sys+"."+"Device");
		GPIO = props.getDeviceProperty(this.sys+"."+"GPIO");
	}
	public void getDatafromdevice(){
		String command = "sudo "+srcCommand+"/"+CommandScpt+" "+Device+" "+GPIO;
		if(executeCommand(command)){
			log.log(Level.INFO,"data successfully read from device!");
		}else{
			log.log(Level.WARNING,"no data read from device!");
		}
	}
	public boolean executeCommand(String command){
		log.log(Level.INFO,"execute "+command);
		String tempDevRet = "";
		Runtime r = Runtime.getRuntime();
		Process p;
		try {
			p = r.exec(command);
			p.waitFor();
			BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((tempDevRet = b.readLine()) != null) {
				log.log(Level.INFO,"result from device <"+tempDevRet+">");
				this.device_return = tempDevRet;
			}
			b.close();
			log.log(Level.INFO,"call parseTemperature() <"+this.device_return+">");
			parseTemperature();
			log.log(Level.INFO,"call parseHumidity() <"+this.device_return+">");
			parseHumidity();
		} catch (IOException e) {
			log.log(Level.SEVERE,e.getLocalizedMessage());
			return false;
		}catch (InterruptedException e) {
			log.log(Level.SEVERE,e.getLocalizedMessage());
			return false;
		}
		return true;
	}
		/**
	 * 
	 */
	private void parseTemperature(){
		int startTemperature = this.device_return.indexOf("T");
		int startHumidity = this.device_return.indexOf("H");
		String Temperature = this.device_return.substring(startTemperature, startHumidity);
		int startResult = Temperature.indexOf("=")+1;
		int endResult = Temperature.indexOf("*");
		log.log(Level.INFO,"parseTemperature "+Temperature.substring(startResult, endResult));
		this.temperature = Double.parseDouble(Temperature.substring(startResult, endResult));
	}
	/**
	 * 
	 */
	private void parseHumidity(){
		int startTemperature = this.device_return.indexOf("T");
		int startHumidity = this.device_return.indexOf("H");
		String Humidity = this.device_return.substring(startHumidity, this.device_return.length());
		int startResult = Humidity.indexOf("=")+1;
		int endResult = Humidity.indexOf("%");
		log.log(Level.INFO,"parseHumidity "+Humidity.substring(startResult, endResult));
		this.humidity = Double.parseDouble(Humidity.substring(startResult, endResult));
	}
	/**
	 * 
	 * @return
	 */
	public double getTemperature(){
		return this.temperature;
	}
	/**
	 * 
	 * @return
	 */
	public double getHumidity(){
		return this.humidity;
	}
}
