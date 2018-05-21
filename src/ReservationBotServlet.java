

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Message;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.SendResponse;

/**
 * Servlet implementation class ReservationBotServlet
 */
@WebServlet("/ReservationBotServlet")
public class ReservationBotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String accessToken = "EAACZCLCNfVkEBAP1nKKDR0ew5zlilNnTdZBtHYwQWewIrWk3IbZCHknUYUQZAOfrHydM6mv2ww4Vox8HZAkYWFADG1bLR5vN0pCTfxE1RKGaQbOeZAJEuP8q8K08HfcZCTMP4nXZA7QiZBUZCPNAFZCRsFDpauLEvcOyBV3aikyz2h5tgZDZD";
    private String verifyToken = "TestReservationBot";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hubToken = request.getParameter("hub.verify_token");
		String hubChallenge = request.getParameter("hub.challenge");
		
		if(verifyToken.equals(hubToken)) {
			response.getWriter().write(hubChallenge);
			response.getWriter().flush();
			response.getWriter().close();
		} else {
			response.getWriter().write("incorrect token");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	void SendMessage(IdMessageRecipient recipient, Message message) {
		// create a version 2.6 client
		FacebookClient pageClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_6);

		SendResponse resp = pageClient.publish("me/messages", SendResponse.class,
		     Parameter.with("recipient", recipient), // the id or phone recipient
			 Parameter.with("message", message)); // one of the messages from above
	}

}
