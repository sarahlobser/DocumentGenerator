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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
/*
 * Below is the location where files are written to and read from, when the project is run locally.
 */
//~/Users/sarahlobser/SD/Java/Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CRUD/output
public class HtmlDocIO implements DocIO {
	@Autowired
	WebApplicationContext ac;
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
		boolean contains = false;
		int matchIndex = -1;
		for (Doc d : docs) {
			if (d.getName().equals(doc.getName())) {
				contains = true;
				matchIndex = docs.indexOf(d);
				break;
			}
		}
		if (contains) docs.set(matchIndex, doc);
		else docs.add(0, doc);
	}
	public void writeDoc (Doc d) {
		File f = new File(ac.getServletContext().getRealPath("/") + d.getName());
		try{
			FileWriter fw = new FileWriter(f);
			PrintWriter pw = new PrintWriter(fw);
			d.wrapHtmlFileBody();
			for (StringBuilder line : d.getContent()) {
				pw.print(line.toString());
			}
			pw.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		d.setLength();
	}
	public void readDoc(Doc d) {
		BufferedReader in = null;
		try {
			InputStream is = ac.getResource(d.getName()).getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = in.readLine()) != null) {
				d.getContent().add(new StringBuilder(line));
			}
			in.close();
		}
		catch (IOException e){
			System.out.println(e.getMessage());
			d.getContent().add(new StringBuilder("no such file"));
		}
		d.setLength();
	}

}
