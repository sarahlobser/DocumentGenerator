package data;

import java.util.ArrayList;
import java.util.List;

public class HtmlDocIO implements DocIO {
	private List<Doc> docs;
	
	public HtmlDocIO() {
		super();
		docs = new ArrayList<Doc>();
	}
	
	@Override
	public List<Doc> getDocs() {
		return docs;
	}
	@Override
	public void setDocs(List<Doc> docs) {
		this.docs = docs;
	}
	@Override
	public Doc getDoc(String name) {
		Doc out = new Doc();
		for (Doc doc : docs) {
			if(doc.getName().equals(name)) {
				return doc;
				//break;
			}
		}
		return out;
	}
	@Override
	public void addDoc(Doc doc) {
		docs.add(doc);
	}

}
