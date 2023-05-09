import React from 'react'
import { useEffect, useState } from 'react';
import { Button, Row, Table } from 'reactstrap';
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
        <div style={{ margin: '0 4em' }}>
            <h2 className="text-center" style={{padding:"1em"}}>Uredaj List</h2>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addUredaj}>Add Uredaj</Button>
            </div>
            <Row className="my-4">
                <Table striped bordered responsive hover>
                    <thead>
                    <tr>
                        <th>Uredaj ID</th>
                        <th>Naziv</th>
                        <th>Type</th>
                        <th>Naplatna Tocka Id</th>
                    </tr>
                    </thead>
                    <tbody>
                    {uredaji.map((uredaj) => (
                        <tr key={uredaj.id}>
                            <td>{uredaj.id}</td>
                            <td>{uredaj.name}</td>
                            <td>{uredaj.uredajtype === 1 ? 'Kamera' : uredaj.uredajtype === 2 ? 'Primopredajnik' : 'Klasifikator'}</td>
                            <td>{uredaj.naplatnaTockaId}</td>
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
            </Row>
        </div>
    );
};


export default UredajComponent;