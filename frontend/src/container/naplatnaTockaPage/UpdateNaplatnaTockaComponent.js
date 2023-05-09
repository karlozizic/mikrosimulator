import { useState, useEffect } from 'react';
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getNaplatnaTocka} from "../../utils/axios/backendCalls/naplatnaTockaEndpoints";
import {naplatnaTockaEdit} from "../../utils/axios/backendCalls/naplatnaTockaEndpoints";
import "../allCss/create-update.css"
import {getAllDionice} from "../../utils/axios/backendCalls/dionicaEndpoints";
const UpdateNaplatnaTocka = () => {
    const [oznaka, setOznaka] = useState('');
    const [naziv, setNaziv] = useState('');
    const [stacionaza, setStacionaza] = useState('');
    const [geografskaDuzina, setGeografskaDuzina] = useState('');
    const [geografskaSirina, setGeografskaSirina] = useState('');
    const [usmjerenje, setUsmjerenje] = useState('');
    const [dionica, setDionica] = useState('');
    const [dionice, setDionice] = useState([]);

    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        getNaplatnaTocka(id).then((res) => {
            setOznaka(res.naplatnaTocka.oznaka);
            setNaziv(res.naplatnaTocka.naziv);
            setStacionaza(res.naplatnaTocka.stacionaza);
            setGeografskaDuzina(res.naplatnaTocka.geografskaDuzina);
            setGeografskaSirina(res.naplatnaTocka.geografskaSirina);
            setUsmjerenje(res.naplatnaTocka.usmjerenje);
        });
    }, [id]);

    useEffect(() => {
        getAllDionice().then((res) => {
            setDionice(res.listaDionica);
        });
    }, []);

    const handleDionicaChange = (selectedOption) => {
        setDionica(selectedOption);
    }


    const updateFunction = (e) => {
        e.preventDefault();
        const naplatnaTocka   = {
            naplatnaTockaId: id,
            oznaka: oznaka,
            naziv: naziv,
            stacionaza: stacionaza,
            geografskaDuzina: geografskaDuzina,
            geografskaSirina: geografskaSirina,
            usmjerenje: usmjerenje
        }
        console.log('naplatna tocka = ' + JSON.stringify(naplatnaTocka));

        naplatnaTockaEdit(naplatnaTocka).then(() => {
            navigate('/naplatnetocke');
        });
    };

    const changeHandler = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'oznaka':
                setOznaka(value);
                break;
            case 'naziv':
                setNaziv(value);
                break;
            case 'stacionaza':
                setStacionaza(value);
                break;
            case 'geografskaDuzina':
                setGeografskaDuzina(value);
                break;
            case 'geografskaSirina':
                setGeografskaSirina(value);
                break;
            case 'usmjerenje':
                setUsmjerenje(value);
                break;
            default:
                break;
        }
    };

    const cancel = () => {
        navigate('/naplatnetocke');
    };


    return (
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Update Naplatna Tocka</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Oznaka:</label>
                                        <input name="oznaka" className="form-control" value={oznaka} onChange={changeHandler}></input>
                                        <label>Naziv:</label>
                                        <input name="naziv" className="form-control" value={naziv} onChange={changeHandler}></input>
                                        <label>Stacionaza:</label>
                                        <input name="stacionaza" className="form-control" value={stacionaza} onChange={changeHandler}></input>
                                        <label>Geografska duzina:</label>
                                        <input name="geografskaDuzina" className="form-control" value={geografskaDuzina} onChange={changeHandler}></input>
                                        <label>Geografska Sirina:</label>
                                        <input name="geografskaSirina" className="form-control" value={geografskaSirina} onChange={changeHandler}></input>
                                        <label>Usmjerenje:</label>
                                        <input name="usmjerenje" className="form-control" value={usmjerenje} onChange={changeHandler}></input>
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

export default UpdateNaplatnaTocka;
