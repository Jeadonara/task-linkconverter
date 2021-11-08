package tasks.can.linkconverter.library.deeplink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.can.linkconverter.infrastructure.error.ServiceError;
import tasks.can.linkconverter.infrastructure.utils.URLUtils;
import tasks.can.linkconverter.library.LinkConverter;
import tasks.can.linkconverter.library.LinkModel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static tasks.can.linkconverter.infrastructure.error.ServiceError.*;
import static tasks.can.linkconverter.library.deeplink.DeepLinkConstants.*;

/**
 * Link Converter Implementation for Deep Links.
 */
@Service
public class DeepLinkConverter implements LinkConverter {

    @Autowired
    private DeepLinkConversionDAO deepLinkConversionDAO;

    @Override
    public LinkModel convertLink(LinkModel linkModel) {
        final String link = linkModel.getLink();

        URL url = convertToURL(link);

        String path = url.getPath();

        boolean isProductDetail = path.replaceFirst("/", "").split("/").length == 2
                && path.contains(PRODUCT_DETAIL_PAGE_URL_PATH_IDENTIFIER);

        boolean isSearchPage = path.indexOf(SEARCH_PAGE_URL_PATH) == 0;

        final LinkModel convertedLink;

        if (isProductDetail) {
            convertedLink = convertToProductDetailDeepLink(url);
        } else if (isSearchPage) {
            convertedLink = convertToSearchPageDeepLink(url);
        } else {
            convertedLink = DEFAULT_DEEP_LINK;
        }

        DeepLinkConversion conversion = DeepLinkConversion.builder()
                .sourceLink(link)
                .deepLink(convertedLink.getLink())
                .build();

        deepLinkConversionDAO.save(conversion);

        return convertedLink;

    }

    private URL convertToURL(String link) {
        URL url;
        try {
            url = URLUtils.buildURL(link);
        } catch (MalformedURLException e) {
            throw INVALID_INPUT;
        }
        return url;
    }

    private LinkModel convertToProductDetailDeepLink(URL url) {

        String path = url.getPath();

        String[] productDetails = path.split(PRODUCT_DETAIL_PAGE_URL_PATH_IDENTIFIER);

        if (Boolean.FALSE.equals(productDetails.length == 2))
            throw ServiceError.GENERIC("Content id is missing");

        final String contentId = productDetails[1];

        Map<String, String> queryParameters = URLUtils.getQueryParameters(url);
        final String boutiqueId = queryParameters.get("boutiqueId");
        final String merchantId = queryParameters.get("merchantId");


        Map<String, String> deepLinkQueryParameters = new LinkedHashMap<>();
        deepLinkQueryParameters.put("Page", "Product");
        deepLinkQueryParameters.put("ContentId", contentId);
        Optional.ofNullable(boutiqueId).ifPresent((value) -> deepLinkQueryParameters.put("CampaignId", value));
        Optional.ofNullable(merchantId).ifPresent((value) -> deepLinkQueryParameters.put("MerchantId", value));


        String link = DEEP_LINK_PREFIX + URLUtils.buildQuery(deepLinkQueryParameters);

        return LinkModel.builder().link(link).build();
    }


    private LinkModel convertToSearchPageDeepLink(URL url) {
        Map<String, String> queryParameters = URLUtils.getQueryParameters(url);

        Map<String, String> deepLinkQueryParameters = new LinkedHashMap<>();
        final String searchParameter = queryParameters.get("q");

        deepLinkQueryParameters.put("Page", "Search");
        Optional.ofNullable(searchParameter).ifPresent((value) -> deepLinkQueryParameters.put("Query", value));

        String link = DEEP_LINK_PREFIX + URLUtils.buildQuery(deepLinkQueryParameters);
        return LinkModel.builder().link(link).build();
    }

}
