package tasks.can.linkconverter.library;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TestConstants {

    private TestConstants() {
    }

    public static final Map<String, String> ProductDetailURLDeepLinkConversionMap = new HashMap<>() {{
        put("https://www.shopme.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064", "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
        put("https://www.shopme.com/casio/erkek-kol-saati-p-1925865", "ty://?Page=Product&ContentId=1925865");
        put("https://www.shopme.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892", "ty://?Page=Product&ContentId=1925865&CampaignId=439892");
        put("https://www.shopme.com/casio/erkek-kol-saati-p-1925865?merchantId=105064", "ty://?Page=Product&ContentId=1925865&MerchantId=105064");
    }};

    public static final Map<String, String> SearchPageURLDeepLinkConversionMap = new HashMap<>() {{
        put("https://www.shopme.com/sr?q=elbise", "ty://?Page=Search&Query=elbise");
        put("https://www.shopme.com/sr?q=ferhanşensoy", "ty://?Page=Search&Query=ferhanşensoy");
        put("https://www.shopme.com/sr?q=%C3%BCt%C3%BC", "ty://?Page=Search&Query=%C3%BCt%C3%BC");
    }};

    public static final Map<String, String> OtherURLDeepLinkConversionMap = new HashMap<>() {{
        put("https://www.shopme.com/Hesabim/#/Siparislerim", "ty://?Page=Home");
        put("https://www.shopme.com/Hesabim/#/Ödemelerim", "ty://?Page=Home");
    }};

    public static final Map<String, String> ProductDetailDeepLinkURLConversionMap = new HashMap<>() {{
        put("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", "https://www.shopme.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");
        put("ty://?Page=Product&ContentId=1925865", "https://www.shopme.com/brand/name-p-1925865");
        put("ty://?Page=Product&ContentId=1925865&CampaignId=439892", "https://www.shopme.com/brand/name-p-1925865?boutiqueId=439892");
        put("ty://?Page=Product&ContentId=1925865&MerchantId=105064", "https://www.shopme.com/brand/name-p-1925865?merchantId=105064");
    }};

    public static final Map<String, String> SearchPageDeepLinURLkConversionMap =
            SearchPageURLDeepLinkConversionMap
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    public static final Map<String, String> OtherDeepLinkURLConversionMap = new HashMap<>() {{
        put("ty://?Page=Favorites", "https://www.shopme.com");
        put("ty://?Page=Orders", "https://www.shopme.com");
    }};

    public static final Map<String, String> URLDeepLinkConversionMap = new HashMap<>() {{
        putAll(ProductDetailURLDeepLinkConversionMap);
        putAll(SearchPageURLDeepLinkConversionMap);
        putAll(OtherURLDeepLinkConversionMap);
    }};

    public static final Map<String, String> DeepLinkURLConversionMap = new HashMap<>() {{
        putAll(ProductDetailDeepLinkURLConversionMap);
        putAll(SearchPageDeepLinURLkConversionMap);
        putAll(OtherDeepLinkURLConversionMap);
    }};

    public static String InvalidURL = "httpssss://www.shopme.com/casio/saat-p-1925865-p";
    public static String InvalidDeepLink = "xtsaty://?Page=Favorites";
}
