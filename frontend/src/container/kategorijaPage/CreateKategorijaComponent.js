import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {Button, Container, Row, Card, Col, CardBody, Form, FormGroup, CardTitle} from 'reactstrap';
import {kategorijaRegister} from "../../utils/axios/backendCalls/kategorijaEndpoints";
const CreateKategorijaComponent = () => {
    const [naziv, setNaziv] = useState('');
    const navigate = useNavigate();

    const saveKategorija = (e) => {
        e.preventDefault();
        const kategorija = { naziv };
        console.log('kategorija = ' + JSON.stringify(kategorija));

        kategorijaRegister(kategorija).then(() => {
            navigate('/kategorije');
        });
    };

    const changeHandler = (event) => {
        console.log(event.target);
        setNaziv(event.target.value);
    };

    const cancel = () => {
        navigate('/kategorije');
    };

    return (
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '500px'}}>
                <CardBody>
                    <CardTitle>
                        <h3>Add Kategorija</h3>
                    </CardTitle>
                    <Form>
                        <FormGroup style={{ padding: '1em' }}>
                            <label>Naziv:</label>
                            <input name="naziv" className="form-control" value={naziv} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></input>
                        </FormGroup>
                        <Button color="success" onClick={saveKategorija}>
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

export default CreateKategorijaComponent;
