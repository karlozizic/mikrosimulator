import axios from "../apiClient"

export const getDrzava = async (id) => {
    const response = await axios.get("/spring/drzavaregistracije/fetch", {
        params: {
            id: id
        }
    });
    return response.data;
}

export const drzavaEdit = async (drzava) => {
    const response = await axios.put("/spring/drzavaregistracije/update", {
        id: drzava.id,
        naziv: drzava.naziv,
    });
    return response.data;
};

export const drzavaRegister = async (drzava) => {
    const response = await axios.post("/spring/drzavaregistracije/register", {
        naziv: drzava.naziv
    });
    return response.data;
};

export const deleteDrzava = async (id) => {
    const response = await axios.delete("/spring/drzavaregistracije/delete/" + id, {
        params: {
        }
    });
    return response.data;
}

export const getAllDrzave = async () => {
    const response = await axios.get("/spring/drzavaregistracije/all");
    return response.data;
}




