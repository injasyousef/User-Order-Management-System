import api from "./axios";

export const loginRequest = async (email: string, password: string) => {
    const response = await api.post("/api/v1/auth/authenticate", {
        email,
        password,
    });

    console.log(response.data);

    return response.data;
};
