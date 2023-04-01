import axios from "../apiClient"

export const getAllEkorazredi = async () => {
    const response = await axios.get("/spring/ekorazred/all");
    return response.data;
}