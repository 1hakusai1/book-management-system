CREATE TABLE books
(
    id    UUID PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE authors_books
(
    author_id UUID REFERENCES authors (id),
    book_id   UUID REFERENCES books (id),
    PRIMARY KEY (author_id, book_id)
);
