import React, { Component } from 'react'
import { useEffect, useState } from 'react';
import { Button, Row, Table } from 'reactstrap';
import Ekorazred from "./Ekorazred";
import {getAllEkorazredi, getEkorazred, ekorazredEdit, ekorazredRegister, deleteEkorazred} from "../../utils/axios/backendCalls/ekorazredEndpoints";
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
        <div>
            <h2 className="text-center" style={{padding:"1em"}}>Ekorazred List</h2>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addEkorazred}>Add Ekorazred</Button>
            </div>
            <Row>
                <Table striped bordered responsive hover>
                    <thead>
                    <tr>
                        <th>Ekorazred ID</th>
                        <th>Ekorazred Naziv</th>
                    </tr>
                    </thead>
                    <tbody>
                    {ekorazredi.map((ekorazred) => (
                        <tr key={ekorazred.id}>
                            <td>{ekorazred.id}</td>
                            <td>{ekorazred.naziv}</td>
                            <td>
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
            </Row>
        </div>
    );
};


export default EkorazredComponent;