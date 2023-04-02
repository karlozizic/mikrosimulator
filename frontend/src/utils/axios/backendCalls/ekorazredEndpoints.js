import axios from "../apiClient"

export const getEkorazred = async (id) => {
    const response = await axios.get("/spring/ekorazred/fetch", {
        params: {
            id: id
        }
    });
    return response.data;
}

export const ekorazredEdit = async (ekorazred) => {
    const response = await axios.put("/spring/ekorazred/update", {
        ekorazred: {
            id: ekorazred.id,
            naziv: ekorazred.naziv,
        }
    });
    return response.data;
};

export const ekorazredRegister = async (ekorazred) => {
    const response = await axios.post("/spring/ekorazred/register", {
        naziv: ekorazred.naziv
    });
    return response.data;
};

export const deleteEkorazred = async (id) => {
    const response = await axios.delete("/spring/ekorazred/delete/" + id, {
        params: {
        }
    });
    return response.data;
}

export const getAllEkorazredi = async () => {
    const response = await axios.get("/spring/ekorazred/all");
    return response.data;
}




