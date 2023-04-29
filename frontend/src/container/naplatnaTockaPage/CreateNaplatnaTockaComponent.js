import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import {naplatnaTockaRegister} from '../../utils/axios/backendCalls/naplatnaTockaEndpoints';

const CreateNaplatnaTockaComponent = () => {
    const [oznaka, setOznaka] = useState('');
    const [naziv, setNaziv] = useState('');
    const [stacionaza, setStacionaza] = useState('');
    const [geografskaDuzina, setGeografskaDuzina] = useState('');
    const [geografskaSirina, setGeografskaSirina] = useState('');
    const [usmjerenje, setUsmjerenje] = useState('');

    const navigate = useNavigate();

    const saveNaplatnaTocka = (e) => {
        e.preventDefault();
        const naplatnaTocka = { oznaka, naziv, stacionaza, geografskaDuzina, geografskaSirina, usmjerenje };
        console.log('naplatna tocka = ' + JSON.stringify(naplatnaTocka));

        naplatnaTockaRegister(naplatnaTocka).then(() => {
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
                            <h3>Add Naplatna Tocka</h3>
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
                                        <label>Geografska sirina:</label>
                                        <input name="geografskaSirina" className="form-control" value={geografskaSirina} onChange={changeHandler}></input>
                                        <label>Usmjerenje:</label>
                                        <input name="usmjerenje" className="form-control" value={usmjerenje} onChange={changeHandler}></input>
                                    </FormGroup>
                                    <Button color="success" onClick={saveNaplatnaTocka}>
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

export default CreateNaplatnaTockaComponent;
