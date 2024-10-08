/*
 * This file is generated by jOOQ.
 */
package com.example.bookmanagementsystem.jooq.public_.tables;


import com.example.bookmanagementsystem.jooq.public_.Keys;
import com.example.bookmanagementsystem.jooq.public_.Public;
import com.example.bookmanagementsystem.jooq.public_.tables.AuthorsBooks.AuthorsBooksPath;
import com.example.bookmanagementsystem.jooq.public_.tables.Books.BooksPath;
import com.example.bookmanagementsystem.jooq.public_.tables.records.AuthorsRecord;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Authors extends TableImpl<AuthorsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.authors</code>
     */
    public static final Authors AUTHORS = new Authors();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthorsRecord> getRecordType() {
        return AuthorsRecord.class;
    }

    /**
     * The column <code>public.authors.id</code>.
     */
    public final TableField<AuthorsRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.authors.name</code>.
     */
    public final TableField<AuthorsRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>public.authors.birthday</code>.
     */
    public final TableField<AuthorsRecord, LocalDate> BIRTHDAY = createField(DSL.name("birthday"), SQLDataType.LOCALDATE, this, "");

    private Authors(Name alias, Table<AuthorsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Authors(Name alias, Table<AuthorsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.authors</code> table reference
     */
    public Authors(String alias) {
        this(DSL.name(alias), AUTHORS);
    }

    /**
     * Create an aliased <code>public.authors</code> table reference
     */
    public Authors(Name alias) {
        this(alias, AUTHORS);
    }

    /**
     * Create a <code>public.authors</code> table reference
     */
    public Authors() {
        this(DSL.name("authors"), null);
    }

    public <O extends Record> Authors(Table<O> path, ForeignKey<O, AuthorsRecord> childPath, InverseForeignKey<O, AuthorsRecord> parentPath) {
        super(path, childPath, parentPath, AUTHORS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class AuthorsPath extends Authors implements Path<AuthorsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> AuthorsPath(Table<O> path, ForeignKey<O, AuthorsRecord> childPath, InverseForeignKey<O, AuthorsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private AuthorsPath(Name alias, Table<AuthorsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public AuthorsPath as(String alias) {
            return new AuthorsPath(DSL.name(alias), this);
        }

        @Override
        public AuthorsPath as(Name alias) {
            return new AuthorsPath(alias, this);
        }

        @Override
        public AuthorsPath as(Table<?> alias) {
            return new AuthorsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<AuthorsRecord> getPrimaryKey() {
        return Keys.AUTHORS_PKEY;
    }

    private transient AuthorsBooksPath _authorsBooks;

    /**
     * Get the implicit to-many join path to the
     * <code>public.authors_books</code> table
     */
    public AuthorsBooksPath authorsBooks() {
        if (_authorsBooks == null)
            _authorsBooks = new AuthorsBooksPath(this, null, Keys.AUTHORS_BOOKS__AUTHORS_BOOKS_AUTHOR_ID_FKEY.getInverseKey());

        return _authorsBooks;
    }

    /**
     * Get the implicit many-to-many join path to the <code>public.books</code>
     * table
     */
    public BooksPath books() {
        return authorsBooks().books();
    }

    @Override
    public Authors as(String alias) {
        return new Authors(DSL.name(alias), this);
    }

    @Override
    public Authors as(Name alias) {
        return new Authors(alias, this);
    }

    @Override
    public Authors as(Table<?> alias) {
        return new Authors(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Authors rename(String name) {
        return new Authors(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Authors rename(Name name) {
        return new Authors(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Authors rename(Table<?> name) {
        return new Authors(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authors where(Condition condition) {
        return new Authors(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authors where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authors where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authors where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Authors where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Authors where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Authors where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Authors where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authors whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Authors whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
