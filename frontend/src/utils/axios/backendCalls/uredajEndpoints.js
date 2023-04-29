import axios from "../apiClient"

export const getUredaj = async (id) => {
    const response = await axios.get("/spring/uredaj/fetch", {
        params: {
            id: id
        }
    });
    return response.data;
}

export const uredajEdit = async (uredaj) => {
    const response = await axios.put("/spring/uredaj/update", {
        id: uredaj.id,
        name: uredaj.name,
    });
    return response.data;
};

export const uredajRegister = async (uredaj) => {
    const response = await axios.post("/spring/uredaj/register", {
        uredajtype: uredaj.uredajtype,
        name: uredaj.name,
    });
    return response.data;
};

export const deleteUredaj = async (id) => {
    const response = await axios.delete("/spring/uredaj/delete/" + id, {
        params: {
        }
    });
    return response.data;
}

export const getAllUredaji = async () => {
    const response = await axios.get("/spring/uredaj/all");
    return response.data;
}




