import React from 'react'
import { useEffect, useState } from 'react';
import {Button, Row, Table, Container, Col} from 'reactstrap';
import {getAllEkorazredi, deleteEkorazred} from "../../utils/axios/backendCalls/ekorazredEndpoints";
import { useNavigate } from "react-router-dom";
const EkorazredComponent = () => {

    const [ekorazredi, setEkorazredi] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllEkorazredi().then((res) => {
            setEkorazredi(res.listaEkoRazreda);
        });
    }, []);

    const addEkorazred = () => {
        navigate('/add-ekorazred');
    };

    const editEkorazred = (id) => {
        navigate(`/update-ekorazred/${id}`);
    };

    const ekorazredDelete = (id) => {
        deleteEkorazred(id).then(res => {
            setEkorazredi(prevEkorazredi => prevEkorazredi.filter(ekorazred => ekorazred.id !== id));
        });
    };

    return (
        <Container style={{ display: 'flex', flexDirection: 'column', maxWidth: '1000px', alignItems: 'left'}}>
            <Row style={{ fontSize: '24px', textAlign: 'left'}}>
                <p style={{fontWeight: 'bold'}}>Ekorazred List</p>
            </Row>
            <div style={{textAlign: 'left'}}>
                <Button color="primary" onClick={addEkorazred}>Add Ekorazred</Button>
            </div>
            <div>
                <Table striped bordered responsive hover className={"my-4"}>
                    <thead>
                    <tr>
                        <th className="col-4">Ekorazred ID</th>
                        <th className="col-4">Ekorazred Naziv</th>
                        <th className="col-4">Akcije</th>
                    </tr>
                    </thead>
                    <tbody>
                    {ekorazredi.map((ekorazred) => (
                        <tr key={ekorazred.id}>
                            <td className="col-4">{ekorazred.id}</td>
                            <td className="col-4">{ekorazred.naziv}</td>
                            <td className="col-4">
                                <Button onClick={() => editEkorazred(ekorazred.id)} color="primary">
                                    Update
                                </Button>
                                <Button style={{marginLeft: "1em"}} onClick={() => ekorazredDelete(ekorazred.id)} color="danger">
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


export default EkorazredComponent;