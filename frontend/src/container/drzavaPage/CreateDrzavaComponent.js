import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {Button, Container, Row, Card, Col, CardBody, Form, FormGroup, CardTitle} from 'reactstrap';
import {drzavaRegister} from "../../utils/axios/backendCalls/drzavaEndpoints";
const CreateDrzavaComponent = () => {
    const [naziv, setNaziv] = useState('');
    const navigate = useNavigate();

    const saveDrzava = (e) => {
        e.preventDefault();
        const drzava = { naziv };
        console.log('drzava = ' + JSON.stringify(drzava));

        drzavaRegister(drzava).then(() => {
            navigate('/drzave');
        });
    };

    const changeHandler = (event) => {
        console.log(event.target);
        setNaziv(event.target.value);
    };

    const cancel = () => {
        navigate('/drzave');
    };

    return (
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '500px'}}>
                <CardBody>
                    <CardTitle className={"text-center mb-4"}>
                        <h3>Add Drzava</h3>
                    </CardTitle>
                    <Form>
                        <FormGroup style={{ padding: '1em' }}>
                            <label>Naziv:</label>
                            <input name="naziv" className="form-control" value={naziv} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></input>
                        </FormGroup>
                        <Button color="success" onClick={saveDrzava}>
                            Save
                        </Button>
                        <Button color="danger" onClick={cancel} style={{marginLeft: "1em"}}>
                            Cancel
                        </Button>
                    </Form>
                </CardBody>
            </Card>
        </Container>
    );
};

export default CreateDrzavaComponent;
