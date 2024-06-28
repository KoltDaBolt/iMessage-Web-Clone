import * as yup from 'yup';

export const newUserSchema = yup.object({
    firstname: yup.string()
        .max(50, "First name can have a max of 50 characters")
        .required("First name is required"),
    lastname: yup.string()
        .max(50, "Last name can have a max of 50 characters")
        .required("Last name is required"),
    username: yup.string()
        .max(50, "Username can have a max of 50 characters")
        .required("Username is required"),
    password: yup.string()
        .min(8, "Password must have a minimum of 8 characters")
        .matches(/[a-z]/, "Password must have at least one lowercase character")
        .matches(/[A-Z]/, "Password must have at least one uppercase character")
        .matches(/[0-9]/, "Password must have at least one numeric character")
        .required("Password is required")
})