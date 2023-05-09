import {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import {dionicaRegister, getAllDionice} from '../../utils/axios/backendCalls/dionicaEndpoints';
import "../allCss/create-update.css"
import Select from "react-select";

const CreateDionicaComponent = () => {
    const [smjer, setSmjer] = useState('');
    const [najvecaBrzina, setNajvecaBrzina] = useState('');
    const [brojTraka, setBrojTraka] = useState('');
    const [oznaka, setOznaka] = useState('');
    const [pocetnaStacionaza, setPocetnaStacionaza] = useState('');
    const [zavrsnaStacionaza, setZavrsnaStacionaza] = useState('');
    const [dionice, setDionice] = useState([]);
    const [dionicaPrije, setDionicaPrije] = useState('');
    const [dionicaPoslije, setDionicaPoslije] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        getAllDionice().then((res) => {
            console.log(res.listaDionica);
            setDionice(res.listaDionica);
        });
    }, []);

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

    const saveDionica = (e) => {
        e.preventDefault();
        const dionicaPrijeId = dionicaPrije.value != null ? dionicaPrije.value : null;
        const dionicaPoslijeId = dionicaPoslije.value != null ? dionicaPoslije.value : null;
        const dionica = { smjer, najvecaBrzina, brojTraka, oznaka, pocetnaStacionaza, zavrsnaStacionaza, dionicaPrijeId, dionicaPoslijeId };
        console.log('dionica = ' + JSON.stringify(dionica));

        dionicaRegister(dionica).then(() => {
            navigate('/dionice');
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
            default:
                break;
        }
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
                            <h3>Add Dionica</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Smjer:</label>
                                        <input name="smjer" className="form-control" value={smjer === "" ? null : smjer} onChange={changeHandler}></input>
                                        <label>Najveca brzina:</label>
                                        <input name="najvecaBrzina" className="form-control" value={najvecaBrzina === "" ? null : najvecaBrzina} onChange={changeHandler}></input>
                                        <label>Broj traka:</label>
                                        <input name="brojTraka" className="form-control" value={brojTraka === "" ? null : brojTraka} onChange={changeHandler}></input>
                                        <label>Oznaka:</label>
                                        <input name="oznaka" className="form-control" value={oznaka === "" ? null : oznaka} onChange={changeHandler}></input>
                                        <label>Pocetna stacionaza:</label>
                                        <input name="pocetnaStacionaza" className="form-control" value={pocetnaStacionaza ==="" ? null : pocetnaStacionaza} onChange={changeHandler}></input>
                                        <label>Zavrsna stacionaza:</label>
                                        <input name="zavrsnaStacionaza" className="form-control" value={zavrsnaStacionaza === "" ? null : zavrsnaStacionaza} onChange={changeHandler}></input>
                                        Dionica Prije:
                                        <Select
                                            name="dionicaPrije"
                                            value={dionicaPrije}
                                            onChange={handleDionicaPrijeChange}
                                            options={dionice && dionice.length > 0 ? dionice.map((dionica) => ({ value: dionica.dionicaId, label: dionica.oznaka })) : []}/>
                                        Dionica poslije
                                        <Select
                                            name="dionicaPoslije"
                                            value={dionicaPoslije}
                                            onChange={handleDionicaPoslijeChange}
                                            options={dionice && dionice.length > 0 ? dionice.map((dionica) => ({ value: dionica.dionicaId, label: dionica.oznaka })) : []}/>
                                    </FormGroup>
                                    <Button color="success" onClick={saveDionica}>
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

export default CreateDionicaComponent;
