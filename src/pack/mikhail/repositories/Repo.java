package pack.mikhail.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import pack.mikhail.entities.Genre;
import pack.mikhail.entities.Klant;
import pack.mikhail.entities.Reservatieregel;
import pack.mikhail.entities.Voorstelling;

public class Repo {

	//server zoekt deze resource naam op in web.xml 
	public final static String JNDI_NAME = "jdbc/cultuurhuis";
	public DataSource dataSource;

	private String SELECT_GENRES = "select * from genres order by naam";
	private String PREPARED_VOORSTELLINGEN = "select * from voorstellingen where genreid=?";
	private String PREPARED_VOORSTELLING = "select * from voorstellingen where id=?";
	private String PREPARED_ZOEK_KLANT_MET_PASSWOORD = "select * from klanten where gebruikersnaam=? and paswoord=?";
	private String PREPARED_ZOEK_KLANT = "select * from klanten where gebruikersnaam=?";
	private String PREPARED_UPDATE_COMMIT_KLANT = "insert into klanten "
			+ "(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord)"
			+"values(?,?,?,?,?,?,?,?)";    
	private String UPDATE_VRIJE_PLAATSEN = "update voorstellingen set vrijeplaatsen=? where id=?";
	private String INSERT_RESERVATIES = "insert into reservaties (klantid,voorstellingsid,plaatsen)"
										+" values(?,?,?)";
	
	
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List getGenres() throws SQLException {

		try (Connection connection = dataSource.getConnection()) {

			List lijst = new ArrayList<Genre>();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_GENRES);

			while (resultSet.next()) {
				lijst.add(new Genre(resultSet.getInt("id"), resultSet.getString("naam")));
			}

			return lijst;

		}
	}

	public List<Voorstelling> getVoorstellingen(int genreId) throws SQLException {

		try (Connection connection = dataSource.getConnection()) {

			List<Voorstelling> lijst = new ArrayList<Voorstelling>();

			PreparedStatement prepStatement = connection.prepareStatement(PREPARED_VOORSTELLINGEN);
			prepStatement.setInt(1, genreId);
			ResultSet resultSet = prepStatement.executeQuery();

			while (resultSet.next()) {

				Voorstelling vrstl = new Voorstelling(resultSet.getInt("id"), resultSet.getString("titel"),
						resultSet.getString("uitvoerders"), new Date(resultSet.getTimestamp("datum").getTime()),
						resultSet.getInt("genreid"), resultSet.getDouble("prijs"), resultSet.getInt("vrijeplaatsen"));
				lijst.add(vrstl);

			}
			return lijst;
		}
	}

	public Voorstelling getVoorstellingById(int id) throws SQLException {

		Voorstelling voorstelling = null;

		try (Connection connection = dataSource.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(PREPARED_VOORSTELLING);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			voorstelling = new Voorstelling(resultSet.getInt("id"), resultSet.getString("titel"),
					resultSet.getString("uitvoerders"), new Date(resultSet.getTimestamp("datum").getTime()),
					resultSet.getInt("genreid"), resultSet.getDouble("prijs"), resultSet.getInt("vrijeplaatsen"));
		}

		return voorstelling;
	}
	
	
	public Optional<Klant> zoekKlant(String login, String passwd) throws SQLException{
		
		try(Connection connection = dataSource.getConnection()){
			
			
			PreparedStatement statement = connection.prepareStatement(PREPARED_ZOEK_KLANT_MET_PASSWOORD);
			statement.setString(1, login);
			statement.setString(2, passwd);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				
				Klant klant = new Klant(resultSet.getInt("id"), resultSet.getString("voornaam"), resultSet.getString("familienaam"), resultSet.getString("Straat"),
										resultSet.getInt("huisnr"), resultSet.getInt("postcode"), resultSet.getString("gemeente"), resultSet.getString("gebruikersnaam"), "hidden");
				
				return Optional.of(klant);

			}
			
			return Optional.empty();
			
		}
	}
		
		public boolean loginBestaatAl(String login) throws SQLException{
			
			try(Connection connection = dataSource.getConnection()){
				
				
				PreparedStatement statement = connection.prepareStatement(PREPARED_ZOEK_KLANT);
				statement.setString(1, login);
				ResultSet resultSet = statement.executeQuery();
				if(resultSet.isBeforeFirst()) {
					
					return true;

				}else {
					
					return false;
				
				}			
			}		
	}
		

		public int commitKlant(Klant klant) throws SQLException {
			
			
			try(Connection connection = dataSource.getConnection()){
				
				
				PreparedStatement statement = connection.prepareStatement(PREPARED_UPDATE_COMMIT_KLANT);
				statement.setString(1, klant.getVoornaam());
				statement.setString(2, klant.getFamilienaam());
				statement.setString(3, klant.getStraat());
				statement.setInt(4, klant.getHuisnr());
				statement.setInt(5, klant.getPostcode());
				statement.setString(6, klant.getGemeente());
				statement.setString(7, klant.getGebruikersnaam());
				statement.setString(8, klant.getPasswoord());
				
				return statement.executeUpdate();				
			
		}
			
		}
			
			public Reservatieregel commitReservering(int idVoorstelling, int zitjes, int klantid) throws SQLException{
				
				
				
				try(Connection connection = dataSource.getConnection()){
					
					Voorstelling voorstelling = getVoorstellingById(idVoorstelling);
					
					//indien plaatsen genoeg, update DB.reservaties en DB.voorstellingen.vrijeplaatsen
					if(voorstelling.getVrijeplaatsen() >= zitjes) {
						
						PreparedStatement statement = connection.prepareStatement(UPDATE_VRIJE_PLAATSEN);
						statement.setInt(1, voorstelling.getVrijeplaatsen() - zitjes);
						statement.setInt(2, idVoorstelling);
						
						int rijenAangepast = statement.executeUpdate();	
						
						PreparedStatement statement2 = connection.prepareStatement(INSERT_RESERVATIES);
						statement2.setInt(1, klantid);
						statement2.setInt(2, idVoorstelling);
						statement2.setInt(3, zitjes);
						int rijenAangepast2 = statement2.executeUpdate();
					
						//return een gelukte Reservatieregel
						
						return new Reservatieregel(voorstelling, zitjes, voorstelling.getVrijeplaatsen() - zitjes, true);
						
					}else{
												
						//return een mislukte Reservatieregel
						return new Reservatieregel(voorstelling, zitjes, voorstelling.getVrijeplaatsen(), false);
						
					}				
			}
			
		}
	/*
	 * public Timestamp checkTimestamp(int genreId) throws SQLException {
	 * 
	 * Timestamp ts; try (Connection connection = dataSource.getConnection()) {
	 * 
	 * List<Voorstelling> lijst = new ArrayList<Voorstelling>();
	 * 
	 * PreparedStatement prepStatement =
	 * connection.prepareStatement(PREPARED_VOORSTELLINGEN); prepStatement.setInt(1,
	 * genreId); ResultSet resultSet = prepStatement.executeQuery();
	 * 
	 * resultSet.next();
	 * 
	 * // new Date(resultSet.getTimestamp("datum").getTime()); ts =
	 * resultSet.getTimestamp("datum"); } return ts;
	 * 
	 * }
	 * 
	 */

}
