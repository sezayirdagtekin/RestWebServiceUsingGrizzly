package com.sezayir.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilTest {

	private static final String DIRECTORY="temp1";
	private static final String PATH = DIRECTORY+"//test-file.txt";
	private static final String INVALID_DIRECTORY = "invalid-dir//password.txt";

	@Before
	public void setUp() throws IOException {
		File f = new File(PATH);
		f.getParentFile().mkdirs();
		f.createNewFile();

	}

	@Test
	public void givenDirectory_whenDeletedWithRecursion_thenIsGone() throws IOException {

		boolean result;
		result = Util.deleteDirectoryStream(DIRECTORY);
		assertThat(result, is(true));
		
		result = Util.deleteDirectoryStream(INVALID_DIRECTORY);
		assertThat(result, is(false));
	}

	@After
	public void tearDown() throws Exception {
		//if directory still exist delete otherwise return false
		Util.deleteDirectoryStream(DIRECTORY);
	}

}
