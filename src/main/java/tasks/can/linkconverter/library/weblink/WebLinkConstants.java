package tasks.can.linkconverter.library.weblink;

import tasks.can.linkconverter.library.LinkModel;

import java.util.HashMap;
import java.util.Map;

final class WebLinkConstants {

    private WebLinkConstants() {
    }

    public static String WEB_LINK_HOST = "https://www.shopme.com";
    public static Map<String,String> PAGE_PATH_MAP = new HashMap<>(){{
        put("Product","/brand/name-p-");
        put("Search","/sr");
    }};
    public static LinkModel DEFAULT_WEB_LINK = LinkModel.builder().link(WEB_LINK_HOST).build();

}
