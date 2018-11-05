package test.util;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class ResourceUtils {

	private ResourceUtils() {

	}

	public static String loadResourceAsString(String pathAsString) {
		try {
			return IOUtils.toString(new ClassPathResource(pathAsString).getInputStream(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException("Error loading resource " + pathAsString, e);
		}
	}

	public static <T> T loadResourceAsObject(String pathAsString, Class<T> type) {
		return new Gson().fromJson(loadResourceAsString(pathAsString), type);
	}
}
