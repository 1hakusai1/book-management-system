/*
 * This file is generated by jOOQ.
 */
package com.example.bookmanagementsystem.jooq.public_.enums;


import com.example.bookmanagementsystem.jooq.public_.Public;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public enum PublicationStatus implements EnumType {

    unpublished("unpublished"),

    published("published");

    private final String literal;

    private PublicationStatus(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public String getName() {
        return "publication_status";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal. Returns
     * <code>null</code>, if no such value could be found, see {@link
     * EnumType#lookupLiteral(Class, String)}.
     */
    public static PublicationStatus lookupLiteral(String literal) {
        return EnumType.lookupLiteral(PublicationStatus.class, literal);
    }
}
