

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;

public class DBConnection {
	private static final Logger log = Logger.getLogger( WeatherService.class.getName() );
	static String system;
	private String dbms;
	private String serverName;
	private String databaseName;
	private String portNumber;
	private Connection con;
	DBConnectionProperty props;
	
	public DBConnection (String system, String location){
		this.system = system+"."+location+".";
		props = new DBConnectionProperty();
	}
	public String getDbms(){
		return this.dbms;
	}
	public String getServerName(){
		return this.serverName;
	}
	public String getDatabaseName(){
		return this.databaseName;
	}
	public void closeConnection(){
		if (this.con != null){
			try {
				this.con.close();
			} catch (SQLException e) {
				log.log(Level.SEVERE,"call closeDB() "+e.getMessage());
			}
		}
	}
	public Connection getConnection() throws SQLException {
		if (this.con != null){
			return this.con;
		}
		else{
			Connection conn = null;
		    dbms = props.getDBProperty(system+"dbms");
		    serverName = props.getDBProperty(system+"Server");
		    databaseName = props.getDBProperty(system+"Database");
		    portNumber = props.getDBProperty(system+"Port");
		    
		    
		    Properties connectionProps = new Properties();
		    connectionProps.put("user", props.getDBProperty(system+"user"));
		    connectionProps.put("password", props.getDBProperty(system+"password"));
		    log.log(Level.INFO,"call getConnection("+connectionProps.toString()+")");
		    if (this.dbms.equals("mysql")) {
		    	try{
			        conn = (Connection)DriverManager.getConnection(
			                   "jdbc:" + dbms + "://" +
			                   serverName +
			                   ":" + portNumber + "/" +
			                   databaseName,
			                   connectionProps);
			        log.log(Level.INFO,"Connected to database");
		    	}catch (SQLException e){
		    		log.log(Level.SEVERE,"DBConnection error " +e);
		    	} 
		    }else {
		    	conn = null;
		    	log.log(Level.SEVERE,"Database unknown");
		    }
		    this.con = conn;
		    return conn;
		}
	}
}
