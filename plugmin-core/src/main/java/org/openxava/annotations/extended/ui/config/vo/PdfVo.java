package org.openxava.annotations.extended.ui.config.vo;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.openxava.annotations.parse.ReflectionUtil.calledFrom;

import org.apache.commons.lang3.StringUtils;
import org.openxava.annotations.extended.CompositeField;
import org.openxava.annotations.extended.VoFor;
import org.openxava.annotations.extended.ui.config.grid.Pdf;
import org.openxava.annotations.parse.DefaultValue;
import org.openxava.annotations.parse.GenericConfigSerializer;
import org.openxava.annotations.parse.JsField;
import org.openxava.annotations.parse.JsFieldArray;
import org.openxava.annotations.parse.JsFieldString;
import org.openxava.annotations.parse.JsFieldValueResolver;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@VoFor(Pdf.class)
@JsonSerialize(using = GenericConfigSerializer.class, as=String.class)
public class PdfVo implements JsFieldValueResolver {
	
	
	private String author;
	
	@DefaultValue("Kendo UI PDF Generator")
	private String creator;
	
	@DefaultValue("Export.pdf")
	private String fileName;
	
	private String keywords;
	
	@DefaultValue("false")
	private Boolean landscape;
	
	private MarginVo margin;
	
	@CompositeField("paperSize")
	@DefaultValue("auto")
	private String paperSizeStr;
	
	@CompositeField("paperSize")
	private String[] paperSizeArray;
	
	private String proxyURL;
	private String subject;
	private String title;

	
	private PdfVo() {
	}
	
	public static PdfVo instance() throws InstantiationRestrictedException {
		if(!calledFrom(TabConfigVo.class)) {
			throw new InstantiationRestrictedException("No permission to instantiate");
		}
		return new PdfVo();
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		if(author != null)
			this.author = author;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		if(isNotEmpty(creator))
			this.creator = creator;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if(isNotEmpty(fileName) &&!fileName.equals("Export.pdf"))
			this.fileName = fileName;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		if(isNotEmpty(keywords))
			this.keywords = keywords;
	}

	public Boolean isLandscape() {
		return landscape;
	}

	public void setLandscape(Boolean landscape) {
		if(landscape != null)
			this.landscape = landscape;
	}

	public MarginVo getMargin_() {
		if(margin == null)
			try {
				margin = MarginVo.instance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return margin;
	}
	
	public MarginVo getMargin() {
		return margin;
	}

	public void setMargin(MarginVo margin) {
		if(margin != null)
			this.margin = margin;
	}

	public String getPaperSizeStr() {
		return paperSizeStr;
	}

	public void setPaperSizeStr(String paperSizeStr) {
		if(isNotEmpty(paperSizeStr) &&!paperSizeStr.equals("auto"))
			this.paperSizeStr = paperSizeStr;
	}

	public String[] getPaperSizeArray() {
		return paperSizeArray;
	}

	public void setPaperSizeArray(String[] paperSizeArray) {
		if(paperSizeArray != null)
			this.paperSizeArray = paperSizeArray;
	}

	public String getProxyURL() {
		return proxyURL;
	}

	public void setProxyURL(String proxyURL) {
		if(isNotEmpty(proxyURL))
			this.proxyURL = proxyURL;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		if(isNotEmpty(subject))
			this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(isNotEmpty(title))
			this.title = title;
	}

	
	public PdfVo copyFrom(Pdf pdf) {
		setAuthor(pdf.author());
		setCreator(pdf.creator());
		setFileName(pdf.fileName());
		setKeywords(pdf.keywords());
		setLandscape(pdf.landscape().getBool());
		getMargin_().copyFrom(pdf.margin());
		setPaperSizeArray(pdf.paperSizeArray());
		setPaperSizeStr(pdf.paperSizeStr());
		setProxyURL(pdf.proxyURL());
		setSubject(pdf.subject());
		setTitle(pdf.title());
		return this;
	}

	@Override
	public JsField resolve(String field) {
		if(field.equals("paperSize")) {
			return resolvePaperSize();
		}
		return null;
	}

	private JsField resolvePaperSize() {
		if(StringUtils.isNotEmpty(paperSizeStr)) {
			return new JsFieldString("paperSize", paperSizeStr, "paperSizeStr");
		} else if(paperSizeArray != null && paperSizeArray.length > 0) {
			return new JsFieldArray("paperSize", paperSizeArray, "paperSizeArray");
		}
		return null;
	}

}
