package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class Doc {
	@Autowired
	WebApplicationContext ac;
	/*
	 * Fields
	 */
	private String name;
	private String filePath;
	private List<StringBuilder> content;
	private String timeStamp;
	private String stylesheet;
	private String styleFilePath;
	private int length;
	
	/*
	 * Constructor
	 */
	public Doc() {
		this("","",new ArrayList<StringBuilder>(), "", "", "");
	}
	public Doc (String name, String filePath, List<StringBuilder> content, String timeStamp, 
			String stylesheet, String styleFilePath) {
		this.name = name;
		this.filePath = filePath;
		this.content = content;
		this.timeStamp = timeStamp;
		this.stylesheet = stylesheet;
		this.styleFilePath = styleFilePath;
	}
	public Doc (Doc feeder) {
		this.name = feeder.getName();
		this.filePath = feeder.getFilePath();
		this.content = new ArrayList<StringBuilder>();
		for (StringBuilder sb : feeder.getContent()) {
			StringBuilder addition = new StringBuilder(sb);
			this.content.add(addition);
		}
		this.timeStamp = feeder.getTimeStamp();
		this.stylesheet = feeder.getStylesheet();
		this.styleFilePath = feeder.getStyleFilePath();
	}
	
	/*
	 * Methods
	 */
	public void addText (StringBuilder s) {
		content.add(s);
		setLength();
	}
	public void wrapTag (StringBuilder s, String tag) {
		String openTag = "<" + tag + ">";
		String closeTag = "</" + tag + ">";
		content.add(s.insert(0, openTag).append(closeTag));
		setLength();
	}
//	public void wrapSpan (StringBuilder s) {
//		content.add(s.insert(0, "<sp>").append("</sp>"));
//	}
	public void wrapHtmlComment (StringBuilder s) {
		content.add(s.insert(0, "<!-- ").append("-->"));
		setLength();
	}
	public void wrapCode (StringBuilder s) {
		String rep = s.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		StringBuilder out = new StringBuilder("<pre><code>" + rep + "</code></pre>");
		content.add(out);
		setLength();
	}
	public StringBuilder getLastBlock () {
		StringBuilder out = content.get(content.size()-1);
		content.remove(out);
		setLength();
		return out;
	}
	public StringBuilder getBlock (int index) {
		StringBuilder out = content.get(index);
		//content.remove(out);
		//setLength();
		return out;
	}
	public StringBuilder getBlock (String indexString) {
		String[] tokens = indexString.split(" ");
		int[] index = new int[tokens.length];
		List<StringBuilder> toRemove = new ArrayList<>();
		for (int i = 0; i < tokens.length; i++) {
			index[i] = Integer.parseInt(tokens[i]);
		}
		StringBuilder out = new StringBuilder();
		for (int i : index) {
			out.append(getBlock(i));
			toRemove.add(getBlock(i));
		}
//		for (int i : index) {
//			content.remove(i);
//		}
		content.removeAll(toRemove);
		setLength();
		return out;
	}
	public void addBlock (StringBuilder s, int index) {
		content.add(index, s);
	}
	public void clearAll () {
		content.clear();
		setLength();
	}
	public void wrapHtmlFileBody () {
		content.add(0,new StringBuilder("<!DOCTYPE html><html><head>" + 
				"<link href='https://fonts.googleapis.com/css?family=Schoolbell' rel='stylesheet' type='text/css'>" +
				"<link href='https://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'>"	+	
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"" + 
				getStyleFilePath() + getStylesheet() + "\">" +
		        "<title>Testing</title></head><body>"));
		content.add(new StringBuilder("</body></html>"));
		
	}

	public void readDoc() {
		BufferedReader in = null;
		try {
			InputStream is = ac.getResource(getFilePath() + getName()).getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = in.readLine()) != null) {
				content.add(new StringBuilder(line));
			}
			in.close();
		}
		catch (IOException e){
			System.out.println(e.getMessage());
			content.add(new StringBuilder("no such file"));
		}
		setLength();
	}
	public void writeDoc () {
		File f = new File(ac.getServletContext().getRealPath("/") + getFilePath() + getName());
		try{
			FileWriter fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw);
			for (StringBuilder line : content) {
				pw.println(line.toString());
			}
			pw.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	/*
	 * Gets and Sets
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<StringBuilder> getContent() {
		return content;
	}

	public void setContent(List<StringBuilder> content) {
		this.content = content;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStylesheet() {
		return stylesheet;
	}

	public void setStylesheet(String stylesheet) {
		this.stylesheet = stylesheet;
	}

	public String getStyleFilePath() {
		return styleFilePath;
	}

	public void setStyleFilePath(String styleFilePath) {
		this.styleFilePath = styleFilePath;
	}
	public int getLength() {
		return length;
	}
	public void setLength() {
		this.length = content.size();
	}
	
	
	

}
