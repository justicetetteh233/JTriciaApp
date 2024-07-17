CREATE TABLE IF NOT EXISTS school (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    phone TEXT NOT NULL,
    motto TEXT NOT NULL,
    address TEXT NOT NULL,
    location TEXT NOT NULL,
    proprietor INTEGER,
    FOREIGN KEY (proprietor) REFERENCES users(id)
);