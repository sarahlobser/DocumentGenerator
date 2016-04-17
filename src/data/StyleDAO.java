package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class StyleDAO {
	private static final String FILE_NAME = "/WEB-INF/fontStyles.csv";
	private Map<String, Style> styles = new HashMap<>();
	@Autowired
	private ApplicationContext ac;

	@PostConstruct
	public void init() {
		// Retrieve an input stream from the application context
		// rather than directly from the file system
		try (InputStream is = ac.getResource(FILE_NAME).getInputStream();
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line = buf.readLine();
			while ((line = buf.readLine()) != null) {
				Style s = new Style(line);
				styles.put(s.getName(), s);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public Collection<Style> getAllStyles() {
		return styles.values();
	}
	public Style getStyle(String name) {
		return styles.get(name);
	}
}
