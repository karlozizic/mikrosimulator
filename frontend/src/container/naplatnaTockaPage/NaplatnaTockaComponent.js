import React from 'react'
import { useEffect, useState } from 'react';
import { Button, Row, Table } from 'reactstrap';
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
        <div style={{ margin: '0 4em' }}>
            <h2 className="text-center" style={{padding:"1em"}}>Naplatna Tocka List</h2>
            <div style={{textAlign:'left'}}>
                <Button color="primary" onClick={addNaplatnaTocka}>Add Naplatna Tocka</Button>
            </div>
            <Row className="my-4">
                <Table striped bordered responsive hover>
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
            </Row>
        </div>
    );
};


export default NaplatnaTockaComponent;