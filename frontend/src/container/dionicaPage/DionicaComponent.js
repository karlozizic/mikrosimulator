import React from 'react'
import { useEffect, useState } from 'react';
import {Button, Container, Row, Table} from 'reactstrap';
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
            setDionice(prevDionice => prevDionice.filter(dionica => dionica.dionicaId !== id));
        });
    };

    return (
        <Container style={{ display: 'flex', flexDirection: 'column', maxWidth: '1600px'}}>
            <Row style={{ fontSize: '24px', textAlign: 'left'}}>
                <p style={{fontWeight: 'bold'}}>Dionica List</p>
            </Row>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addDionica}>Add Dionica</Button>
            </div>
            <div>
                <Table striped bordered responsive hover className="my-4" size={"sm"}>
                    <thead>
                    <tr>
                        <th>Dionica ID</th>
                        <th>Smjer</th>
                        <th>Najveca brzina</th>
                        <th>Broj traka</th>
                        <th>Oznaka</th>
                        <th>Pocetna stacionaza</th>
                        <th>Zavrsna stacionaza</th>
                        <th>Oznaka autoceste</th>
                        <th>Prethodi Dionica</th>
                        <th>Slijedi Dionica</th>
                    </tr>
                    </thead>
                    <tbody>
                    {dionice.map((dionica) => (
                        <tr key={dionica.dionicaId}>
                            <td>{dionica.dionicaId}</td>
                            <td>{dionica.smjer}</td>
                            <td>{dionica.najvecaBrzina}</td>
                            <td>{dionica.brojTraka}</td>
                            <td>{dionica.oznaka}</td>
                            <td>{dionica.pocetnaStacionaza}</td>
                            <td>{dionica.zavrsnaStacionaza}</td>
                            <td>{dionica.oznakaAutoceste}</td>
                            <td>{dionica.prethodi === null ? "" : dionica.prethodi.oznaka}</td>
                            <td>{dionica.slijedi === null ? "" : dionica.slijedi.oznaka}</td>
                            <td>
                                <Button onClick={() => editEkorazred(dionica.dionicaId)} color="primary">
                                    Update
                                </Button>
                                <Button style={{marginLeft: "1em"}} onClick={() => dionicaDelete(dionica.dionicaId)} color="danger">
                                    Delete
                                </Button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            </div>
        </Container>
    );
};


export default DionicaComponent;