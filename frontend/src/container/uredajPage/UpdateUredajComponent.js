import { useState, useEffect } from 'react';
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getUredaj, uredajEdit} from "../../utils/axios/backendCalls/uredajEndpoints";

const UpdateUredajComponent = () => {
    const [name, setName] = useState('');
    const [kvar, setKvar] = useState('');
    const [razinaPouzdanosti, setRazinaPouzdanosti] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        getUredaj(id).then((res) => {
            setName(res.uredaj.name);
            setKvar(res.uredaj.kvar);
            setRazinaPouzdanosti(res.uredaj.razinaPouzdanosti);
        });
    }, [id]);

    const updateFunction = (e) => {
        e.preventDefault();
        const uredaj = {
            id: id,
            name: name,
            kvar: kvar,
            razinaPouzdanosti: razinaPouzdanosti
        }
        console.log('uredaj = ' + JSON.stringify(uredaj));

        uredajEdit(uredaj).then(() => {
            navigate('/uredaji')
        });
    };

    const changeHandler = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'name':
                setName(value);
                break;
            case 'kvar':
                setKvar(value);
                break;
            case 'razinaPouzdanosti':
                setRazinaPouzdanosti(value);
                break;
            default:
                break;
        }
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
                                        <label>Kvar:</label>
                                        <select name="kvar" className="form-control" value={kvar} onChange={changeHandler}>
                                            <option value={0}>Ne</option>
                                            <option value={1}>Da</option>
                                        </select>
                                        <label>Razina pouzdanosti:</label>
                                        <input name="razinaPouzdanosti" className="form-control" value={razinaPouzdanosti} onChange={changeHandler}></input>
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
