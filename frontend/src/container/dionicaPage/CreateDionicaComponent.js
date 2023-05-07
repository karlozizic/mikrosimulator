import {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import {dionicaRegister, getAllDionice} from '../../utils/axios/backendCalls/dionicaEndpoints';
import "../allCss/create-update.css"
import {getAllNaplatneTocke} from "../../utils/axios/backendCalls/naplatnaTockaEndpoints";
import Select from "react-select";

const CreateDionicaComponent = () => {
    const [smjer, setSmjer] = useState('');
    const [najvecaBrzina, setNajvecaBrzina] = useState('');
    const [brojTraka, setBrojTraka] = useState('');
    const [oznaka, setOznaka] = useState('');
    const [pocetnaStacionaza, setPocetnaStacionaza] = useState('');
    const [zavrsnaStacionaza, setZavrsnaStacionaza] = useState('');
    const [naplatnaTocka, setNaplatnaTocka] = useState();
    const [slijediDionica, setSlijediDionica] = useState('');
    const [prethodiDionica, setPrethodiDionica] = useState('')
    const [dionice, setDionice] = useState([]);
    const [naplatneTocke, setNaplatneTocke] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        getAllDionice().then((res) => {
            setDionice(res.listaDionica);
        });
    }, []);

    useEffect(() => {
        getAllNaplatneTocke().then((res) => {
            setNaplatneTocke(res.listaNaplatnihTocki);
        });
    }, []);

    const handleNaplatnaTockaChange = (selectedOption) => {
        console.log(selectedOption);
        const tocka =  {"naplatnaTockaId": selectedOption.value};
        setNaplatnaTocka(selectedOption);
    };

    const saveDionica = (e) => {
        e.preventDefault();
        const dionica = { smjer, najvecaBrzina, brojTraka, oznaka, pocetnaStacionaza, zavrsnaStacionaza, naplatnaTocka, slijediDionica, prethodiDionica };
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
                                        {/*<label>Naplatna Tocka:</label>*/}
                                        {/*<input name="naplatnaTocka" className="form-control" value={naplatnaTocka === "" ? null : naplatnaTocka} onChange={changeHandler}></input>*/}
                                        Naplatna Tocka:
                                        <Select
                                        name="naplatnaTocka"
                                        value={naplatnaTocka}
                                        onChange={handleNaplatnaTockaChange}
                                        options={naplatneTocke && naplatneTocke.length > 0 ? naplatneTocke.map((naplatnaTocka) => ({ value: naplatnaTocka.naplatnaTockaId, label: naplatnaTocka.oznaka })) : []}                                        />
                                        <label>Sljedi dionica:</label>
                                        <input name="slijediDionica" className="form-control" value={slijediDionica === "" ? null : slijediDionica} onChange={changeHandler}></input>
                                        <label>Prethodi dionica:</label>
                                        <input name="prethodiDionica" className="form-control" value={prethodiDionica === "" ? null : prethodiDionica} onChange={changeHandler}></input>
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
