/*
 * This file is generated by jOOQ.
 */
package com.example.bookmanagementsystem.jooq.public_;


import com.example.bookmanagementsystem.jooq.public_.tables.Authors;
import com.example.bookmanagementsystem.jooq.public_.tables.AuthorsBooks;
import com.example.bookmanagementsystem.jooq.public_.tables.Books;
import com.example.bookmanagementsystem.jooq.public_.tables.records.AuthorsBooksRecord;
import com.example.bookmanagementsystem.jooq.public_.tables.records.AuthorsRecord;
import com.example.bookmanagementsystem.jooq.public_.tables.records.BooksRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AuthorsRecord> AUTHORS_PKEY = Internal.createUniqueKey(Authors.AUTHORS, DSL.name("authors_pkey"), new TableField[] { Authors.AUTHORS.ID }, true);
    public static final UniqueKey<AuthorsBooksRecord> AUTHORS_BOOKS_PKEY = Internal.createUniqueKey(AuthorsBooks.AUTHORS_BOOKS, DSL.name("authors_books_pkey"), new TableField[] { AuthorsBooks.AUTHORS_BOOKS.AUTHOR_ID, AuthorsBooks.AUTHORS_BOOKS.BOOK_ID }, true);
    public static final UniqueKey<BooksRecord> BOOKS_PKEY = Internal.createUniqueKey(Books.BOOKS, DSL.name("books_pkey"), new TableField[] { Books.BOOKS.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AuthorsBooksRecord, AuthorsRecord> AUTHORS_BOOKS__AUTHORS_BOOKS_AUTHOR_ID_FKEY = Internal.createForeignKey(AuthorsBooks.AUTHORS_BOOKS, DSL.name("authors_books_author_id_fkey"), new TableField[] { AuthorsBooks.AUTHORS_BOOKS.AUTHOR_ID }, Keys.AUTHORS_PKEY, new TableField[] { Authors.AUTHORS.ID }, true);
    public static final ForeignKey<AuthorsBooksRecord, BooksRecord> AUTHORS_BOOKS__AUTHORS_BOOKS_BOOK_ID_FKEY = Internal.createForeignKey(AuthorsBooks.AUTHORS_BOOKS, DSL.name("authors_books_book_id_fkey"), new TableField[] { AuthorsBooks.AUTHORS_BOOKS.BOOK_ID }, Keys.BOOKS_PKEY, new TableField[] { Books.BOOKS.ID }, true);
}
