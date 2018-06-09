package com.sezayir.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilTest {

	private static final String PATH = "dir_temp//test-file.txt";
	private static final String INVALID_PATH = "dir_temp//test-file.txt";

	@Before
	public void setUp() throws IOException {
		File f = new File(PATH);
		f.getParentFile().mkdirs();
		f.createNewFile();

	}

	@Test
	public void givenDirectory_whenDeletedWithRecursion_thenIsGone() throws IOException {

		boolean result;
		result = Util.deleteDirectoryStream(PATH);
		assertThat(result, is(true));
		
		result = Util.deleteDirectoryStream(INVALID_PATH);
		assertThat(result, is(false));
	}

	@After
	public void tearDown() throws Exception {
		//if directory still exist delete otherwise return false
		Util.deleteDirectoryStream(PATH);
	}

}
