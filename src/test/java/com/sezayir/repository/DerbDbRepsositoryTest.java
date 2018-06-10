package com.sezayir.repository;

import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DerbDbRepsository.class })
@PowerMockIgnore("javax.management.*")
public class DerbDbRepsositoryTest {

	@Mock
	Connection connection;

	private static final String URL = "jdbc:derby:memory:TestingDB;create=true";
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

	@Before
	public void setUp() throws Exception {

		PowerMockito.mockStatic(DerbDbRepsository.class);
		// Creating testDB database
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL);
		PowerMockito.when(DerbDbRepsository.getConnection()).thenReturn(connection);
	}

	@After
	public void tearDown() throws Exception {
		if (connection != null) {
			connection.close();
		}
	}

	@Test
	public void derbyDbConnectiontest() throws ClassNotFoundException, SQLException {
		connection = DerbDbRepsository.getConnection();
		assertNotNull(connection);

	}

}
