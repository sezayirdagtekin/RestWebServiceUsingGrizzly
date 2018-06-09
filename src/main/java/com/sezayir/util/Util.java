package com.sezayir.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class Util {



	public static boolean deleteDirectoryStream( String file_path) throws IOException {

		Path path = Paths.get(file_path);
		if (path != null && Files.exists(path)) {
			Files.walk(path).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			return true;
		}
		return false;
	}

}
