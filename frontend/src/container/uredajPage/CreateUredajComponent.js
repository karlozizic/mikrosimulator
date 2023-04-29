import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import { uredajRegister } from '../../utils/axios/backendCalls/uredajEndpoints';

const CreateUredajComponent = () => {
    const [name, setName] = useState('');
    const [uredajtype, setUredajtype] = useState(1);

    const navigate = useNavigate();

    const saveUredaj = (e) => {
        e.preventDefault();
        const uredaj = { name, uredajtype };
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
                                        <select name="uredajtype" className="form-control" value={uredajtype} onChange={changeHandler}>
                                            <option value={1}>Kamera</option>
                                            <option value={2}>Primopredajnik</option>
                                            <option value={3}>Klasifikator</option>
                                        </select>
                                    </FormGroup>
                                    <Button color="success" onClick={saveUredaj}>
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

export default CreateUredajComponent;
