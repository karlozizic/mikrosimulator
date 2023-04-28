import React from 'react'
import { useEffect, useState } from 'react';
import { Button, Row, Table } from 'reactstrap';
import {getAllKategorije} from "../../utils/axios/backendCalls/kategorijaEndpoints";
import {deleteKategorija} from "../../utils/axios/backendCalls/kategorijaEndpoints";
import { useNavigate } from "react-router-dom";

const KategorijaComponent = () => {

    const [kategorije, setKategorije] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllKategorije().then((res) => {
            setKategorije(res.listaKategorija);
        });
    }, []);

    const addKategorija = () => {
        navigate('/add-kategorija');
    };

    const editKategorija = (id) => {
        navigate(`/update-kategorija/${id}`);
    };

    const kategorijaDelete = (id) => {
        deleteKategorija(id).then(res => {
            setKategorije(prevKategorije => prevKategorije.filter(kategorija => kategorija.id !== id));
        });
    };

    return (
        <div>
            <h2 className="text-center" style={{padding:"1em"}}>Kategorija List</h2>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addKategorija}>Add Kategorija</Button>
            </div>
            <Row>
                <Table striped bordered responsive hover>
                    <thead>
                    <tr>
                        <th>Kategorija ID</th>
                        <th>Kategorija Naziv</th>
                    </tr>
                    </thead>
                    <tbody>
                    {kategorije.map((kategorija) => (
                        <tr key={kategorija.id}>
                            <td>{kategorija.id}</td>
                            <td>{kategorija.naziv}</td>
                            <td>
                                <Button onClick={() => editKategorija(kategorija.id)} color="primary">
                                    Update
                                </Button>
                                <Button style={{marginLeft: "1em"}} onClick={() => kategorijaDelete(kategorija.id)} color="danger">
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


export default KategorijaComponent;