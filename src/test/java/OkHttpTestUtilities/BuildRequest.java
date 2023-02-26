package OkHttpTestUtilities;

import Utility.OkHttpBaseTest;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.Map;
import java.util.Objects;

import static TestUtilities.Constants.GET_ALL_ENDPOINT;
import static TestUtilities.Constants.GET_BY_ID_ENDPOINT;


public class BuildRequest extends OkHttpBaseTest {
    public static String addQueryParams() {
        Map<String, String> queryParams = getApiKeyAndTokenFromConfigFile();

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://api.trello.com/1")).newBuilder();

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        String queryParamsAsString = urlBuilder.build().toString();
        return queryParamsAsString.substring(24);
    }

    public static Request buildGETRequest() {

        HttpUrl endpoint = Objects.requireNonNull(HttpUrl.parse(uri + GET_ALL_ENDPOINT + addQueryParams()))
                .newBuilder()
                .build();

        return new Request.Builder()
                .url(endpoint)
                .get()
                .build();
    }

    public static Request buildGETByIdRequest(String boardId) {

        HttpUrl endpoint = Objects.requireNonNull(HttpUrl.parse(uri + GET_BY_ID_ENDPOINT.replace("{id}", boardId)
                        + addQueryParams()))
                .newBuilder()
                .build();

        return new Request.Builder()
                .url(endpoint)
                .get()
                .build();
    }
}
