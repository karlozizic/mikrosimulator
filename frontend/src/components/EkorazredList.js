import React from "react";
import Ekorazred from "./Ekorazred";

function EkorazredList() {
    // const ekorazredi = [
    //     {id: '1', naziv: 'EURO1'},
    //     {id: '2', naziv: 'EURO2'},
    //     {id: '3', naziv: 'EURO3'}
    // ];
    const [ekorazredi, setEkorazredi] = React.useState([]);

    React.useEffect(() => {
        fetch('/spring/ekorazred/all')
            .then(data => data.json())
            .then(ekorazredi => setEkorazredi(ekorazredi))
        }
        , []);
    console.log(ekorazredi.listaEkoRazreda);
    return (
        <div className='EkorazredList'>
            { ekorazredi.map(ekorazred => <Ekorazred key = {ekorazred.id} ekorazred = {ekorazred}/>)}
        </div>
    );
}

export default EkorazredList;