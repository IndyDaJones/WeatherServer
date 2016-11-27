import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author jonas
 *
 */
public class RemoteDBHandler extends BaseDBHandler {
	private static final Logger log = Logger.getLogger( WeatherService.class.getName() );
	public RemoteDBHandler(String system) {
		super(system, "rem");
		// TODO Auto-generated constructor stub
	}
	/**
	 * Insert measured temperature and humidity in DB
	 * @param temp
	 * @param humidity
	 */
	@Override
	public void makePersistent(double temp, double humidity){
		try{
			log.log(Level.INFO,"call insertTempHumidity");
			this.dbSql.insertData(super.getDBConnection().getConnection(), temp, humidity);	
		}catch (SQLException e){
			log.log(Level.SEVERE,e.getLocalizedMessage());
		}	
	}
	/**
	 * 
	 * @param status
	 * @param temperature
	 * @param humidity
	 * @param create_dt
	 * @param create_by
	 * @param update_dt
	 * @param update_by
	 */
	@Override
	public void makePersistent(String status, Double temperature, Double humidity, Timestamp create_dt, String create_by, Timestamp update_dt, String update_by){
		try{
			log.log(Level.INFO,"call insertTempHumidity");
			this.dbSql.insertData(super.getDBConnection().getConnection(), status, temperature, humidity, create_dt, create_by, update_dt, update_by);	
		}catch (SQLException e){
			log.log(Level.SEVERE,e.getLocalizedMessage());
		}	
	}
	
}