import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Container, Row, Card, Col, CardBody, Form, FormGroup } from 'reactstrap';
import {kategorijaRegister} from "../../utils/axios/backendCalls/kategorijaEndpoints";
import "../allCss/create-update.css"
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
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Add Kategorija</h3>
                            <CardBody>
                                <Form>
                                    <FormGroup style={{ padding: '1em' }}>
                                        <label>Naziv:</label>
                                        <input name="naziv" className="form-control" value={naziv} onChange={changeHandler}></input>
                                    </FormGroup>
                                    <Button color="success" onClick={saveKategorija}>
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

export default CreateKategorijaComponent;
