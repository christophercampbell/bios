package bios.util;

import java.io.File;

/**
 * Open Tree of Life data parser, newick standard
 * <p/>
 * http://files.opentreeoflife.org/trees/
 */
public class OTLParser {
    final File data;
    final File output;

    public OTLParser(final File data, final File output) {
        this.data = data;
        this.output = output;
    }


    /**
     * Developed from http://yanwong.me/?page_id=1090
     * <p/>
     * First find the _ott number for the taxon you want (e.g. Arthropoda_ott632179)
     */
    public void extractSubTree() {
    }
}
