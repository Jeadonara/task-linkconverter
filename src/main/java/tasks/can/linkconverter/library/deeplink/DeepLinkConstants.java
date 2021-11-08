package tasks.can.linkconverter.library.deeplink;

import tasks.can.linkconverter.library.LinkModel;

final class DeepLinkConstants {

    private DeepLinkConstants() {
    }


    public static String DEEP_LINK_PREFIX = "ty://";
    public static String SEARCH_PAGE_URL_PATH = "/sr";
    public static String PRODUCT_DETAIL_PAGE_URL_PATH_IDENTIFIER = "-p-";
    public static LinkModel DEFAULT_DEEP_LINK = LinkModel.builder().link("ty://?Page=Home").build();
}
