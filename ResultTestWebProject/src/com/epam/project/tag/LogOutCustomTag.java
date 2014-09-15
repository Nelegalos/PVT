package com.epam.project.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.tagext.TagSupport;

import com.epam.project.configuration.ConfigurationManager;

public class LogOutCustomTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private static final String BUNDLE_KEY_HELLO = "hello";
	private static final String BUNDLE_KEY_LOG_OUT = "logOut";
	private static final String BUNDLE_PATH = "bundle";
	private String firstName;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

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
					.append("<input name=\"page\" type=\"hidden\" value=\"logOut\" />");
			htmlContent.append("<h1>");
			htmlContent.append(rs.getString(BUNDLE_KEY_HELLO) + ", ");
			htmlContent.append(firstName + " </h1><p class=\"alignRight\" />");
			htmlContent.append("<input type=\"submit\" value=\"");
			htmlContent.append(rs.getString(BUNDLE_KEY_LOG_OUT) + "\"  />");
			htmlContent.append("</p></form></td></tr></table>");
			pageContext.getOut().write(htmlContent.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

}
