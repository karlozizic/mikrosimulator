import { useState, useEffect } from 'react';
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getDionica} from "../../utils/axios/backendCalls/dionicaEndpoints";
import {dionicaEdit} from "../../utils/axios/backendCalls/dionicaEndpoints";
import "../allCss/create-update.css"
import {getAllDionice} from "../../utils/axios/backendCalls/dionicaEndpoints";
const UpdateDionicaComponent = () => {

    const [smjer, setSmjer] = useState('');
    const [najvecaBrzina, setNajvecaBrzina] = useState('');
    const [brojTraka, setBrojTraka] = useState('');
    const [oznaka, setOznaka] = useState('');
    const [pocetnaStacionaza, setPocetnaStacionaza] = useState('');
    const [zavrsnaStacionaza, setZavrsnaStacionaza] = useState('');
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
                                        </FormGroup>
                                    <Button color="success" onClick={updateFunction}>
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

export default UpdateDionicaComponent;
