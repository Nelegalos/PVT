package com.epam.project.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.tagext.TagSupport;

import com.epam.project.configuration.ConfigurationManager;

public class BackCustomTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private static final String BUNDLE_KEY_BACK = "back";
	private static final String BUNDLE_PATH = "bundle";

	public int doStartTag() {
		try {
			String lang = (String) pageContext.getSession()
					.getAttribute("lang");
			Locale locale = new Locale(lang);
			ResourceBundle rs = ResourceBundle.getBundle(
					ConfigurationManager.getProperty(BUNDLE_PATH), locale);
			StringBuilder htmlContent = new StringBuilder(
					"<table class = \"navigation\" ><tr><td class = \"alignMidle\">");
			htmlContent.append("<form action=\"Test\" method=\"post\">");
			htmlContent
					.append("<input name=\"page\" type=\"hidden\" value=\"back\" />");
			htmlContent.append("<input type=\"submit\" value=\"");
			htmlContent.append(rs.getString(BUNDLE_KEY_BACK) + "\"  />");
			htmlContent.append("</form></td></tr></table>");
			pageContext.getOut().write(htmlContent.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
