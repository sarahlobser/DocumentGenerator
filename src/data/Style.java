package data;

public class Style {
	private String name;
	private String fileName;
	private String fontUrl;
	public Style() {
		super();
	}
	public Style(String name, String fileName, String fontUrl) {
		super();
		this.name = name;
		this.fileName = fileName;
		this.fontUrl = fontUrl;
	}
	public Style(String line) {
		String[] tokens = line.split(",");
		this.setName(tokens[0]);
		this.setFileName(tokens[1]);
		this.setFontUrl(tokens[2]);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFontUrl() {
		return fontUrl;
	}
	public void setFontUrl(String fontUrl) {
		this.fontUrl = fontUrl;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCSV() {
		return getName() + "," + getFileName() + "," + getFontUrl();
	}

}
