import axios from "../apiClient"

export const getDionica = async (id) => {
    const response = await axios.get("/spring/dionica/fetch", {
        params: {
            id: id
        }
    });
    return response.data;
}

export const dionicaEdit = async (dionica) => {
    const response = await axios.put("/spring/dionica/update", {
        dionicaId: dionica.dionicaId,
        smjer: dionica.smjer,
        najvecaBrzina: dionica.najvecaBrzina,
        brojTraka: dionica.brojTraka,
        oznaka: dionica.oznaka,
        pocetnaStacionaza: dionica.pocetnaStacionaza,
        zavrsnaStacionaza: dionica.zavrsnaStacionaza,
        slijedi: dionica.slijedi,
        prethodi: dionica.prethodi,
    });
    return response.data;
};

export const dionicaRegister = async (dionica) => {
    const response = await axios.post("/spring/dionica/register", {
        smjer: dionica.smjer,
        najvecaBrzina: dionica.najvecaBrzina,
        brojTraka: dionica.brojTraka,
        oznaka: dionica.oznaka,
        pocetnaStacionaza: dionica.pocetnaStacionaza,
        zavrsnaStacionaza: dionica.zavrsnaStacionaza,
        naplatnaTocka: dionica.naplatnaTocka,
        slijedi: dionica.slijedi,
        prethodi: dionica.prethodi,
    });
    return response.data;
};

export const deleteDionica = async (id) => {
    const response = await axios.delete("/spring/dionica/delete/" + id, {
        params: {
        }
    });
    return response.data;
}

export const getAllDionice = async () => {
    const response = await axios.get("/spring/dionica/all");
    return response.data;
}




