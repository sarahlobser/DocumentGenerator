package data;

import java.util.List;

public interface DocIO {
	public List<Doc> getDocs();
	public void setDocs(List<Doc> docs);
	public Doc getDoc (String name);
	public void addDoc (Doc doc);
}