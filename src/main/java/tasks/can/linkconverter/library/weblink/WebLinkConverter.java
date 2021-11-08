package tasks.can.linkconverter.library.weblink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tasks.can.linkconverter.infrastructure.utils.URLUtils;
import tasks.can.linkconverter.library.LinkConverter;
import tasks.can.linkconverter.library.LinkModel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static tasks.can.linkconverter.infrastructure.error.ServiceError.*;
import static tasks.can.linkconverter.library.weblink.WebLinkConstants.*;

/**
 * Link Converter Implementation for Web Links.
 */
@Service
public class WebLinkConverter implements LinkConverter {

    @Autowired
    private WebLinkConversionDAO conversionDAO;

    @Override
    public LinkModel convertLink(LinkModel linkModel) {
        final String link = linkModel.getLink();

        validateLinkFormat(link);

        URI uri = convertToURI(link);

        Map<String, String> queryParameters = URLUtils.getQueryParameters(uri);

        String pageType = queryParameters.getOrDefault("Page", "");

        boolean isProductPage = pageType.equals("Product");

        boolean isSearchPage = pageType.equals("Search");

        final LinkModel convertedLink;

        if (isProductPage) {
            convertedLink = getPathForProductPage(queryParameters);
        } else if (isSearchPage) {
            convertedLink = getPathForSearchPage(queryParameters);
        } else {
            convertedLink = DEFAULT_WEB_LINK;
        }

        WebLinkConversion conversion = WebLinkConversion.builder()
                .sourceLink(link)
                .webLink(convertedLink.getLink())
                .build();

        conversionDAO.save(conversion);

        return convertedLink;
    }

    private void validateLinkFormat(String link) {
        if (Boolean.FALSE.equals(StringUtils.hasText(link))
                || link.indexOf("ty://") != 0)
            throw INVALID_INPUT;
    }

    private URI convertToURI(String link) {
        URI uri;
        try {
            uri = URLUtils.buildURI(link);
        } catch (URISyntaxException e) {
            throw INVALID_INPUT;
        }
        return uri;
    }

    private LinkModel getPathForProductPage(Map<String, String> queryParameters) {

        Map<String, String> productQueryMap = new LinkedHashMap<>();
        Optional.ofNullable(queryParameters.get("CampaignId"))
                .ifPresent((value) -> productQueryMap.put("boutiqueId", value));
        Optional.ofNullable(queryParameters.get("MerchantId"))
                .ifPresent((value) -> productQueryMap.put("merchantId", value));

        String query = URLUtils.buildQuery(productQueryMap);

        String path = PAGE_PATH_MAP.get("Product") + queryParameters.get("ContentId");

        String link = WEB_LINK_HOST + path + query;

        return new LinkModel(link);
    }

    private LinkModel getPathForSearchPage(Map<String, String> queryParameters) {
        Map<String, String> searchQueryMap = new LinkedHashMap<>();

        Optional.ofNullable(queryParameters.get("Query"))
                .ifPresent((value) -> searchQueryMap.put("q", value));

        String query = URLUtils.buildQuery(searchQueryMap);

        String path = PAGE_PATH_MAP.get("Search");

        String link = WEB_LINK_HOST + path + query;

        return new LinkModel(link);
    }
}
