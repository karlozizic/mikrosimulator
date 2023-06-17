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
                            <CardBody>
                                <Label>Broj vozila</Label>
                                <Input name={"brojVozila"} onChange={changeHandler}></Input>
                                <Label>Vremenski interval</Label>
                                <Label>Od</Label>
                                <Input></Input>
                                <Label>Do</Label>
                                <Input></Input>
                                <Button onClick={generateVozila}>Generiraj Vozila</Button>
                                <a href="http://localhost:3333/vehicles/all">
                                    <Button>Pregled Vozila</Button>
                                </a>
                                <div>
                                    <Button onClick={generateOcitanje}>Generiraj Očitanja</Button>
                                </div>
                                <a href="http://localhost:5555/payments/all">
                                    <Button>Pregled Očitanja</Button>
                                </a>
                            </CardBody>
                        </Col>
                    </Card>
                </Row>
            </Container>
        </div>
    );
};
export default Mikrosimulator;