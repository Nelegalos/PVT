package by.pvt.epam.custom.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class LogOutTag extends TagSupport {

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
			String form = "<form action=\"controller\" method=\"post\"><input type=\"hidden\" name=\"command\" value=\"logout\" /><input	class=\"button1\" type=\"submit\" value="
					+ rb.getString("option.logout") + " /></form>";
			out.write(form);
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

}
