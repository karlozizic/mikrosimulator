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
    console.log(vozilo);
    const response = await axios.put("/spring/vozilo/update?ekorazredId=" + vozilo.ekoRazredId + "&drzavaId=" + vozilo.drzavaRegistracijeId + "&kategorijaId=" + vozilo.kategorijaId, {
        voziloId: vozilo.voziloId,
        nacinNaplate: vozilo.nacinNaplate,
        boja: vozilo.boja,
        brojOsovina: vozilo.brojOsovina,
        idENC: vozilo.idENC,
        registracijskaOznaka: vozilo.registracijskaOznaka,
        vin: vozilo.vin,
    });
    return response.data;
};

export const voziloRegister = async (vozilo) => {

    const response = await axios.post("/spring/vozilo/register?ekorazredId=" + vozilo.ekoRazredId + "&drzavaId=" + vozilo.drzavaRegistracijeId + "&kategorijaId=" + vozilo.kategorijaId , {
            id: vozilo.voziloId,
            nacinNaplate: vozilo.nacinNaplate,
            boja: vozilo.boja,
            brojOsovina: vozilo.brojOsovina,
            idENC: vozilo.idENC,
            registracijskaOznaka: vozilo.registracijskaOznaka,
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




