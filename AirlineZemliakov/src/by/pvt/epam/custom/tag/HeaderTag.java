package by.pvt.epam.custom.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class HeaderTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {

		try {
			String lang = (String) pageContext.getSession()
					.getAttribute("lang");
			Locale locale = new Locale(lang);
			Locale.setDefault(locale);
			ResourceBundle rb = ResourceBundle.getBundle(
					"resources.pagecontent", locale);
			JspWriter out = pageContext.getOut();
			String header = "<h1><a href=\"index.jsp\" id=\"logo\"></a></h1><span id=\"slogan\">"
					+ rb.getString("header.zemliakov") + "</span>";
			out.write(header);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

}
