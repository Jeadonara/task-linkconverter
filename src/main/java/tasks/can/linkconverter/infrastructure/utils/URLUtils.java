package tasks.can.linkconverter.infrastructure.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public abstract class URLUtils {

    private URLUtils() {
    }

    /**
     * @param source to be parsed as URL
     * @return a URL object from source
     */
    public static URL buildURL(String source) throws MalformedURLException {
        return new URL(source);
    }

    /**
     * @param source to be parsed as URI
     * @return a URI object from source
     */
    public static URI buildURI(String source) throws URISyntaxException {
        return new URI(source);
    }


    /**
     * @param url to get its query parameters
     * @return query parameters of input URL as a Map object
     */
    public static Map<String, String> getQueryParameters(URL url) {
        String query = url.getQuery();

        return getQueryMap(query);
    }

    /**
     * @param uri to get its query parameters
     * @return query parameters of input URL as a Map object
     */
    public static Map<String, String> getQueryParameters(URI uri) {
        String query = uri.getRawQuery();

        return getQueryMap(query);
    }

    private static Map<String, String> getQueryMap(String query) {
        if (!StringUtils.hasText(query) || Boolean.FALSE.equals(query.indexOf("=") > 0))
            return new HashMap<>();
        else if (Boolean.FALSE.equals(query.indexOf("&") > 0))
            return new HashMap<>() {{
                put(query.split("=")[0], query.split("=")[1]);
            }};
        else
            return Arrays.stream(query.split("&"))
                    .collect(Collectors.toMap(v -> v.split("=")[0], v -> v.split("=")[1]));
    }

    /**
     * @param queryParameters to create a URL query
     * @return URL query representation
     */
    public static String buildQuery(Map<String, String> queryParameters) {
        if (CollectionUtils.isEmpty(queryParameters))
            return "";
        else
            return queryParameters.keySet().stream()
                    .map(key -> key + "=" + queryParameters.get(key))
                    .collect(joining("&", "?", ""));
    }

}
