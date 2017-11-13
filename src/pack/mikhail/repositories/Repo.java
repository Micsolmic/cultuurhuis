package pack.mikhail.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;



import pack.mikhail.entities.Genre;


public class Repo {

	public final static String JNDI_NAME = "jdbc/cultuurhuis";
	public DataSource dataSource;
	
	private String SELECT_GENRES = "select * from genres order by naam";
  
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List getGenres() throws SQLException{
		
		try(Connection connection = dataSource.getConnection()){
			
			List lijst = new ArrayList<Genre>();
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_GENRES);
			
			if(resultSet.next()) {				
				lijst.add(new Genre(resultSet.getInt("id"), resultSet.getString("naam")));					
			}
			
			return lijst;
			
			
			
		}
		
		
			
		
	}

	
	

}
