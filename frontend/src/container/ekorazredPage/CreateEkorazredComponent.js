import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {Button, Container, Row, Card, Col, CardBody, Form, FormGroup, Label, Input, CardTitle} from 'reactstrap';
import { ekorazredRegister } from '../../utils/axios/backendCalls/ekorazredEndpoints';
const CreateEkorazredComponent = () => {
    const [naziv, setNaziv] = useState('');
    const navigate = useNavigate();

    const saveEkorazred = (e) => {
        e.preventDefault();
        const ekorazred = { naziv };
        console.log('ekorazred = ' + JSON.stringify(ekorazred));

        ekorazredRegister(ekorazred).then(() => {
            navigate('/ekorazredi');
        });
    };

    const changeHandler = (event) => {
        console.log(event.target);
        setNaziv(event.target.value);
    };

    const cancel = () => {
        navigate('/ekorazredi');
    };

    return (
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '500px'}}>
                <CardBody>
                    <CardTitle className={"text-center mb-4"}>
                        <h3>Add Ekorazred</h3>
                    </CardTitle>
                    <Form>
                        <FormGroup style={{ padding: '1em' }}>
                            <Label>Naziv:</Label>
                            <Input name="naziv" className="form-control" value={naziv} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                        </FormGroup>
                        <Button color="success" onClick={saveEkorazred}>
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

export default CreateEkorazredComponent;
