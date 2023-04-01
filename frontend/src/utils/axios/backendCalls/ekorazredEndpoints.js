import axios from "../apiClient"

export const getAllEkorazredi = async () => {
    const response = await axios.get("/spring/ekorazred/all");
    return response.data;
}
export const ekorazredRegister = async (ekorazred) => {
    const response = await axios.post("/spring/ekorazred/register", {
        naziv: ekorazred.naziv
    });
    return response.data;
};

export const getEkorazred = async (id) => {
    const response = await axios.get("/spring/ekorazred/fetch", {
        params: {
            imeKluba: imeKluba,
            korisnickoImeVlasnika: korisnickoImeVlasnika
        }
    });
    return response.data;
}
