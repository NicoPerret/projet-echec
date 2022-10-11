package fr.echec.demowebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import fr.echec.classe.historique.Message;

@Controller
public class WebControllerChat {

	@MessageMapping("/hello")
	@SendTo("/topic/chat")
	public Hello messagerie(Message message) throws Exception {
		return new Hello(message.getMessage());
	}

}
