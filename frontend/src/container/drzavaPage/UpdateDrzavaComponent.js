import { useState, useEffect } from 'react';
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getDrzava} from "../../utils/axios/backendCalls/drzavaEndpoints";
import {drzavaEdit} from "../../utils/axios/backendCalls/drzavaEndpoints";
import "../allCss/create-update.css"
const UpdateDrzavaComponent = () => {
    const [naziv, setNaziv] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        getDrzava(id).then((res) => {
            setNaziv(res.drzavaRegistracije.naziv);
        });
    }, [id]);

    const updateFunction = (e) => {
        e.preventDefault();
        const drzava = {
            id: id,
            naziv: naziv
        }
        console.log('drzava = ' + JSON.stringify(drzava));

        drzavaEdit(drzava).then(() => {
            navigate('/drzave')
        });
    };

    const changeHandler = (event) => {
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
                            <h3>Update Drzava</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Naziv:</label>
                                        <input name="naziv" className="form-control" value={naziv} onChange={changeHandler}></input>
                                    </FormGroup>
                                    <Button color="success" onClick={updateFunction}>
                                        Save
                                    </Button>
                                    <Button style={{marginLeft: "1em"}} color="danger" onClick={cancel}>
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

export default UpdateDrzavaComponent;
