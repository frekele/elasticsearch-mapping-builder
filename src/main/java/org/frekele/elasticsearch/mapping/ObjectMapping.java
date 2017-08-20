package org.frekele.elasticsearch.mapping;

import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.io.Serializable;

public class ObjectMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    private XContentBuilder mapping;

    public ObjectMapping(XContentBuilder mapping) {
        this.mapping = mapping;
    }

    public XContentBuilder source() throws IOException {
        return this.mapping;
    }

    public String sourceAsString() throws IOException {
        return this.source().string();
    }

}