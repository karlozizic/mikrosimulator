import axios from "../apiClient"

export const getNaplatnaTocka = async (id) => {
    const response = await axios.get("/spring/naplatnatocka/fetch", {
        params: {
            id: id
        }
    });
    return response.data;
}

export const naplatnaTockaEdit = async (naplatnaTocka) => {
    const response = await axios.put("/spring/naplatnatocka/update?id=" + naplatnaTocka.naplatnaTockaId, {
        naplatnatockaId: naplatnaTocka.naplatnatockaId,
        oznaka: naplatnaTocka.oznaka,
        naziv: naplatnaTocka.naziv,
        stacionaza: naplatnaTocka.stacionaza,
        geografskaDuzina: naplatnaTocka.geografskaDuzina,
        geografskaSirina: naplatnaTocka.geografskaSirina,
        usmjerenje: naplatnaTocka.usmjerenje,
        dionicaId: naplatnaTocka.dionicaId
    });
    return response.data;
};

export const naplatnaTockaRegister = async (naplatnaTocka) => {
    const response = await axios.post("/spring/naplatnatocka/register", {
        oznaka: naplatnaTocka.oznaka,
        naziv: naplatnaTocka.naziv,
        stacionaza: naplatnaTocka.stacionaza,
        geografskaDuzina: naplatnaTocka.geografskaDuzina,
        geografskaSirina: naplatnaTocka.geografskaSirina,
        usmjerenje: naplatnaTocka.usmjerenje,
        dionicaId: naplatnaTocka.dionicaId
    });
    return response.data;
};

export const deleteNaplatnaTocka = async (id) => {
    const response = await axios.delete("/spring/naplatnatocka/delete/" + id, {
        params: {
        }
    });
    return response.data;
}

export const getAllNaplatneTocke = async () => {
    const response = await axios.get("/spring/naplatnatocka/all");
    return response.data;
}




