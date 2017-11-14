package pack.mikhail.repositories;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import pack.mikhail.entities.Genre;
import pack.mikhail.entities.Voorstelling;

public class Repo {

	public final static String JNDI_NAME = "jdbc/cultuurhuis";
	public DataSource dataSource;

	private String SELECT_GENRES = "select * from genres order by naam";
	private String PREPARED_VOORSTELLINGEN = "select * from voorstellingen where genreid=?";

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
									 resultSet.getString("uitvoerders"), new Date(resultSet.getTimestamp("datum").getTime()), resultSet.getInt("genreid"),
									 resultSet.getDouble("prijs"), resultSet.getInt("vrijeplaatsen"));
				lijst.add(vrstl);

			}
			return lijst;
		}

	}

}
