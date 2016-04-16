package controllers;

//import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.Doc;
import data.DocIO;

@Controller
@SessionAttributes("doc")
public class DocController {
	@Autowired
	private DocIO io;
	
	@ModelAttribute("doc")
	public Doc initDoc() {
		return new Doc();
	}
	//Loads the editor view with the session doc
	@RequestMapping(path="edit.do", method=RequestMethod.GET)
	public ModelAndView createDoc(@ModelAttribute("doc") Doc doc) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editor.jsp");
		doc.setName("firstdoc.html");
		mv.addObject("doc", doc);
		mv.addObject("docs", io.getDocs());
		return mv;
	}
//	@RequestMapping(path="edit.do", method=RequestMethod.POST)
//	public ModelAndView editDoc(String fileName) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("editor.jsp");
//		mv.addObject("doc", io.getDoc(fileName));
//		return mv;
//	}
//	@RequestMapping(path="io.do", method=RequestMethod.GET)
//	public ModelAndView makeDoc() {
//		// Prime the model with an empty document object so that
//		// the form can populate it with values
//		Doc doc = new Doc();
//		return new ModelAndView("editor.jsp", "doc", doc);
//	}
//	
//	@RequestMapping(path="createDoc.do", method=RequestMethod.POST)
//	public String addDoc(Doc doc) {
//		io.addDoc(doc);
//		// book command object is already stored in the request
//		// no need to add it to the ModelAndView again
//		return "editor.jsp";
//	}
	
	@RequestMapping(path="edit.do", method=RequestMethod.POST)
	public ModelAndView addSnippet(StringBuilder snippet, 
			@ModelAttribute("doc") Doc doc, 
			String tag, int insertIndex, String grabIndex) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editor.jsp");
		switch (tag) {
		case "txt":
			doc.addText(snippet);
			break;
		case "p": case "h1": case "h2": case "h3": case "h4": case "h5": case "h6":
			doc.wrapTag(snippet, tag);
			break;
		case "comment":
			doc.wrapHtmlComment(snippet);
			break;
		case "code":
			doc.wrapCode(snippet);
			break;
		case "last":
			snippet = doc.getLastBlock();
			mv.addObject("snippet", snippet);
			break;
		case "grab":
			snippet = doc.getBlock(grabIndex);
			mv.addObject("snippet", snippet);
			break;
		case "add":
			doc.addBlock(snippet, insertIndex);
			break;
		case "clear":
			doc.clearAll();
			break;
		}
		mv.addObject("docs", io.getDocs());
		return mv;
	}
	
	@RequestMapping(path="io.do")
	public ModelAndView ioTransfer(@ModelAttribute("doc") Doc doc, 
			String name, String chooseFile,
			@RequestParam("iotransfer") String command) {
		ModelAndView mv = new ModelAndView();
		if (command.equals("Open")) {
			doc = new Doc(io.getDoc(chooseFile));
		}
		else if (command.equals("Save")) {
			Doc saveDoc = new Doc (doc);
			saveDoc.setName(name);
			io.addDoc(saveDoc);
		}
		mv.setViewName("editor.jsp");
		mv.addObject("docs", io.getDocs());
		for (Doc d : io.getDocs()) {
			System.out.println(d.getName());
			for (StringBuilder sb : d.getContent()) {
				System.out.println(sb);
			}
		}
		mv.addObject("doc", doc);
		return mv;
	}


}
