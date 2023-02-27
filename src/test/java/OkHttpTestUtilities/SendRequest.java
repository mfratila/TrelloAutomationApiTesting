package OkHttpTestUtilities;

import Utility.OkHttpBaseTest;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class SendRequest extends OkHttpBaseTest {
    public static Response sendRequest(Request request) throws IOException {
        return client.newCall(request).execute();
    }
}
