package kwetter.websockets;

import kwetter.JWT.JwtManager;
import kwetter.model.models.User;
import kwetter.service.KweetService;
import kwetter.service.UserService;
import kwetter.websockets.Encoders.JsonDecoder;
import kwetter.websockets.Encoders.JsonEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(
        value="/api/socket/{username}",
        encoders = JsonEncoder.class,
        decoders = JsonDecoder.class,
        configurator =  HttpSessionProvider.class
)
public class KweetEndpoint {

    private static final Logger LOG = Logger.getLogger(KweetEndpoint.class.getName());

    Map<Long, Session> sessions = new ConcurrentHashMap<Long, Session>();

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    private HttpSession httpSession;

    private Session session;

    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session){

        if(token == null || token == ""){
            sendError(session, "client not authenticated");
            closedConnection(session);
            return;
        }

            String username = JwtManager.decodeToken(token);
            if(username == null){
                sendError(session, "invalid token");
                return;
            }
            User user = userService.findByUsername(username);
            sessions.put(user.getId(), session);
            System.out.println("Client connected, id: " + user.getId());


        LOG.log(Level.INFO, "onOpen: username: {0}, session: {1}", new Object[]{username, session});
    }

    @OnMessage
    public void onMessage(Session session, String message){
        System.out.println("Recieved websocket message: "+message);
    }

    private void sendError(Session session, String message)
    {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

   @OnClose
   public static void closedConnection(Session session){
        try{
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

}
