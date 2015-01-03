package org.openxava.filters.meta.xmlparse;



import org.openxava.filters.meta.MetaFilter;
import org.openxava.util.XavaException;
import org.openxava.util.meta.MetaSet;
import org.openxava.util.xmlparse.XmlElementsNames;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author: Javier Paniza
 */
public class FilterParser extends XmlElementsNames {

	
	
	public static MetaFilter parseFilter(Node n, int lang) throws XavaException {
		Element el = (Element) n;
		MetaFilter e = new MetaFilter();
		e.setClassName(el.getAttribute(xclass[lang]));
		fillSets(el, e, lang);
		return e;
	}
	
	private static void fillSets(Element el, MetaFilter container, int lang)
		throws XavaException {
		NodeList l = el.getElementsByTagName(xset[lang]);
		int c = l.getLength();
		for (int i = 0; i < c; i++) {
			container._addMetaSet(createSet(l.item(i), lang));
		}
	}
	
	
	private static MetaSet createSet(Node n, int lang) throws XavaException {
		Element el = (Element) n;
		MetaSet a = new MetaSet();		
		a.setPropertyName(el.getAttribute(xproperty[lang]));
		a.setPropertyNameFrom(el.getAttribute(xfrom[lang]));
		a.setValue(el.getAttribute(xvalue[lang]));		
		return a;
	}
				
}