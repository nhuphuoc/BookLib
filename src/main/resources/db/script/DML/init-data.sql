INSERT INTO public.author ("name")
VALUES ('J.K. Rowling'),
       ('George Orwell'),
       ('J.R.R. Tolkien'),
       ('Jane Austen'),
       ('F. Scott Fitzgerald'),
       ('Mark Twain'),
       ('Leo Tolstoy'),
       ('Herman Melville'),
       ('Mary Shelley'),
       ('Charlotte Brontë');
INSERT INTO public.book (genre, publication_date, quantity, title, author_id)
VALUES ('Fantasy', '1997-06-26', 120, 'Harry Potter and the Philosopher''s Stone', 1),
       ('Dystopian', '1949-06-08', 50, '1984', 2),
       ('Fantasy', '1937-09-21', 80, 'The Hobbit', 3),
       ('Romance', '1813-01-28', 70, 'Pride and Prejudice', 4),
       ('Tragedy', '1925-04-10', 60, 'The Great Gatsby', 5),
       ('Adventure', '1876-06-10', 90, 'The Adventures of Tom Sawyer', 6),
       ('Historical', '1869-01-01', 40, 'War and Peace', 7),
       ('Adventure', '1851-10-18', 30, 'Moby-Dick', 8),
       ('Gothic', '1818-01-01', 100, 'Frankenstein', 9);
