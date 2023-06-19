import { useState, useEffect } from 'react';
import {Button, Card, CardBody, CardTitle, Col, Container, Form, FormGroup, Row} from 'reactstrap';
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
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '500px'}}>
                <Col>
                    <CardBody>
                        <CardTitle className={"text-center mb-4"}>
                            <h3>Edit Drzava</h3>
                        </CardTitle>
                        <Form>
                            <FormGroup style={{ padding: '1em' }}>
                                <label>Naziv:</label>
                                <input name="naziv" className="form-control" value={naziv} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></input>
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
        </Container>
    );
};

export default UpdateDrzavaComponent;
