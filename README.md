# iMessage-Web-Clone
A clone of iMessage on the Web.

***NOTE***: This is not an exact replica of iMessage. UI and functinality will be different. The goal of this project is an exercise in full stack development, as well as try to implement how iMessage works under the hood, particularly with security. This should not be treated as an alternative or competitor to iMessage.

## Tech Stack
- Frontend: [VueJS](https://vuejs.org/)
    - Styles: [Sass/Scss](https://sass-lang.com/)
    - UI Component Library: [PrimeVue](https://primevue.org/)
- API: [Spring Boot](https://spring.io/)
    - Language: [Kotlin](https://kotlinlang.org/)
- Database: [MariaDB](https://mariadb.org/)

## Technical Specification
### User identification
- This iMessage Clone identifies users by a username which is a unique constraint, though entries in the table still have an id as a primary key.
- iMessage primarily identifies users through their Apple ID, which is a unique identifier tied to an individual's phone number, email address, and device.
- Although Apple IDs and phone numbers are used for user identificiation, internally, it is possible that Apple likely uses unique user IDs for efficient database operations and scalability. This could be a UUID or another form of unique identifier.

### Key Generation and Device (User) Registration Process
1. Key Pair Generation
    - This iMessage clone generates key pairs on the backend.
    - When a user sets up iMessage, the device generates a pair of cryptographic keys: a public key and a private key. This key pair is generated using a strong cryptographic algorithm (e.g., RSA or Elliptic Curve Cryptography).
2. Storing the Keys
    - This app securely stores both the private and public key in a database and is mapped to a user's username. This is not what I would normally recommend doing, but due to the challenges of securely storing private keys on the client side and having to deal with how a user accesses their private key if they switch browsers, I store them on the server side.
    - In Apple's setup, the private key is stored securely on the user's device in a secure enclave or keychain, ensuring it never leaves the device. The public key is uploaded to Apple's servers.

### Messaging Process
iMessage (and this clone) uses end-to-end encryption. Each device (user) has its own set of encryption keys. When you send a message, it is encrypted on your device and can only be decrypted by the recipient's device.
1. Sending a Message
    - The sender's device (as well as this app) encrypts the message using the recipient's public key.
    - iMessages are then sent to Apple's servers and are from there routed to the recipient's devices using their device IDs.
    - This app sends the message to the backend and stores the message in a database
2. Receiving a Message
    - In iMessage, the recipient's device receives the encrypted message.
    - Because I'm not using cellular technology, this app implements polling, whereby after a fixed length of time, it makes a call to the API to see if there are any new messages.
    - The device (browser for this app) uses its private key to decrypt the message, ensuring that only the intended recipient can read it.