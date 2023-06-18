import {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import {Button, Container, Row, Card, Col, CardBody, Form, FormGroup, CardTitle, Label, Input} from 'reactstrap';
import {naplatnaTockaRegister} from '../../utils/axios/backendCalls/naplatnaTockaEndpoints';
import {getAllDionice} from "../../utils/axios/backendCalls/dionicaEndpoints";
import Select from "react-select";
const CreateNaplatnaTockaComponent = () => {
    const [oznaka, setOznaka] = useState('');
    const [naziv, setNaziv] = useState('');
    const [stacionaza, setStacionaza] = useState('');
    const [geografskaDuzina, setGeografskaDuzina] = useState('');
    const [geografskaSirina, setGeografskaSirina] = useState('');
    const [usmjerenje, setUsmjerenje] = useState('');
    const [dionica, setDionica] = useState('');
    const [dionice, setDionice] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        getAllDionice().then((res) => {
            setDionice(res.listaDionica);
        });
    }, []);

    const handleDionicaChange = (selectedOption) => {
        setDionica(selectedOption);
    }

    const saveNaplatnaTocka = (e) => {
        e.preventDefault();
        const dionicaId = dionica.value == null ?  '' : dionica.value;
        const naplatnaTocka = { oznaka, naziv, stacionaza, geografskaDuzina, geografskaSirina, usmjerenje, dionicaId };
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
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '500px'}}>
                        <CardBody>
                            <CardTitle>
                                <h3>Add Naplatna tocka</h3>
                            </CardTitle>
                            <Form>
                                <FormGroup style={{ padding: '1em' }}>
                                    <Label>Oznaka:</Label>
                                    <Input name="oznaka" className="form-control" value={oznaka} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                    <Label>Naziv:</Label>
                                    <Input name="naziv" className="form-control" value={naziv} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                    <Label>Stacionaza:</Label>
                                    <Input name="stacionaza" className="form-control" value={stacionaza} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                    <Label>Usmjerenje:</Label>
                                    <Input name="usmjerenje" className="form-control" value={usmjerenje} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                                    Dionica:
                                    <Select
                                        name="dionica"
                                        value={dionica}
                                        onChange={handleDionicaChange}
                                        options={dionice && dionice.length > 0 ? dionice.map((dionica) => ({ value: dionica.dionicaId, label: dionica.oznaka })) : []}
                                    />
                                </FormGroup>
                                <Button color="success" onClick={saveNaplatnaTocka}>
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

export default CreateNaplatnaTockaComponent;
