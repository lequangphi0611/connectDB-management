import com.db.DatabaseConnector;
import com.db.impl.DatabaseConnectorImpl;
import com.db.impl.DatabasePropertiesImpl;

public class test {

	
	public static void main(String[] args) {
		DatabaseConnector connector = new DatabaseConnectorImpl(null);
		System.out.println(connector
			.createQueryBuilder("SELECT")
			.executeQuery(rs -> rs.getString(1))
			.getSingleResult());
		
	}
}
