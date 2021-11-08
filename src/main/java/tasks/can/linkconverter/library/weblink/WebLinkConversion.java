package tasks.can.linkconverter.library.weblink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "web_link_conversion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebLinkConversion {

    @Id
    @GeneratedValue
    private long id;

    /**
     * Link to be converted.
     */
    @Column(name = "source_link", nullable = false)
    private String sourceLink;

    /**
     * Web link of converted source link.
     */
    @Column(name = "web_link", nullable = false)
    private String webLink;

}
