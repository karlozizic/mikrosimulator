import {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import {Button, Container, Row, Card, Col, CardBody, Form, FormGroup, CardTitle, Label, Input} from 'reactstrap';
import {dionicaRegister, getAllDionice} from '../../utils/axios/backendCalls/dionicaEndpoints';
import Select from "react-select";

const CreateDionicaComponent = () => {
    const [smjer, setSmjer] = useState('');
    const [najvecaBrzina, setNajvecaBrzina] = useState('');
    const [brojTraka, setBrojTraka] = useState('');
    const [oznaka, setOznaka] = useState('');
    const [pocetnaStacionaza, setPocetnaStacionaza] = useState('');
    const [zavrsnaStacionaza, setZavrsnaStacionaza] = useState('');
    const [dionice, setDionice] = useState([]);
    const [oznakaAutoceste, setOznakaAutoceste] = useState('');
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
        const dionica = { smjer, najvecaBrzina, brojTraka, oznaka, pocetnaStacionaza, zavrsnaStacionaza, oznakaAutoceste,dionicaPrijeId, dionicaPoslijeId };
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
            case 'oznakaAutoceste':
                setOznakaAutoceste(value);
                break;
            default:
                break;
        }
    };

    const cancel = () => {
        navigate('/dionice');
    };

    return (
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '800px'}}>
                <CardBody>
                    <CardTitle className="text-center"><h3>Add Dionica</h3></CardTitle>
                    <Form>
                        <FormGroup style={{ padding: '1em'}}>
                            <Row>
                                <Col md={6}>
                                    <Label>Smjer:</Label>
                                    <Input name="smjer" className="form-control" value={smjer === "" ? null : smjer} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                                <Col md={6}>
                                    <Label>Najveca brzina:</Label>
                                    <Input name="najvecaBrzina" className="form-control" value={najvecaBrzina === "" ? null : najvecaBrzina} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                            </Row>
                            <Row>
                                <Col md={6}>
                                    <Label>Broj traka:</Label>
                                    <Input name="brojTraka" className="form-control" value={brojTraka === "" ? null : brojTraka} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                                <Col md={6}>
                                    <Label>Oznaka:</Label>
                                    <Input name="oznaka" className="form-control" value={oznaka === "" ? null : oznaka} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                            </Row>
                            <Row>
                                <Col md={6}>
                                    <Label>Pocetna stacionaza:</Label>
                                    <Input name="pocetnaStacionaza" className="form-control" value={pocetnaStacionaza ==="" ? null : pocetnaStacionaza} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                                <Col md={6}>
                                    <Label>Zavrsna stacionaza:</Label>
                                    <Input name="zavrsnaStacionaza" className="form-control" value={zavrsnaStacionaza === "" ? null : zavrsnaStacionaza} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                            </Row>
                            <Row>
                                <Col md={6}>
                                    <Label>Oznaka autoceste:</Label>
                                    <Input name="oznakaAutoceste" className="form-control" value={oznakaAutoceste === "" ? null : oznakaAutoceste} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                </Col>
                            </Row>
                            <Row>
                                <Col md={6} >
                                    <Label>Dionica Prije:</Label>
                                    <Select
                                        name="dionicaPrije"
                                        value={dionicaPrije}
                                        onChange={handleDionicaPrijeChange}
                                        options={dionice && dionice.length > 0 ? dionice.map((dionica) => ({ value: dionica.dionicaId, label: dionica.oznaka })) : []}
                                    />
                                </Col>
                                <Col md={6} >
                                    <Label>Dionica poslije</Label>
                                    <Select
                                        name="dionicaPoslije"
                                        value={dionicaPoslije}
                                        onChange={handleDionicaPoslijeChange}
                                        options={dionice && dionice.length > 0 ? dionice.map((dionica) => ({ value: dionica.dionicaId, label: dionica.oznaka })) : []}/>
                                </Col>
                            </Row>
                            </FormGroup>
                        <Button color="success" onClick={saveDionica}>
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

export default CreateDionicaComponent;
