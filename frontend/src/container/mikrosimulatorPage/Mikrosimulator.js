import React from 'react'
import {useEffect, useState} from 'react';
import {
    Button,
    Card,
    CardBody,
    Col,
    Container,
    Form,
    FormGroup,
    Row,
    Table,
    Input,
    Label,
    ButtonGroup, ButtonToolbar, CardTitle
} from 'reactstrap';
import Select from "react-select";
import axios from "../../utils/axios/apiClient";
//import css file

const Mikrosimulator = () => {
    const [brojVozila, setBrojVozila] = useState('');
    const [intenzitet, setIntenzitet] = useState('');
    const [pocetnoVrijeme, setPocetnoVrijeme] = useState('');
    const [zavrsnoVrijeme, setZavrsnoVrijeme] = useState('');

    const generateVozila = async () => {
        let request = '/vehicles/generate/' + brojVozila + '?intenzitet='
        request+= intenzitet !== '' ? intenzitet : '-1';
        console.log(pocetnoVrijeme);
        console.log(zavrsnoVrijeme);
        const response = await axios.post(request, {
            pocetnoVrijeme: pocetnoVrijeme,
            zavrsnoVrijeme: zavrsnoVrijeme
            });
    };

    const generateOcitanje = async () => {
        const response = await axios.get('/generate')
    };

    const changeHandler = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'brojVozila':
                setBrojVozila(value);
                break;
            case 'intenzitet':
                setIntenzitet(value);
                break;
            case 'pocetnoVrijeme':
                setPocetnoVrijeme(value);
                break;
            case 'zavrsnoVrijeme':
                setZavrsnoVrijeme(value);
                break;
            default:
                break;
        }
    }

    return (
        <Container className="d-flex justify-content-center align-items-center">
                <Card>
                    <CardBody>
                        <CardTitle>
                            <h3>Mikrosimulator</h3>
                        </CardTitle>
                        <Row sm={10} className="d-flex justify-content-center align-items-center">
                            <Col>
                                <Label>Broj vozila</Label>
                                <Input name={"brojVozila"} onChange={changeHandler} style={{ width: '80%', margin: '0 auto', marginBottom: '2em'}}>
                                </Input>
                            </Col>
                            <Col>
                                <Label>Intenzitet</Label>
                                <Input name={"intenzitet"} onChange={changeHandler} style={{ width: '80%', margin: '0 auto', marginBottom: '2em'}}>
                                </Input>
                            </Col>
                        </Row>
                        {/*<Label>Vremenski interval</Label>*/}
                        <Row sm={10} className="d-flex justify-content-center align-items-center">
                            <Col>
                                <Label>Vrijeme pocetka simulacije</Label>
                                <Input type={"datetime-local"} name={"pocetnoVrijeme"} onChange={changeHandler} style={{ width: '80%', margin: '0 auto', marginBottom: '2em'}}></Input>
                            </Col>
                            <Col>
                                <Label>Vrijeme kraja simulacije</Label>
                                <Input type={"datetime-local"} name={"zavrsnoVrijeme"} onChange={changeHandler} style={{ width: '80%', margin: '0 auto', marginBottom: '2em'}}></Input>
                            </Col>
                        </Row>
                        <Row className={"m-4"}>
                            <Col>
                                <Button color={"primary"} onClick={generateVozila}>Generiraj Vozila</Button>
                                <a href="http://localhost:3333/vehicles/all">
                                    <Button style={{marginLeft: "1.25em"}} color={"primary"}>Pregled Vozila</Button>
                                </a>
                                <Button style={{marginLeft: "1.25em"}} color={"primary"} onClick={generateOcitanje}>Generiraj Očitanja</Button>
                                <a href="http://localhost:5555/payments/all">
                                    <Button style={{marginLeft: "1.25em"}} color={"primary"}>Pregled Očitanja</Button>
                                </a>
                            </Col>
                        </Row>
                    </CardBody>
                </Card>
        </Container>
    );
};
export default Mikrosimulator;