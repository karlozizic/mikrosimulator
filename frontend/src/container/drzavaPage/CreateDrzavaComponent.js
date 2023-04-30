import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import {drzavaRegister} from "../../utils/axios/backendCalls/drzavaEndpoints";
import "../allCss/create-update.css"
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
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Add Drzava</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Naziv:</label>
                                        <input name="naziv" className="form-control" value={naziv} onChange={changeHandler}></input>
                                    </FormGroup>
                                    <Button color="success" onClick={saveDrzava}>
                                        Save
                                    </Button>
                                    <Button color="danger" onClick={cancel} style={{marginLeft: "1em"}}>
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

export default CreateDrzavaComponent;
