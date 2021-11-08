package tasks.can.linkconverter.library.weblink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tasks.can.linkconverter.infrastructure.error.LinkConverterException;
import tasks.can.linkconverter.library.LinkModel;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tasks.can.linkconverter.library.TestConstants.*;

@RunWith(MockitoJUnitRunner.class)
public class WebLinkConverterTest {

    @Mock
    private WebLinkConversionDAO webLinkConversionDAO;

    @InjectMocks
    private WebLinkConverter webLinkConverter;

    @Test
    public void convertProductDetailDeepLinksToURLsSuccessfully() {
        convertURLsToDeepLinksSuccessfully(ProductDetailDeepLinkURLConversionMap);
    }

    @Test
    public void convertSearchPageDeepLinksToURLsSuccessfully() {
        convertURLsToDeepLinksSuccessfully(SearchPageDeepLinURLkConversionMap);
    }

    @Test
    public void convertOtherDeepLinksToURLsSuccessfully() {
        convertURLsToDeepLinksSuccessfully(OtherDeepLinkURLConversionMap);
    }

    private void convertURLsToDeepLinksSuccessfully(Map<String, String> URLDeepLinkConversionMap) {
        URLDeepLinkConversionMap.forEach((key, value) -> {
            LinkModel input = LinkModel.builder().link(key).build();
            assertThat(webLinkConverter.convertLink(input).getLink()).isEqualTo(value);
            WebLinkConversion conversion = WebLinkConversion.builder().webLink(value).sourceLink(key).build();
            verify(webLinkConversionDAO, times(1)).save(conversion);
        });
    }

    @Test
    public void convertFailsWhenDeepLinkInvalid() {
        LinkModel invalidLink = new LinkModel(InvalidDeepLink);
        assertThatThrownBy(() -> webLinkConverter.convertLink(invalidLink))
                .isInstanceOf(LinkConverterException.class);
    }

}
