import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
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
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Add Ekorazred</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Naziv:</label>
                                        <input name="naziv" className="form-control" value={naziv} onChange={changeHandler}></input>
                                    </FormGroup>
                                    <Button color="success" onClick={saveEkorazred}>
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

export default CreateEkorazredComponent;
