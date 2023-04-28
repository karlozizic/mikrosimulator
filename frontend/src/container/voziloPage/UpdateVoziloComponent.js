import { useState, useEffect } from 'react';
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getVozilo} from "../../utils/axios/backendCalls/voziloEndpoints";
import {voziloEdit} from "../../utils/axios/backendCalls/voziloEndpoints";

const UpdateEkorazredComponent = () => {
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
    const { id } = useParams();

    useEffect(() => {
        getVozilo(id).then((res) => {
            res.vozilo.nacinNaplate === null ? setNacinNaplate('') : setNacinNaplate(res.vozilo.nacinNaplate);
            res.vozilo.boja === null ? setBoja('') : setBoja(res.vozilo.boja);
            res.vozilo.brojOsovina === null ? setBrojOsovina('') : setBrojOsovina(res.vozilo.brojOsovina);
            res.vozilo.idENC === null ? setIDEnc('') : setIDEnc(res.vozilo.idENC);
            res.vozilo.registracijskaOznaka === null ? setRegistracijskaOznaka('') : setRegistracijskaOznaka(res.vozilo.registracijskaOznaka);
            res.vozilo.ekoRazred === null ? setEkoRazred('') : setEkoRazred(res.vozilo.ekoRazred);
            res.vozilo.kategorija === null ? setKategorija('') : setKategorija(res.vozilo.kategorija);
            res.vozilo.drzavaRegistracije === null ? setDrzavaRegistracije('') : setDrzavaRegistracije(res.vozilo.drzavaRegistracije);
            res.vozilo.vin === null ? setVin('') : setVin(res.vozilo.vin);

            // setBoja(res.vozilo.nacinNaplate);
            // setBoja(res.vozilo.boja);
            // setBrojOsovina(res.vozilo.brojOsovina);
            // setIDEnc(res.vozilo.idENC);
            // setRegistracijskaOznaka(res.vozilo.registracijskaOznaka);
            // setEkoRazred(res.vozilo.ekoRazred);
            // setKategorija(res.vozilo.kategorija);
            // setDrzavaRegistracije(res.vozilo.drzavaRegistracije);
            // setVin(res.vozilo.vin);
        });
    }, [id]);

    const updateFunction = (e) => {
        e.preventDefault();
        const vozilo = {
            id: id,
            nacinNaplate: nacinNaplate,
            boja: boja,
            brojOsovina: brojOsovina,
            idENC: idENC,
            registracijskaOznaka: registracijskaOznaka,
            ekoRazred: ekoRazred,
            kategorija: kategorija,
            drzavaRegistracije: drzavaRegistracije,
            vin: vin,
        }
        console.log('vozilo = ' + JSON.stringify(vozilo));

        voziloEdit(vozilo).then(() => {
            navigate('/vozila')
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
                            <h3>Update Vozilo</h3>
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
                                    <Button color="success" onClick={updateFunction}>
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

export default UpdateEkorazredComponent;
