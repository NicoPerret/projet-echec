/*
package fr.echec.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp").setAllowedOrigins("*");
	}

	private Map<String, Integer> progress = new HashMap<>();
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Bean
	public CommandLineRunner websocketDemo() {
		return (args) -> {
			while (true) {
				try {
					Thread.sleep(3 * 1000); // Each 3 sec.
					progress.put("num1", randomWithRange(0, 100));
					progress.put("num2", randomWithRange(0, 100));
					messagingTemplate.convertAndSend("/topic/progress", this.progress);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	private int randomWithRange(int min, int max) {
		int range = Math.abs(max - min) + 1;
		return (int) (Math.random() * range) + (min <= max ? min : max);
	}

}
*/
