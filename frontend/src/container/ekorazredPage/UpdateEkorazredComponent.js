import { useState, useEffect } from 'react';
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getEkorazred, ekorazredEdit} from "../../utils/axios/backendCalls/ekorazredEndpoints";
import ekorazred from "./Ekorazred";

const UpdateEkorazredComponent = () => {
    const [naziv, setNaziv] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        getEkorazred(id).then((res) => {
           setNaziv(res.ekoRazred.naziv);
        });
    }, [id]);

    const updateFunction = (e) => {
        e.preventDefault();
        const ekorazred = {
            id: id,
            naziv: naziv
        }
        console.log('ekorazred = ' + JSON.stringify(ekorazred));

        ekorazredEdit(ekorazred).then(() => {
            navigate('/ekorazredi')
        });
    };

    const changeHandler = (event) => {
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
                            <h3>Update Ekorazred</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Naziv:</label>
                                        <input name="naziv" className="form-control" value={naziv} onChange={changeHandler}></input>
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

export default UpdateEkorazredComponent;
