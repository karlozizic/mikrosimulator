import {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import {Button, Container, Row, Card, Col, CardBody, Form, FormGroup, CardTitle, Label, Input} from 'reactstrap';
import { uredajRegister } from '../../utils/axios/backendCalls/uredajEndpoints';
import {getAllNaplatneTocke} from "../../utils/axios/backendCalls/naplatnaTockaEndpoints";
import Select from "react-select";
const CreateUredajComponent = () => {
    const [name, setName] = useState('');
    const [kvar, setKvar] = useState(0);
    const [razinaPouzdanosti, setRazinaPouzdanosti] = useState('1.0');
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
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '500px'}}>
                <CardBody>
                    <CardTitle>
                        <h3 className="text-center">Dodaj uredaj</h3>
                    </CardTitle>
                    <Form>
                        <FormGroup style={{ padding: '1em' }}>
                            <Label>Name:</Label>
                            <Input name="name" className="form-control" value={name} onChange={changeHandler} />
                            <Label>UredajType:</Label>
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
                            <Label>Kvar:</Label>
                            <select name="kvar" className="form-control" value={kvar} onChange={changeHandler}>
                                <option value={0}>Ne</option>
                                <option value={1}>Da</option>
                            </select>
                            <Label>Razina Pouzdanosti:</Label>
                            <Input name="razinaPouzdanosti" className="form-control" value={razinaPouzdanosti} onChange={changeHandler} />
                        </FormGroup>
                        <Button color="success" onClick={saveUredaj}>
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

export default CreateUredajComponent;
