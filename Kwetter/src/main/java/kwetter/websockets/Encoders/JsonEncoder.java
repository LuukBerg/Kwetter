package kwetter.websockets.Encoders;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class JsonEncoder implements Encoder.Text<Message>{

    private final Gson gson = new Gson();

    @Override
    public String encode(Message arg0) throws EncodeException {
        return gson.toJson(arg0);
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
