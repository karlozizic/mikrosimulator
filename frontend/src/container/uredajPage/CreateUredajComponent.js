import {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import { uredajRegister } from '../../utils/axios/backendCalls/uredajEndpoints';
import "../allCss/create-update.css"
import {getAllNaplatneTocke} from "../../utils/axios/backendCalls/naplatnaTockaEndpoints";
import Select from "react-select";
const CreateUredajComponent = () => {
    const [name, setName] = useState('');
    const [kvar, setKvar] = useState(0);
    const [razinaPouzdanosti, setRazinaPouzdanosti] = useState('');
    const [uredajtype, setUredajtype] = useState(1);
    const [naplatnaTocka, setNaplatnaTocka] = useState('');
    const [naplatneTocke, setNaplatneTocke] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        getAllNaplatneTocke().then((res) => {
            setNaplatneTocke(res.listaNaplatnihTocki);
        });
    }, []);

    const handleNaplatnaTockaChange = (selectedOption) => {
        console.log(selectedOption);
        setNaplatnaTocka(selectedOption);
    }

    const saveUredaj = (e) => {
        e.preventDefault();
        const naplatnaTockaId = naplatnaTocka.value != null ? naplatnaTocka.value : null;
        const uredaj = { name, uredajtype, naplatnaTockaId, kvar, razinaPouzdanosti};
        console.log('uredaj = ' + JSON.stringify(uredaj));
        uredajRegister(uredaj).then(() => {
            navigate('/uredaji');
        });
    };

    const changeHandler = (event) => {
        const { name, value } = event.target;
        if (name === 'name') {
            setName(value);
        } else if (name === 'uredajtype') {
            setUredajtype(Number(value));
        } else if (name === 'kvar') {
            setKvar(Number(value));
        } else if(name === 'razinaPouzdanosti') {
            setRazinaPouzdanosti(value);
        }
    };

    const cancel = () => {
        navigate('/uredaji');
    };

    return (
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Add Uredaj</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Name:</label>
                                        <input name="name" className="form-control" value={name} onChange={changeHandler} />
                                        <label>UredajType:</label>
                                        <select name="uredajtype" className="form-control" value={uredajtype}  onChange={changeHandler}>
                                            <option value={1}>Kamera</option>
                                            <option value={2}>Primopredajnik</option>
                                            <option value={3}>Klasifikator</option>
                                        </select>
                                        Naplatna tocka:
                                        <Select
                                            name="naplatnaTocka"
                                            value={naplatnaTocka}
                                            onChange={handleNaplatnaTockaChange}
                                            options={naplatneTocke && naplatneTocke.length > 0 ? naplatneTocke.map((naplatneTocka) => ({ value: naplatneTocka.naplatnaTockaId, label: naplatneTocka.oznaka })) : []}/>
                                        <label>Kvar:</label>
                                        <select name="kvar" className="form-control" value={kvar} onChange={changeHandler}>
                                            <option value={0}>Ne</option>
                                            <option value={1}>Da</option>
                                        </select>
                                        <label>Razina Pouzdanosti:</label>
                                        <input name="razinaPouzdanosti" className="form-control" value={razinaPouzdanosti} onChange={changeHandler}/>
                                    </FormGroup>
                                    <Button color="success" onClick={saveUredaj}>
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

export default CreateUredajComponent;
