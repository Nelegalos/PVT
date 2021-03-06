package by.pvt.epam.custom.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FooterTag extends TagSupport {

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
			String footer = "<footer><div class=\"wrapper\"><div class=\"links\">"
					+ rb.getString("contact.email") + "</div></div></footer>";
			out.write(footer);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
