import React from 'react'
import {useEffect, useState} from 'react';
import {Button, Card, CardBody, Col, Container, Form, FormGroup, Row, Table, Input, Label} from 'reactstrap';
import Select from "react-select";
import axios from "../../utils/axios/apiClient";
//import css file

const Mikrosimulator = () => {
    const [brojVozila, setBrojVozila] = useState('');

    const generateVozila = async () => {
        const response = await axios.get('/vehicles/generate/' + brojVozila)
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
            default:
                break;
        }
    }

    return (
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Mikrosimulator</h3>
                        </Col>
                            <CardBody>
                                <Row>
                                    <Col>
                                        <Label>Broj vozila</Label>
                                        <Input name={"brojVozila"} onChange={changeHandler}>
                                        </Input>
                                    </Col>
                                    <Col>
                                        <Label>Intenzitet</Label>
                                        <Input name={"intenzitet"} onChange={changeHandler}>
                                        </Input>
                                    </Col>
                                </Row>
                                {/*<Label>Vremenski interval</Label>*/}
                                <Row>
                                    <Col>
                                        <Label>Vrijeme pocetka simulacije</Label>
                                        <Input></Input>
                                    </Col>
                                    <Col>
                                        <Label>Vrijeme kraja simulacije</Label>
                                        <Input></Input>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                    <Button onClick={generateVozila}>Generiraj Vozila</Button>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col>
                                        <a href="http://localhost:3333/vehicles/all">
                                            <Button>Pregled Vozila</Button>
                                        </a>
                                        <Button onClick={generateOcitanje}>Generiraj Očitanja</Button>
                                        <a href="http://localhost:5555/payments/all">
                                            <Button>Pregled Očitanja</Button>
                                        </a>
                                    </Col>
                                </Row>
                            </CardBody>

                    </Card>
                </Row>
            </Container>
        </div>
    );
};
export default Mikrosimulator;