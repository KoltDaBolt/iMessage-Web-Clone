export default interface User{
    id: number | null
    first_name: string,
    last_name: string,
    username: string,
    password_hash: string,
    registered_at: string | null
}