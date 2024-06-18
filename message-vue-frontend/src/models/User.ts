export default interface User{
    username: String,
    firstname: String,
    password: String,
    publicKey: String | null,
    privateKey: String | null
}