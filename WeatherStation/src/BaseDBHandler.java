import java.sql.Timestamp;

public abstract class BaseDBHandler {
	static String system;
	private DBConnection connection;
	DBConnectionProperty props;
	DBSql dbSql;
	
	public BaseDBHandler(String system, String location){
		this.dbSql = new DBSql();
		this.connection = new DBConnection(system, location);
	}
	public DBConnection getDBConnection(){
		return this.connection;
	}
	/**
	 * Base method which will be overwritten by the specific DBHandlers
	 */
	public abstract void makePersistent(double x, double y);
	
	public abstract void makePersistent(String status, Double temperature, Double humidity, Timestamp create_dt, String create_by, Timestamp update_dt, String update_by);
	/**
	 * Close the assigned database connection
	 */
	public void closeDB(){
		this.connection.closeConnection();
	}
}
