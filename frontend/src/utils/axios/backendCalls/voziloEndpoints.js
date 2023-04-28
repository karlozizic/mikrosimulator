import axios from "../apiClient"

export const getVozilo = async (id) => {
    const response = await axios.get("/spring/vozilo/fetch", {
        params: {
            id: id
        }
    });
    return response.data;
}

export const voziloEdit = async (vozilo) => {
    const response = await axios.put("/spring/vozilo/update", {
        id: vozilo.voziloId,
        nacinNaplate: vozilo.nacinNaplate,
        boja: vozilo.boja,
        brojOsovina: vozilo.brojOsovina,
        idENC: vozilo.idENC,
        registracijaOznaka: vozilo.registracijaOznaka,
        ekoRazred: vozilo.ekoRazred,
        kategorija: vozilo.kategorija,
        drzavaRegistracije: vozilo.drzavaRegistracije,
        vin: vozilo.vin,
    });
    return response.data;
};

export const voziloRegister = async (vozilo) => {
    const response = await axios.post("/spring/vozilo/register", {
        nacinNaplate: vozilo.nacinNaplate,
        boja: vozilo.boja,
        brojOsovina: vozilo.brojOsovina,
        idENC: vozilo.idENC,
        registracijaOznaka: vozilo.registracijaOznaka,
        ekoRazred: vozilo.ekoRazred,
        kategorija: vozilo.kategorija,
        drzavaRegistracije: vozilo.drzavaRegistracije,
        vin: vozilo.vin,
    });
    return response.data;
};

export const deleteVozilo = async (id) => {
    const response = await axios.delete("/spring/vozilo/delete/" + id, {
        params: {
        }
    });
    return response.data;
}

export const getAllVozila = async () => {
    const response = await axios.get("/spring/vozilo/all");
    return response.data;
}




