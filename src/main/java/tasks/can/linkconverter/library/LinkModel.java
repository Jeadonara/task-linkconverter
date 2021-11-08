package tasks.can.linkconverter.library;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * Class LinkModel represents an object that has link related attributes.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkModel {

    @NonNull
    protected String link;

}
