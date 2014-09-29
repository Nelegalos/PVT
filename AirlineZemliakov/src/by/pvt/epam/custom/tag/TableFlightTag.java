package by.pvt.epam.custom.tag;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import by.pvt.epam.entity.Flight;

@SuppressWarnings("serial")
public class TableFlightTag extends BodyTagSupport {

	private List<Flight> flights;
	private String buttonPart1;
	private String buttonPart2;

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void setButtonPart1(String buttonPart1) {
		this.buttonPart1 = buttonPart1;
	}

	public void setButtonPart2(String buttonPart2) {
		this.buttonPart2 = buttonPart2;
	}

	@Override
	public int doStartTag() throws JspException {

		if (flights.isEmpty()) {
			return SKIP_BODY;
		}

		int flightsPage = (Integer) pageContext.getSession().getAttribute(
				"flightsPage");
		try {
			JspWriter out = pageContext.getOut();
			Flight flight = flights.get(flightsPage);
			out.write("<tr><td>" + flight.getId() + "</td><td>"
					+ flight.getTo() + "</td><td>" + flight.getFrom()
					+ "</td><td>" + flight.getDate() + "</td><td>"
					+ buttonPart1 + flight.getId() + buttonPart2 + "</td></tr>");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
