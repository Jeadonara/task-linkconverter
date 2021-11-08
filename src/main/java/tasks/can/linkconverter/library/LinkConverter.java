package tasks.can.linkconverter.library;

/**
 *  Interface to be implemented by any type of link conversion.
 */
public interface LinkConverter {

    /**
     * @param linkModel is the link to be converted
     * @return the converted link
     */
    LinkModel convertLink(LinkModel linkModel);

}
