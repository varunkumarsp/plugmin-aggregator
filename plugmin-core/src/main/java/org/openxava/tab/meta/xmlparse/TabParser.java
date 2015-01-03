package org.openxava.tab.meta.xmlparse;




import org.openxava.filters.meta.MetaFilter;
import org.openxava.filters.meta.xmlparse.FilterParser;
import org.openxava.model.meta.xmlparse.ModelParser;
import org.openxava.tab.meta.MetaRowStyle;
import org.openxava.tab.meta.MetaTab;
import org.openxava.util.Is;
import org.openxava.util.XavaException;
import org.openxava.util.xmlparse.ParserUtil;
import org.openxava.util.xmlparse.XmlElementsNames;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author: Javier Paniza
 */
public class TabParser extends XmlElementsNames {

	
	
	public static MetaTab parseTab(Node n, int lang) throws XavaException {
		Element el = (Element) n;
		MetaTab e = new MetaTab();
		e.setName(el.getAttribute(xname[lang]));
		String excludeByKey = el.getAttribute(xexclude_by_key[lang]);
		if (!Is.emptyString(excludeByKey)) {
			e.setExcludeByKey(Boolean.valueOf(excludeByKey).booleanValue());
		}		
		String excludeAll = el.getAttribute(xexclude_all[lang]);
		if (!Is.emptyString(excludeAll)) {
			e.setExcludeAll(Boolean.valueOf(excludeAll).booleanValue());
		}
		e.setDefaultPropertiesNames(ParserUtil.getString(el, xproperties[lang]));		
		e.setMetaFilter(createFilter(el, lang));
		fillRowStyles(el, e, lang);
		e.setBaseCondition(ParserUtil.getString(el, xbase_condition[lang]));		
		e.setDefaultOrder(ParserUtil.getString(el, xdefault_order[lang]));
		e.setEditor(el.getAttribute(xeditor[lang]));
		fillProperties(el, e, lang);
		return e;
	}
		
	private static MetaFilter createFilter(Element el, int lang) throws XavaException {
		NodeList l = el.getChildNodes();				
		int c = l.getLength();
		for (int i = 0; i < c; i++) {
			Node n = l.item(i);			
			if (xfilter[lang].equals(n.getNodeName())) {
				return FilterParser.parseFilter(l.item(i), lang);
			} 
		}
		return null;		
	}
		
	private static void fillProperties(Element el, MetaTab container, int lang)
		throws XavaException {
		NodeList l = el.getChildNodes();
		int c = l.getLength();
		for (int i = 0; i < c; i++) {
			if (!(l.item(i) instanceof Element)) continue;
			Element d = (Element) l.item(i);
			String type = d.getTagName();
			if (type.equals(xproperty[lang])) {
				container.addMetaProperty(ModelParser.createProperty(d, lang));
			}
		}
	}
	
	private static void fillRowStyles(Element el, MetaTab container, int lang)
		throws XavaException {
		NodeList l = el.getChildNodes();
		int c = l.getLength();
		for (int i = 0; i < c; i++) {
			if (!(l.item(i) instanceof Element)) continue;
			Element d = (Element) l.item(i);
			String type = d.getTagName();
			if (type.equals(xrow_style[lang])) {
				container.addMetaRowStyle(createRowStyle(d, lang));
			}
		}
	}
	
	public static MetaRowStyle createRowStyle(Node n, int lang) throws XavaException {
		Element el = (Element) n;
		MetaRowStyle style = new MetaRowStyle();
		style.setStyle(el.getAttribute(xstyle[lang]));
		style.setProperty(el.getAttribute(xproperty[lang]));
		style.setValue(el.getAttribute(xvalue[lang]));
		return style;
	}	
			
}