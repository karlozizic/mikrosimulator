import { useState, useEffect } from 'react';
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getUredaj, uredajEdit} from "../../utils/axios/backendCalls/uredajEndpoints";

const UpdateUredajComponent = () => {
    const [name, setName] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        getUredaj(id).then((res) => {
            setName(res.uredaj.name);
        });
    }, [id]);

    const updateFunction = (e) => {
        e.preventDefault();
        const uredaj = {
            id: id,
            name: name
        }
        console.log('uredaj = ' + JSON.stringify(uredaj));

        uredajEdit(uredaj).then(() => {
            navigate('/uredaji')
        });
    };

    const changeHandler = (event) => {
        setName(event.target.value);
    };

    const cancel = () => {
        navigate('/uredaji');
    };


    return (
        <div style={{ margin: '0 4em' }}>
            <Container>
                <Row className="my-4">
                    <Card>
                        <Col>
                            <h3>Update Uredaj</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Name:</label>
                                        <input name="name" className="form-control" value={name} onChange={changeHandler}></input>
                                    </FormGroup>
                                    <Button color="success" onClick={updateFunction}>
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

export default UpdateUredajComponent;
