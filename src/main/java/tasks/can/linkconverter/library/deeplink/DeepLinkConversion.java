package tasks.can.linkconverter.library.deeplink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deep_link_conversion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class DeepLinkConversion {

    @Id
    @GeneratedValue
    private long id;

    /**
     * Link to be converted.
     */
    @Column(name = "source_link", nullable = false)
    private String sourceLink;

    /**
     * Deep link of converted source link.
     */
    @Column(name = "deep_link", nullable = false)
    private String deepLink;
}
