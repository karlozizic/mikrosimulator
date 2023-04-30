import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import {voziloRegister} from "../../utils/axios/backendCalls/voziloEndpoints";
import "../allCss/create-update.css"
const CreateVoziloComponent = () => {
    const [nacinNaplate, setNacinNaplate] = useState('');
    const [boja, setBoja] = useState('');
    const [brojOsovina, setBrojOsovina] = useState('');
    const [idENC, setIDEnc] = useState('');
    const [registracijskaOznaka, setRegistracijskaOznaka] = useState('');
    const [ekoRazredId, setEkoRazred] = useState('');
    const [kategorijaId, setKategorija] = useState('');
    const [drzavaRegistracijeId, setDrzavaRegistracije] = useState('');
    const [vin, setVin] = useState('');

    const navigate = useNavigate();

    const saveVozilo = (e) => {
        e.preventDefault();
        const vozilo = { nacinNaplate, boja, brojOsovina, idENC, registracijskaOznaka, ekoRazredId, kategorijaId, drzavaRegistracijeId, vin };
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
            case 'ekoRazredId':
                setEkoRazred(value);
                break;
            case 'kategorijaId':
                setKategorija(value);
                break;
            case 'drzavaRegistracijeId':
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
                                        <label>Eko razred Id:</label>
                                        <input name="ekoRazredId" className="form-control" value={ekoRazredId} onChange={changeHandler}></input>
                                        <label>Kategorija Id:</label>
                                        <input name="kategorijaId" className="form-control" value={kategorijaId} onChange={changeHandler}></input>
                                        <label>Drzava Registracije Id:</label>
                                        <input name="drzavaRegistracijeId" className="form-control" value={drzavaRegistracijeId} onChange={changeHandler}></input>
                                        <label>VIN:</label>
                                        <input name="vin" className="form-control" value={vin} onChange={changeHandler}></input>
                                    </FormGroup>
                                    <Button color="success" onClick={saveVozilo}>
                                        Save
                                    </Button>
                                    <Button style={{marginLeft: "1em"}} color="danger" onClick={cancel}>
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
