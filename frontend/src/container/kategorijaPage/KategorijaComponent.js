import React from 'react'
import { useEffect, useState } from 'react';
import {Button, Container, Row, Table} from 'reactstrap';
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
        <Container style={{ display: 'flex', flexDirection: 'column', maxWidth: '1000px', alignItems: 'left'}}>
            <Row style={{ fontSize: '24px', textAlign: 'left'}}>
                <p style={{fontWeight: 'bold'}}>Kategorija List</p>
            </Row>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addKategorija}>Add Kategorija</Button>
            </div>
            <div>
                <Table striped bordered responsive hover className="my-4" size={"sm"}>
                    <thead>
                    <tr>
                        <th className="col-4">Kategorija ID</th>
                        <th className="col-4">Kategorija Naziv</th>
                    </tr>
                    </thead>
                    <tbody>
                    {kategorije.map((kategorija) => (
                        <tr key={kategorija.id}>
                            <td className="col-4">{kategorija.id}</td>
                            <td className="col-4">{kategorija.naziv}</td>
                            <td className="col-4">
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
            </div>
        </Container>
    );
};


export default KategorijaComponent;