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
	private Style style;
	//private String styleFilePath;
	private int length;
	
	/*
	 * Constructor
	 */
	public Doc() {
		this("",new ArrayList<StringBuilder>(), new Style());
	}
	public Doc (String name, List<StringBuilder> content, Style style) {
		this.name = name;
		this.content = content;
		this.style = style;
		this.setLength();
	}
	public Doc (Doc feeder) {
		this.name = feeder.getName();
		this.content = new ArrayList<StringBuilder>();
		for (StringBuilder sb : feeder.getContent()) {
			StringBuilder addition = new StringBuilder(sb);
			this.content.add(addition);
		}
		this.style = feeder.getStyle();
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
	public void highLight (StringBuilder s, String color) {
		int index = s.indexOf(">");
		if (index > 0) {
			s.insert(index, " style=\"background-color:" + color + "\"");
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
			style.getFontUrl() + 
			"<link rel=\"stylesheet\" type=\"text/css\" href=\"" + 
			style.getFileName() + 
			"\"> <title>DocGen</title></head><body>"));
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

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}
	public int getLength() {
		return length;
	}
	public void setLength() {
		this.length = content.size();
	}

}
