package OkHttpTestUtilities;

import Utility.OkHttpBaseTest;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Map;
import java.util.Objects;

import static TestUtilities.Constants.*;


public class BuildRequest extends OkHttpBaseTest {

    public static Request buildGETRequest() {

        HttpUrl endpoint = createEndpoint(GET_ALL_ENDPOINT);

        return new Request.Builder()
                .url(endpoint)
                .get()
                .build();
    }

    public static Request buildGETByIdRequest(String boardId) {

        HttpUrl endpoint = createEndpointWithId(GET_BY_ID_ENDPOINT, boardId);

        return new Request.Builder()
                .url(endpoint)
                .get()
                .build();
    }

    public static Request buildDELETERequest(String boardId) {
        HttpUrl endpoint = createEndpointWithId(DELETE_ENDPOINT, boardId);

        return new Request.Builder()
                .url(endpoint)
                .delete()
                .build();
    }

    public static Request buildPOSTRequest(RequestBody payload) {
        HttpUrl endpoint = createEndpoint(POST_ENDPOINT);

        return new Request.Builder()
                .url(endpoint)
                .post(payload)
                .build();
    }

    public static Request buildPUTRequest(String boardId, RequestBody payload) {
        HttpUrl endpoint = createEndpointWithId(PUT_ENDPOINT, boardId);

        return new Request.Builder()
                .url(endpoint)
                .put(payload)
                .build();
    }


    public static String addQueryParams() {
        Map<String, String> queryParams = getApiKeyAndTokenFromConfigFile();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://api.trello.com/1")).newBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        String queryParamsAsString = urlBuilder.build().toString();
        return queryParamsAsString.substring(24);
    }

    public static HttpUrl createEndpoint(String endpoint) {

        return Objects.requireNonNull(HttpUrl.parse(uri + endpoint + addQueryParams()))
                .newBuilder()
                .build();
    }

    public static HttpUrl createEndpointWithId(String endpoint, String boardId) {
        return Objects.requireNonNull(HttpUrl.parse(uri + endpoint.replace("{id}", boardId) + addQueryParams()))
                .newBuilder()
                .build();
    }
}
