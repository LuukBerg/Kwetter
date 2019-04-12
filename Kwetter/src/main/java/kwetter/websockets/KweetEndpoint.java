package kwetter.websockets;

import com.google.gson.Gson;
import kwetter.JWT.JwtManager;
import kwetter.model.DTO.KweetDTO;
import kwetter.model.models.Kweet;
import kwetter.model.models.Profile;
import kwetter.model.models.User;
import kwetter.service.KweetService;
import kwetter.service.UserService;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(
        value="/api/socket/{token}"
)
public class KweetEndpoint {


    static Map<String, Long> sessions = new ConcurrentHashMap<>();
    static Map<Long, Session> sessions1 = new ConcurrentHashMap<>();
    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;


    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session){
        System.out.println("onOpen");
        if(token == null || token == ""){
            sendError(session, "client not authenticated");
            System.out.println("client not authenticated");
            closedConnection(session);
            return;
        }
            System.out.println(token);
            String username = JwtManager.decodeToken(token);
            if(username == null){
                System.out.println("invalid token");
                sendError(session, "invalid token");
                return;
            }
            User user = userService.findByUsername(username);
            sessions.put(session.getId(), user.getId());
            sessions1.put(user.getId(), session);
            System.out.println("Client connected, id: " + user.getId() + " session: " + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException, EncodeException {
        User user = userService.findById(sessions.get(session.getId()));
        Kweet kweet = new Kweet(message, user.getProfile());
        kweetService.create(kweet);
        KweetDTO dto = KweetDTO.transform(kweet);
        Gson gson = new Gson();
        String obj = gson.toJson(dto);
        session.getBasicRemote().sendObject(obj);
        for(Profile profile : user.getProfile().getFollowers()){
            Session followerSession = sessions1.get(profile.getOwner().getId());
            if(followerSession != null){
                followerSession.getBasicRemote().sendObject(obj);
            }
        }


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
            System.out.println("closed ");
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

}
