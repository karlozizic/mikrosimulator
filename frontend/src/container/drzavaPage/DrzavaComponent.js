import React from 'react'
import { useEffect, useState } from 'react';
import { Button, Row, Table } from 'reactstrap';
import {getAllDrzave} from "../../utils/axios/backendCalls/drzavaEndpoints";
import {deleteDrzava} from "../../utils/axios/backendCalls/drzavaEndpoints";
import { useNavigate } from "react-router-dom";
import "../allCss/create-update.css"

const DrzavaComponent = () => {

    const [drzave, setDrzave] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllDrzave().then((res) => {
            setDrzave(res.listaDrzavaRegistracije);
        });
    }, []);

    const addDrzava = () => {
        navigate('/add-drzava');
    };

    const editDrzava = (id) => {
        navigate(`/update-drzava/${id}`);
    };

    const drzavaDelete = (id) => {
        deleteDrzava(id).then(res => {
            setDrzave(prevDrzave => prevDrzave.filter(drzava => drzava.id !== id));
        });
    };

    return (
        <div style={{ margin: '0 4em' }}>
            <h2 className="text-center" style={{padding:"1em"}}>Drzava List</h2>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addDrzava}>Add Drzava</Button>
            </div>
            <Row className="my-4">
                <Table striped bordered responsive hover>
                    <thead>
                    <tr>
                        <th>Drzava ID</th>
                        <th>Drzava Naziv</th>
                    </tr>
                    </thead>
                    <tbody>
                    {drzave.map((drzava) => (
                        <tr key={drzava.id}>
                            <td>{drzava.id}</td>
                            <td>{drzava.naziv}</td>
                            <td>
                                <Button onClick={() => editDrzava(drzava.id)} color="primary">
                                    Update
                                </Button>
                                <Button style={{marginLeft: "1em"}} onClick={() => drzavaDelete(drzava.id)} color="danger">
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


export default DrzavaComponent;