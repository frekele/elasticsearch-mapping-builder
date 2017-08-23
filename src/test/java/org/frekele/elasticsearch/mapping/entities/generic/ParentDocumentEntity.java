package org.frekele.elasticsearch.mapping.entities.generic;

import org.frekele.elasticsearch.mapping.annotations.ElasticDocument;
import org.frekele.elasticsearch.mapping.annotations.ElasticLongField;
import org.frekele.elasticsearch.mapping.annotations.values.BoolValue;

@ElasticDocument(
    value = "my_doc_type",
    dynamic = @BoolValue(true),
    includeInAll = @BoolValue(true),
    parent = "my_parent_doc_type",
    //add eager_global_ordinals into _parent
    eagerGlobalOrdinalsParent = @BoolValue(true),
    enabledAll = @BoolValue(true),
    //add store into _all
    storeAll = @BoolValue(true),
    //add required into _routing
    requiredRouting = @BoolValue(true)
)
public class ParentDocumentEntity {

    @ElasticLongField
    private Long id;

    public ParentDocumentEntity() {
    }
}
