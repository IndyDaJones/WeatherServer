import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class replicates the local database to predefined different databases.
 * During development the available databases are the dev.services on the Network Area Storage
 * Diskstation and a Cloud- Database provided by hostpoint
 * @author jonas
 *
 */
public class DBReplication {
	private static final Logger log = Logger.getLogger( WeatherService.class.getName() );
	DBSql dbSql;
	
	public DBReplication (String system){
		this.dbSql = new DBSql();
		
			/**
			 * Get connection to local database
			 */
			LocalDBHandler local = new LocalDBHandler(system);
			RemoteDBHandler remote = new RemoteDBHandler(system);
			log.log(Level.INFO,"call replicate");
			replicate(local, remote);
			
	}
	private void replicate(LocalDBHandler local, RemoteDBHandler remote){
		try {
			ResultSet localRes = this.dbSql.getResultSetFromDb(local.getDBConnection());
			while (localRes.next()){
				remote.makePersistent(localRes.getString("STATUS"), localRes.getDouble("TEMPERATURE"), localRes.getDouble("HUMIDITY"), localRes.getTimestamp("CREATE_DT"), localRes.getString("CREATE_BY"), localRes.getTimestamp("UPDATE_DT"), localRes.getString("UPDATE_BY"));
				log.log(Level.INFO,"Row replicated");
			}
		} catch (Exception e) {
		    log.log(Level.SEVERE,"call getConnection() "+e.getMessage());
		}
	}
	
}
