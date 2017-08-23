package org.frekele.elasticsearch.mapping;

import java.io.Serializable;

public interface MappingBuilder extends Serializable {

    public ObjectMapping build(Class... documentClass);

    public ObjectMapping build(boolean pretty, Class... documentClass);
}
