package data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class Doc {
	@Autowired
	WebApplicationContext ac;
	/*
	 * Fields
	 */
	private String name;
	//private String filePath;
	private List<StringBuilder> content;
	//private String timeStamp;
	private String stylesheet;
	//private String styleFilePath;
	private int length;
	
	/*
	 * Constructor
	 */
	public Doc() {
		this("",new ArrayList<StringBuilder>(), "");
	}
	public Doc (String name, List<StringBuilder> content, String stylesheet) {
		this.name = name;
		//this.filePath = filePath;
		this.content = content;
//		this.timeStamp = timeStamp;
		this.stylesheet = stylesheet;
//		this.styleFilePath = styleFilePath;
		this.setLength();
	}
	public Doc (Doc feeder) {
		this.name = feeder.getName();
//		this.filePath = feeder.getFilePath();
		this.content = new ArrayList<StringBuilder>();
		for (StringBuilder sb : feeder.getContent()) {
			StringBuilder addition = new StringBuilder(sb);
			this.content.add(addition);
		}
//		this.timeStamp = feeder.getTimeStamp();
		this.stylesheet = feeder.getStylesheet();
//		this.styleFilePath = feeder.getStyleFilePath();
		this.setLength();
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
	public void highLight (StringBuilder s, String color) {
		int index = s.indexOf(">");
		if (index > 0) {
			s.insert(index, "style=\"background-color:" + color + "\">");
		}
		else s.insert(0,  "<sp style=\"background-color:" + color + "\">").append("</sp>");
		content.add(s);
		setLength();
	}
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
		content.removeAll(toRemove);
		setLength();
		return out;
	}
	public void addBlock (StringBuilder s, int index) {
		content.add(index, s);
		setLength();
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
				getStylesheet() + "\">" +
		        "<title>Testing</title></head><body>"));
		content.add(new StringBuilder("</body></html>"));
		
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

	public List<StringBuilder> getContent() {
		return content;
	}

	public void setContent(List<StringBuilder> content) {
		this.content = content;
	}

	public String getStylesheet() {
		return stylesheet;
	}

	public void setStylesheet(String stylesheet) {
		this.stylesheet = stylesheet;
	}
	public int getLength() {
		return length;
	}
	public void setLength() {
		this.length = content.size();
	}
	
	
	

}
