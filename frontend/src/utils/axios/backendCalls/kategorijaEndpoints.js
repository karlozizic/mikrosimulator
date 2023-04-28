import axios from "../apiClient"

export const getKategorija = async (id) => {
    const response = await axios.get("/spring/kategorija/fetch", {
        params: {
            id: id
        }
    });
    return response.data;
}

export const kategorijaEdit = async (kategorija) => {
    const response = await axios.put("/spring/kategorija/update", {
        id: kategorija.id,
        naziv: kategorija.naziv,
    });
    return response.data;
};

export const kategorijaRegister = async (kategorija) => {
    const response = await axios.post("/spring/kategorija/register", {
        naziv: kategorija.naziv
    });
    return response.data;
};

export const deleteKategorija = async (id) => {
    const response = await axios.delete("/spring/kategorija/delete/" + id, {
        params: {
        }
    });
    return response.data;
}

export const getAllKategorije = async () => {
    const response = await axios.get("/spring/kategorija/all");
    return response.data;
}




