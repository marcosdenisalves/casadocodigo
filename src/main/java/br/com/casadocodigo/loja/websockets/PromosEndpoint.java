package br.com.casadocodigo.loja.websockets;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/canal/promos")
public class PromosEndpoint {
	
	@OnOpen
	public void onMessage(Session session) {
		if (session.isOpen()) {
			try {
				Thread.sleep(10000);
				session.getBasicRemote().sendText(
						"{'titulo':'Livro Coraline com 40% de desconto', 'livroId':3}");
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
