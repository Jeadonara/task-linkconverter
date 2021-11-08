package tasks.can.linkconverter.library;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tasks.can.linkconverter.library.deeplink.DeepLinkConverter;
import tasks.can.linkconverter.library.weblink.WebLinkConverter;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tasks.can.linkconverter.library.TestConstants.*;

@RunWith(MockitoJUnitRunner.class)
public class LinkConverterServiceTest {

    @Mock
    private DeepLinkConverter deepLinkConverter;

    @Mock
    private WebLinkConverter webLinkConverter;

    @InjectMocks
    private LinkConverterService linkConverterService;


    @Test
    public void convertURLsToDeepLinkSuccessfully() {
        URLDeepLinkConversionMap.forEach((key, value) -> {
            LinkModel input = LinkModel.builder().link(key).build();
            LinkModel output = LinkModel.builder().link(value).build();
            when(deepLinkConverter.convertLink(input)).thenReturn(output);
        });


        URLDeepLinkConversionMap.forEach((key, value) -> {
            LinkModel input = LinkModel.builder().link(key).build();
            assertThat(linkConverterService.convertToDeepLink(input).getLink()).isEqualTo(value);
        });

    }

    @Test
    public void convertDeepLinksToURLSuccessfully() {
        DeepLinkURLConversionMap.forEach((key, value) -> {
            LinkModel input = LinkModel.builder().link(key).build();
            LinkModel output = LinkModel.builder().link(value).build();
            when(webLinkConverter.convertLink(input)).thenReturn(output);
        });


        DeepLinkURLConversionMap.forEach((key, value) -> {
            LinkModel input = LinkModel.builder().link(key).build();
            assertThat(linkConverterService.convertToWebLink(input).getLink()).isEqualTo(value);
        });

    }
}
