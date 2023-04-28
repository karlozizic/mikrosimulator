import { useState, useEffect } from 'react';
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getDionica} from "../../utils/axios/backendCalls/dionicaEndpoints";
import {dionicaEdit} from "../../utils/axios/backendCalls/dionicaEndpoints";

const UpdateDionicaComponent = () => {

    const [smjer, setSmjer] = useState('');
    const [najvecaBrzina, setNajvecaBrzina] = useState('');
    const [brojTraka, setBrojTraka] = useState('');
    const [oznaka, setOznaka] = useState('');
    const [pocetnaStacionaza, setPocetnaStacionaza] = useState('');
    const [zavrsnaStacionaza, setZavrsnaStacionaza] = useState('');
    const [naplatnaTocka, setNaplatnaTocka] = useState('');
    const [slijediDionica, setSlijediDionica] = useState('');
    const [prethodiDionica, setPrethodiDionica] = useState('');

    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        getDionica(id).then((res) => {
            setSmjer(res.dionica.smjer);
            setNajvecaBrzina(res.dionica.najvecaBrzina);
            setBrojTraka(res.dionica.brojTraka);
            setOznaka(res.dionica.oznaka);
            setPocetnaStacionaza(res.dionica.pocetnaStacionaza);
            setZavrsnaStacionaza(res.dionica.zavrsnaStacionaza);
            setNaplatnaTocka(res.dionica.naplatnaTocka);
            setSlijediDionica(res.dionica.slijediDionica);
            setPrethodiDionica(res.dionica.prethodiDionica);
        });
    }, [id]);

    const updateFunction = (e) => {
        e.preventDefault();
        const dionica = {
            id: id,
            smjer: smjer,
            najvecaBrzina: najvecaBrzina,
            brojTraka: brojTraka,
            oznaka: oznaka,
            pocetnaStacionaza: pocetnaStacionaza,
            zavrsnaStacionaza: zavrsnaStacionaza,
            naplatnaTocka: naplatnaTocka,
            slijediDionica: slijediDionica,
            prethodiDionica: prethodiDionica,
        }
        console.log('dionica = ' + JSON.stringify(dionica));

        dionicaEdit(dionica).then(() => {
            navigate('/dionice')
        });
    };

    const changeHandler = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'smjer':
                setSmjer(value);
                break;
            case 'najvecaBrzina':
                setNajvecaBrzina(value);
                break;
            case 'brojTraka':
                setBrojTraka(value);
                break;
            case 'oznaka':
                setOznaka(value);
                break;
            case 'pocetnaStacionaza':
                setPocetnaStacionaza(value);
                break;
            case 'zavrsnaStacionaza':
                setZavrsnaStacionaza(value);
                break;
            case 'naplatnaTocka':
                setNaplatnaTocka(value);
                break;
            case 'slijediDionica':
                setSlijediDionica(value);
                break;
            case 'prethodiDionica':
                setPrethodiDionica(value);
                break;
            default:
                break;
        }
        console.log(event.target);
    };

    const cancel = () => {
        navigate('/dionice');
    };


    return (
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Update Dionica</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Smjer:</label>
                                        <input name="smjer" className="form-control" value={smjer} onChange={changeHandler}></input>
                                        <label>Najveca brzina:</label>
                                        <input name="najvecaBrzina" className="form-control" value={najvecaBrzina} onChange={changeHandler}></input>
                                        <label>Broj traka:</label>
                                        <input name="brojTraka" className="form-control" value={brojTraka} onChange={changeHandler}></input>
                                        <label>Oznaka:</label>
                                        <input name="oznaka" className="form-control" value={oznaka} onChange={changeHandler}></input>
                                        <label>Pocetna stacionaza:</label>
                                        <input name="pocetnaStacionaza" className="form-control" value={pocetnaStacionaza} onChange={changeHandler}></input>
                                        <label>Zavrsna stacionaza:</label>
                                        <input name="zavrsnaStacionaza" className="form-control" value={zavrsnaStacionaza} onChange={changeHandler}></input>
                                        <label>Naplatna tocka:</label>
                                        <input name="naplatnaTocka" className="form-control" value={naplatnaTocka} onChange={changeHandler}></input>
                                        <label>Slijedi dionica:</label>
                                        <input name="slijediDionica" className="form-control" value={slijediDionica} onChange={changeHandler}></input>
                                        <label>Prethodi dionica:</label>
                                        <input name="prethodiDionica" className="form-control" value={prethodiDionica} onChange={changeHandler}></input>
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

export default UpdateDionicaComponent;
