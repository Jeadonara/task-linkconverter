package tasks.can.linkconverter.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkConverterController {

    @Autowired
    private LinkConverterService linkConverterService;

    /**
     * Deep link conversion API.
     *
     * @param linkModel is the link to be converted
     * @return the link conversion result
     */
    @PostMapping("/convert-deeplink")
    ResponseEntity<LinkModel> convertToDeepLink(@RequestBody LinkModel linkModel) {

        LinkModel output = linkConverterService.convertToDeepLink(linkModel);

        return ResponseEntity.ok(output);
    }

    /**
     * Web link conversion API.
     *
     * @param linkModel is the link to be converted
     * @return the link conversion result
     */
    @PostMapping("/convert-weblink")
    ResponseEntity<LinkModel> convertToWebLink(@RequestBody LinkModel linkModel) {

        LinkModel output = linkConverterService.convertToWebLink(linkModel);

        return ResponseEntity.ok(output);
    }
}
