import React from 'react'
import { useEffect, useState } from 'react';
import {Button, Container, Row, Table} from 'reactstrap';
import {getAllUredaji} from "../../utils/axios/backendCalls/uredajEndpoints";
import {deleteUredaj} from "../../utils/axios/backendCalls/uredajEndpoints";
import { useNavigate } from "react-router-dom";
import "../allCss/create-update.css"
const UredajComponent = () => {

    const [uredaji, setUredaji] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllUredaji().then((res) => {
            setUredaji(res.listaUredaja);
        });
    }, []);


    const addUredaj = () => {
        navigate('/add-uredaj');
    };

    const editUredaj = (id) => {
        navigate(`/update-uredaj/${id}`);
    };

    const uredajDelete = (id) => {
        deleteUredaj(id).then(res => {
            setUredaji(prevUredaji => prevUredaji.filter(uredaj => uredaj.id !== id));
        });
    };

    return (
        <Container style={{ display: 'flex', flexDirection: 'column', maxWidth: '1600px'}}>
            <Row style={{ fontSize: '24px', textAlign: 'left'}}>
                <p style={{fontWeight: 'bold'}}>Uredaj List</p>
            </Row>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addUredaj}>Add Uredaj</Button>
            </div>
            <div>
                <Table striped bordered responsive hover className="my-4" size={"sm"}>
                    <thead>
                    <tr>
                        <th>Uredaj ID</th>
                        <th>Naziv</th>
                        <th>Type</th>
                        <th>Naplatna Tocka Id</th>
                        <th>Kvar</th>
                        <th>Razina pouzdanosti</th>
                    </tr>
                    </thead>
                    <tbody>
                    {uredaji.map((uredaj) => (
                        <tr key={uredaj.id}>
                            <td>{uredaj.id}</td>
                            <td>{uredaj.name}</td>
                            <td>{uredaj.uredajtype === 1 ? 'Kamera' : uredaj.uredajtype === 2 ? 'Primopredajnik' : 'Klasifikator'}</td>
                            <td>{uredaj.naplatnaTockaId}</td>
                            <td>{uredaj.kvar === 1 ? 'Da' : 'Ne'}</td>
                            <td>{uredaj.razinaPouzdanosti}</td>
                            <td>
                                <Button onClick={() => editUredaj(uredaj.id)} color="primary">
                                    Update
                                </Button>
                                <Button style={{marginLeft: "1em"}} onClick={() => uredajDelete(uredaj.id)} color="danger">
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


export default UredajComponent;