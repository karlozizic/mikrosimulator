import React from 'react'
import { useEffect, useState } from 'react';
import { Button, Row, Table } from 'reactstrap';
import {getAllDionice, deleteDionica} from "../../utils/axios/backendCalls/dionicaEndpoints";
import { useNavigate } from "react-router-dom";

const DionicaComponent = () => {

    const [dionice, setDionice] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllDionice().then((res) => {
            setDionice(res.listaDionica);
        });
    }, []);

    const addDionica = () => {
        navigate('/add-dionica');
    };

    const editEkorazred = (id) => {
        navigate(`/update-dionica/${id}`);
    };

    const dionicaDelete = (id) => {
        deleteDionica(id).then(res => {
            setDionice(prevDionice => prevDionice.filter(dionica => dionica.id !== id));
        });
    };

    return (
        <div>
            <h2 className="text-center" style={{padding:"1em"}}>Dionica List</h2>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addDionica}>Add Dionica</Button>
            </div>
            <Row>
                <Table striped bordered responsive hover>
                    <thead>
                    <tr>
                        <th>Dionica ID</th>
                        <th>Dionica smjer</th>
                        <th>Dionica najveca brzina</th>
                        <th>Dionica broj traka</th>
                        <th>Dionica oznaka</th>
                        <th>Dionica pocetna stacionaza</th>
                        <th>Dionica zavrsna stacionaza</th>
                        <th>Dionica naplatna tocka</th>
                        <th>Slijedi Dionica</th>
                        <th>Prethodi Dionica</th>
                    </tr>
                    </thead>
                    <tbody>
                    {dionice.map((dionica) => (
                        <tr key={dionica.id}>
                            <td>{dionica.id}</td>
                            <td>{dionica.smjer}</td>
                            <td>{dionica.najvecaBrzina}</td>
                            <td>{dionica.brojTraka}</td>
                            <td>{dionica.oznaka}</td>
                            <td>{dionica.pocetnaStacionaza}</td>
                            <td>{dionica.zavrsnaStacionaza}</td>
                            <td>{dionica.naplatnaTocka}</td>
                            <td>{dionica.slijedi}</td>
                            <td>{dionica.prethodi}</td>
                            <td>
                                <Button onClick={() => editEkorazred(dionica.id)} color="primary">
                                    Update
                                </Button>
                                <Button style={{marginLeft: "1em"}} onClick={() => dionicaDelete(dionica.id)} color="danger">
                                    Delete
                                </Button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            </Row>
        </div>
    );
};


export default DionicaComponent;