import React from 'react'
import { useEffect, useState } from 'react';
import {Button, Container, Row, Table} from 'reactstrap';
import {getAllNaplatneTocke} from "../../utils/axios/backendCalls/naplatnaTockaEndpoints";
import {deleteNaplatnaTocka} from "../../utils/axios/backendCalls/naplatnaTockaEndpoints";
import { useNavigate } from "react-router-dom";

const NaplatnaTockaComponent = () => {

    const [naplatneTocke, setNaplatneTocke] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        getAllNaplatneTocke().then((res) => {
            setNaplatneTocke(res.listaNaplatnihTocki);
        });
    }, []);

    const addNaplatnaTocka = () => {
        navigate('/add-naplatnatocka');
    };

    const editNaplatnaTocka = (id) => {
        navigate(`/update-naplatnatocka/${id}`);
    };

    const naplatnaTockaDelete = (id) => {
        deleteNaplatnaTocka(id).then(res => {
            setNaplatneTocke(prevNaplatneTocke => prevNaplatneTocke.filter(naplatnaTocka => naplatnaTocka.naplatnaTockaId !== id));
        });
    };

    return (
        <Container style={{ display: 'flex', flexDirection: 'column', maxWidth: '1600px'}}>
            <Row style={{ fontSize: '24px', textAlign: 'left'}}>
                <p style={{fontWeight: 'bold'}}>Naplatna Tocka List</p>
            </Row>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addNaplatnaTocka}>Add Naplatna Tocka</Button>
            </div>
            <div>
                <Table striped bordered responsive hover className="my-4" size={"sm"}>
                    <thead>
                    <tr>
                        <th>Naplatna Tocka ID</th>
                        <th>Oznaka</th>
                        <th>Naziv</th>
                        <th>Stacionaza</th>
                        {/*<th>Geografska Duzina</th>*/}
                        {/*<th>Geografska Sirina</th>*/}
                        <th>Usmjerenje</th>
                        <th>Oznaka Dionice</th>
                    </tr>
                    </thead>
                    <tbody>
                    {naplatneTocke.map((naplatnaTocka) => (
                        <tr key={naplatnaTocka.naplatnaTockaId}>
                            <td>{naplatnaTocka.naplatnaTockaId}</td>
                            <td>{naplatnaTocka.oznaka}</td>
                            <td>{naplatnaTocka.naziv}</td>
                            <td>{naplatnaTocka.stacionaza}</td>
                            {/*<td>{naplatnaTocka.geografskaDuzina}</td>*/}
                            {/*<td>{naplatnaTocka.geografskaSirina}</td>*/}
                            <td>{naplatnaTocka.usmjerenje}</td>
                            <td>{naplatnaTocka.dionica.oznaka}</td>
                            <td>
                                <Button onClick={() => editNaplatnaTocka(naplatnaTocka.naplatnaTockaId)} color="primary">
                                    Update
                                </Button>
                                <Button style={{marginLeft: "1em"}} onClick={() => naplatnaTockaDelete(naplatnaTocka.naplatnaTockaId)} color="danger">
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


export default NaplatnaTockaComponent;