package tasks.can.linkconverter.library.deeplink;

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
public class DeepLinkConverterTest {

    @Mock
    private DeepLinkConversionDAO deepLinkConversionDAO;

    @InjectMocks
    private DeepLinkConverter deepLinkConverter;

    @Test
    public void convertProductDetailURLsToDeepLinksSuccessfully() {
        convertURLsToDeepLinksSuccessfully(ProductDetailURLDeepLinkConversionMap);
    }

    @Test
    public void convertSearchPageURLsToDeepLinksSuccessfully() {
        convertURLsToDeepLinksSuccessfully(SearchPageURLDeepLinkConversionMap);
    }

    @Test
    public void convertOtherURLsToDeepLinksSuccessfully() {
        convertURLsToDeepLinksSuccessfully(OtherURLDeepLinkConversionMap);
    }

    private void convertURLsToDeepLinksSuccessfully(Map<String, String> URLDeepLinkConversionMap) {
        URLDeepLinkConversionMap.forEach((key, value) -> {
            LinkModel input = LinkModel.builder().link(key).build();
            assertThat(deepLinkConverter.convertLink(input).getLink()).isEqualTo(value);
            DeepLinkConversion conversion = DeepLinkConversion.builder().deepLink(value).sourceLink(key).build();
            verify(deepLinkConversionDAO, times(1)).save(conversion);
        });
    }

    @Test
    public void convertFailsWhenURLInvalid() {
        LinkModel invalidLink = new LinkModel(InvalidURL);
        assertThatThrownBy(() -> deepLinkConverter.convertLink(invalidLink))
                .isInstanceOf(LinkConverterException.class);
    }

}
