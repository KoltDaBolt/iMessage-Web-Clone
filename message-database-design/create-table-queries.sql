/*
This file is to display the queries used to create the different tables in MariaDB.

Note: In the user_contacts table, the first_name and last_name are the displayed names in the application.
The username (in the users table) is the unique identifier of the user in the system.
So, when you want to create a contact with someone, you would search for their username.
Basically, the username is this app's equivalent of a phone number or email being the unique identifier of a device in iMessage.
*/

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password_hash VARCHAR(255),
    registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE keys (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50),
    public_key BLOB,
    private_key BLOB,
    FOREIGN KEY (user_id) REFERENCES users(username)
);

CREATE TABLE contacts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    source_id VARCHAR(50),
    target_id VARCHAR(50),
    target_firstname VARCHAR(50),
    target_lastname VARCHAR(50),
    FOREIGN KEY (source_id) REFERENCES users(username),
    FOREIGN KEY (target_id) REFERENCES users(username),
    UNIQUE (source_id, target_id)
);

CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id VARCHAR(50),
    recipient_id VARCHAR(50),
    message TEXT DEFAULT NULL,
    status ENUM('SENT', 'DELIVERED', 'READ') DEFAULT 'SENT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(username),
    FOREIGN KEY (recipient_id) REFERENCES users(username)
);

CREATE TABLE attachments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    message_id INT,
    attachment_type ENUM('IMAGE', 'VIDEO'),
    attachment BLOB,
    FOREIGN KEY (message_id) REFERENCES messages(id)
);

CREATE TABLE groups (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE group_members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    group_id INT,
    user_id VARCHAR(50),
    FOREIGN KEY (group_id) REFERENCES groups(id),
    FOREIGN KEY (user_id) REFERENCES users(username),
    UNIQUE (group_id, user_id)
);

CREATE TABLE group_messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id VARCHAR(50),
    group_id INT,
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(username),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);

CREATE TABLE emojis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text_representation VARCHAR(50),
    unicode VARCHAR(10) UNIQUE
);

CREATE TABLE reactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reactor_id VARCHAR(50),
    message_id INT,
    emoji_id INT,
    FOREIGN KEY (reactor_id) REFERENCES users(username),
    FOREIGN KEY (message_id) REFERENCES messages(id),
    FOREIGN KEY (emoji_id) REFERENCES emojis(id),
    UNIQUE (reactor_id, message_id, emoji_id)
);

