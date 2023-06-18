import React from 'react'
import { useEffect, useState } from 'react';
import {Button, Container, Row, Table} from 'reactstrap';
import {getAllDrzave} from "../../utils/axios/backendCalls/drzavaEndpoints";
import {deleteDrzava} from "../../utils/axios/backendCalls/drzavaEndpoints";
import { useNavigate } from "react-router-dom";

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
        <Container style={{ display: 'flex', flexDirection: 'column', maxWidth: '1000px', alignItems: 'left'}}>
            <Row style={{ fontSize: '24px', textAlign: 'left'}}>
                <p style={{fontWeight: 'bold'}}>Drzava List</p>
            </Row>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addDrzava}>Add Drzava</Button>
            </div>
            <div>
                <Table striped bordered responsive hover className="my-4" size={"sm"}>
                    <thead>
                    <tr>
                        <th className="col-4">Drzava ID</th>
                        <th className="col-4">Drzava Naziv</th>
                    </tr>
                    </thead>
                    <tbody>
                    {drzave.map((drzava) => (
                        <tr key={drzava.id}>
                            <td className="col-4">{drzava.id}</td>
                            <td className="col-4">{drzava.naziv}</td>
                            <td className="col-4">
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
            </div>
        </Container>
    );
};


export default DrzavaComponent;