This file is to display the queries used to create the different tables in MariaDB.

Note: In the users table, the first_name and last_name are the displayed names in the application.
The username is the unique identifier of the user in the system.
So, when you want to create a contact with someone, you would search for their username.
Basically, the username is this app's equivalent of a phone number or email being the unique identifier of a device in iMessage.

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    username VARCHAR(50) UNIQUE,
    password_hash VARCHAR(255),
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_keys (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    public_key TEXT,
    private_key TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE user_contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    source_id INT,
    target_id INT,
    FOREIGN KEY (source_id) REFERENCES users(id),
    FOREIGN KEY (target_id) REFERENCES users(id),
    UNIQUE (source_id, target_id)
);

CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT,
    recipient_id INT,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (recipient_id) REFERENCES users(id)
);

CREATE TABLE groups (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE group_members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    group_id INT,
    user_id INT,
    FOREIGN KEY (group_id) REFERENCES groups(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE (group_id, user_id)
);

CREATE TABLE group_messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT,
    group_id INT,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);

CREATE TABLE emojis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text_representation VARCHAR(50),
    unicode VARCHAR(10) UNIQUE
);

CREATE TABLE reactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    message_id INT,
    emoji_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (message_id) REFERENCES messages(id),
    FOREIGN KEY (emoji_id) REFERENCES emojis(id),
    UNIQUE (user_id, message_id, emoji_id)
);

