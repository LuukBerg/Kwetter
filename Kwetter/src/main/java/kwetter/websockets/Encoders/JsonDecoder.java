package kwetter.websockets.Encoders;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;


public class JsonDecoder implements Decoder.Text<Message> {

    private final Gson gson = new Gson();

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public Message decode(String arg0) throws DecodeException {
        return gson.fromJson(arg0, Message.class);
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }


    @Override
    public void destroy() {

    }
}
