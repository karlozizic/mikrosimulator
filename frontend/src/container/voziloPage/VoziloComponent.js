import React from 'react'
import { useEffect, useState } from 'react';
import { Button, Row, Table } from 'reactstrap';
import {getAllVozila} from "../../utils/axios/backendCalls/voziloEndpoints";
import {deleteVozilo} from "../../utils/axios/backendCalls/voziloEndpoints";
import { useNavigate } from "react-router-dom";

const VoziloComponent = () => {

    const [vozila, setVozila] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllVozila().then((res) => {
            console.log(res.listaVozila);
            setVozila(res.listaVozila);
        });
    }, []);

    const addVozilo = () => {
        navigate('/add-vozilo');
    };

    const editVozilo = (id) => {
        navigate(`/update-vozilo/${id}`);
    };

    const voziloDelete = (id) => {
        deleteVozilo(id).then(res => {
            setVozila(prevVozila => prevVozila.filter(vozilo => vozilo.voziloId !== id));
        });
    };

    return (
        <div style={{ margin: '0 4em' }}>
            <h2 className="text-center" style={{padding:"1em"}}>Vozilo List</h2>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addVozilo}>Add Vozilo</Button>
            </div>
            <Row className="my-4">
                <Table striped bordered responsive hover>
                    <thead>
                    <tr>
                        <th>Vozilo ID</th>
                        <th>Nacin Naplate</th>
                        <th>Boja</th>
                        <th>Broj Osovina</th>
                        <th>ID ENC</th>
                        <th>Registracijska Oznaka</th>
                        <th>Eko Razred</th>
                        <th>Kategorija</th>
                        <th>Drzava Registracije</th>
                        <th>VIN</th>
                    </tr>
                    </thead>
                    <tbody>
                    {vozila.map((vozilo) => (
                        <tr key={vozilo.voziloId}>
                            <td>{vozilo.voziloId}</td>
                            <td>{vozilo.nacinNaplate}</td>
                            <td>{vozilo.boja}</td>
                            <td>{vozilo.brojOsovina}</td>
                            <td>{vozilo.idENC}</td>
                            <td>{vozilo.registracijskaOznaka}</td>
                            <td>{vozilo.ekoRazred.naziv}</td>
                            <td>{vozilo.kategorija.naziv}</td>
                            <td>{vozilo.drzavaRegistracije.naziv}</td>
                            <td>{vozilo.vin}</td>
                            <td>
                                <Button onClick={() => editVozilo(vozilo.voziloId)} color="primary">
                                    Update
                                </Button>
                                <Button style={{marginLeft: "1em"}} onClick={() => voziloDelete(vozilo.voziloId)} color="danger">
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


export default VoziloComponent;