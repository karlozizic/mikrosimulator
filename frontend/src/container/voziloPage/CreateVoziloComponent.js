import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import {voziloRegister} from "../../utils/axios/backendCalls/voziloEndpoints";

const CreateVoziloComponent = () => {
    const [nacinNaplate, setNacinNaplate] = useState('');
    const [boja, setBoja] = useState('');
    const [brojOsovina, setBrojOsovina] = useState('');
    const [idENC, setIDEnc] = useState('');
    const [registracijskaOznaka, setRegistracijskaOznaka] = useState('');
    const [ekoRazred, setEkoRazred] = useState('');
    const [kategorija, setKategorija] = useState('');
    const [drzavaRegistracije, setDrzavaRegistracije] = useState('');
    const [vin, setVin] = useState('');

    const navigate = useNavigate();

    const saveEkorazred = (e) => {
        e.preventDefault();
        const vozilo = { nacinNaplate, boja, brojOsovina, idENC, registracijskaOznaka, ekoRazred, kategorija, drzavaRegistracije, vin };
        console.log('ekorazred = ' + JSON.stringify(vozilo));

        voziloRegister(vozilo).then(() => {
            navigate('/vozila');
        });
    };

    const changeHandler = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'nacinNaplate':
                setNacinNaplate(value);
                break;
            case 'boja':
                setBoja(value);
                break;
            case 'brojOsovina':
                setBrojOsovina(value);
                break;
            case 'idENC':
                setIDEnc(value);
                break;
            case 'registracijskaOznaka':
                setRegistracijskaOznaka(value);
                break;
            case 'ekoRazred':
                setEkoRazred(value);
                break;
            case 'kategorija':
                setKategorija(value);
                break;
            case 'drzavaRegistracije':
                setDrzavaRegistracije(value);
                break;
            case 'vin':
                setVin(value);
                break;
            default:
                break;
        }
    };

    const cancel = () => {
        navigate('/vozila');
    };

    return (
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Add Vozilo</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Nacin naplate:</label>
                                        <input name="nacinNaplate" className="form-control" value={nacinNaplate} onChange={changeHandler}></input>
                                        <label>Boja:</label>
                                        <input name="boja" className="form-control" value={boja} onChange={changeHandler}></input>
                                        <label>Broj Osovina:</label>
                                        <input name="brojOsovina" className="form-control" value={brojOsovina} onChange={changeHandler}></input>
                                        <label>ID ENC:</label>
                                        <input name="idENC" className="form-control" value={idENC} onChange={changeHandler}></input>
                                        <label>Registracijska oznaka:</label>
                                        <input name="registracijskaOznaka" className="form-control" value={registracijskaOznaka} onChange={changeHandler}></input>
                                        <label>Eko razred:</label>
                                        <input name="ekoRazred" className="form-control" value={ekoRazred} onChange={changeHandler}></input>
                                        <label>Kategorija:</label>
                                        <input name="kategorija" className="form-control" value={kategorija} onChange={changeHandler}></input>
                                        <label>Drzava Registracije:</label>
                                        <input name="drzavaRegistracije" className="form-control" value={drzavaRegistracije} onChange={changeHandler}></input>
                                        <label>VIN:</label>
                                        <input name="vin" className="form-control" value={vin} onChange={changeHandler}></input>
                                    </FormGroup>
                                    <Button color="success" onClick={saveEkorazred}>
                                        Save
                                    </Button>
                                    <Button color="danger" onClick={cancel}>
                                        Cancel
                                    </Button>
                                </Form>
                            </CardBody>
                        </Col>
                    </Card>
                </Row>
            </Container>
        </div>
    );
};

export default CreateVoziloComponent;
