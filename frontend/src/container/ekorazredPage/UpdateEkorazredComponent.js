import { useState, useEffect } from 'react';
import {Button, Card, CardBody, CardTitle, Col, Container, Form, FormGroup, Input, Label, Row} from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getEkorazred, ekorazredEdit} from "../../utils/axios/backendCalls/ekorazredEndpoints";

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
        <Container className="d-flex justify-content-center align-items-center">
                <Card style={{width: '500px'}}>
                    <CardBody>
                        <CardTitle className={"text-center mb-4"}>
                            <h3>Edit Ekorazred</h3>
                        </CardTitle>
                        <Form>
                            <FormGroup style={{ padding: '1em' }}>
                                <Label>Naziv:</Label>
                                <Input name="naziv" className="form-control" value={naziv} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                            </FormGroup>
                                <Button color="success" onClick={updateFunction}>
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

export default UpdateEkorazredComponent;
