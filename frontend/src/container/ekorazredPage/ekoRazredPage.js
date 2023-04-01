import React from "react";
import Ekorazred from "./Ekorazred";
import {getAllEkorazredi} from "../../utils/axios/backendCalls/ekorazredEndpoints";

const EkorazredPage =() => {

    const [allEkorazredi, setAllEkorazredi] = React.useState([]);

    async function fetchAllEkorazredi(){
        try {
            const response = await getAllEkorazredi();
            setAllEkorazredi(response.listaEkoRazreda);
        }
        catch {
            console.log("Error fetching Ekorazredi!");
        }

    }

    React.useEffect(() => {
        fetchAllEkorazredi();
    }, []);

    return (
        <div className=''>
            { allEkorazredi.map(ekorazred => <Ekorazred key = {ekorazred.id} ekorazred = {ekorazred}/>)}
        </div>
    );
}

export default EkorazredPage;