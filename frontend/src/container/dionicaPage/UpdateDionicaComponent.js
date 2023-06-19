import { useState, useEffect } from 'react';
import {Button, Card, CardBody, CardTitle, Col, Container, Form, FormGroup, Input, Label, Row} from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getDionica} from "../../utils/axios/backendCalls/dionicaEndpoints";
import {dionicaEdit} from "../../utils/axios/backendCalls/dionicaEndpoints";
import {getAllDionice} from "../../utils/axios/backendCalls/dionicaEndpoints";
const UpdateDionicaComponent = () => {

    const [smjer, setSmjer] = useState('');
    const [najvecaBrzina, setNajvecaBrzina] = useState('');
    const [brojTraka, setBrojTraka] = useState('');
    const [oznaka, setOznaka] = useState('');
    const [pocetnaStacionaza, setPocetnaStacionaza] = useState('');
    const [zavrsnaStacionaza, setZavrsnaStacionaza] = useState('');
    const [oznakaAutoceste, setOznakaAutoceste] = useState('');
    const [naplatnaTocka, setNaplatnaTocka] = useState('');
    const [dionicaPrije, setDionicaPrije] = useState('');
    const [dionicaPoslije, setDionicaPoslije] = useState('');
    const [dionice, setDionice] = useState([]);

    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        getAllDionice().then((res) => {
            console.log(res.listaDionica);
            setDionice(res.listaDionica);
        });
    }, []);

    useEffect(() => {
        getDionica(id).then((res) => {
            setSmjer(res.dionica.smjer);
            setNajvecaBrzina(res.dionica.najvecaBrzina);
            setBrojTraka(res.dionica.brojTraka);
            setOznaka(res.dionica.oznaka);
            setPocetnaStacionaza(res.dionica.pocetnaStacionaza);
            setZavrsnaStacionaza(res.dionica.zavrsnaStacionaza);
            setOznakaAutoceste(res.dionica.oznakaAutoceste);
            setNaplatnaTocka(res.dionica.naplatnaTocka);
        });
    }, [id]);


    const handleDionicaPrijeChange = (selectedOption) => {
        console.log(selectedOption);
        // const dionica =  {"dionicaId": selectedOption.value};
        setDionicaPrije(selectedOption);
    }

    const handleDionicaPoslijeChange = (selectedOption) => {
        console.log(selectedOption);
        // const dionica =  {"dionicaId": selectedOption.value};
        setDionicaPoslije(selectedOption);
    }


    const updateFunction = (e) => {
        e.preventDefault();
        const dionicaPrijeId = dionicaPrije.value != null ? dionicaPrije.value : null;
        const dionicaPoslijeId = dionicaPoslije.value != null ? dionicaPoslije.value : null;
        const dionica = {
            dionicaId: id,
            smjer: smjer,
            najvecaBrzina: najvecaBrzina,
            brojTraka: brojTraka,
            oznaka: oznaka,
            pocetnaStacionaza: pocetnaStacionaza,
            zavrsnaStacionaza: zavrsnaStacionaza,
            oznakaAutoceste: oznakaAutoceste,
            naplatnaTocka: naplatnaTocka,
            dionicaPrijeId: dionicaPrijeId,
            dionicaPoslijeId: dionicaPoslijeId
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
            case 'oznakaAutoceste':
                setOznakaAutoceste(value);
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
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '800px'}}>
                <CardBody>
                    <CardTitle className="text-center"><h3>Update Dionica</h3></CardTitle>
                    <Form>
                        <FormGroup style={{ padding: '1em' }}>
                            <Row>
                                <Col md={6}>
                                    <Label>Smjer:</Label>
                                    <Input name="smjer" className="form-control" value={smjer} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                                <Col md={6}>
                                    <Label>Najveca brzina:</Label>
                                    <Input name="najvecaBrzina" className="form-control" value={najvecaBrzina} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                            </Row>
                            <Row>
                                <Col md={6}>
                                    <Label>Broj traka:</Label>
                                    <Input name="brojTraka" className="form-control" value={brojTraka} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                                <Col md={6}>
                                    <Label>Oznaka:</Label>
                                    <Input name="oznaka" className="form-control" value={oznaka} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                            </Row>
                            <Row>
                                <Col md={6}>
                                    <Label>Pocetna stacionaza:</Label>
                                    <Input name="pocetnaStacionaza" className="form-control" value={pocetnaStacionaza} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                                <Col md={6}>
                                    <Label>Zavrsna stacionaza:</Label>
                                    <Input name="zavrsnaStacionaza" className="form-control" value={zavrsnaStacionaza} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                            </Row>
                            <Row>
                                <Col md={6}>
                                    <Label>Oznaka autoceste:</Label>
                                <Input name="oznakaAutoceste" className="form-control" value={oznakaAutoceste} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                            </Row>
                        </FormGroup>
                        <Button color="success" onClick={updateFunction}>
                            Save
                        </Button>
                        <Button style={{marginLeft: "1em"}} color="danger" onClick={cancel}>
                            Cancel
                        </Button>
                    </Form>
                </CardBody>
            </Card>
        </Container>
    );
};

export default UpdateDionicaComponent;
