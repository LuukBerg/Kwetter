package kwetter.websockets;


import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class HttpSessionProvider extends ServerEndpointConfig.Configurator {

    public static HttpSession provide(EndpointConfig config){
        return (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        config.getUserProperties().put(HttpSession.class.getName(), request.getHttpSession());
    }


}
