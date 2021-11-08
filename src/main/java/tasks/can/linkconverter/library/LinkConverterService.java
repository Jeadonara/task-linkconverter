package tasks.can.linkconverter.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tasks.can.linkconverter.library.deeplink.DeepLinkConverter;
import tasks.can.linkconverter.library.weblink.WebLinkConverter;

/**
 * Service responsible of managing link conversion.
 */
@Service
public class LinkConverterService {

    @Autowired
    private DeepLinkConverter deepLinkConverter;

    @Autowired
    private WebLinkConverter webLinkConverter;

    public LinkModel convertToDeepLink(LinkModel link) {
        return deepLinkConverter.convertLink(link);
    }

    public LinkModel convertToWebLink(LinkModel link) {
        return webLinkConverter.convertLink(link);
    }

}
