import { useState, useEffect } from 'react';
import {Button, Card, CardBody, CardTitle, Col, Container, Form, FormGroup, Row} from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getKategorija} from "../../utils/axios/backendCalls/kategorijaEndpoints";
import {kategorijaEdit} from "../../utils/axios/backendCalls/kategorijaEndpoints";

const UpdateKategorijaComponent = () => {
    const [naziv, setNaziv] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        getKategorija(id).then((res) => {
            setNaziv(res.kategorija.naziv);
        });
    }, [id]);

    const updateFunction = (e) => {
        e.preventDefault();
        const kategorija = {
            id: id,
            naziv: naziv
        }
        console.log('kategorija = ' + JSON.stringify(kategorija));

        kategorijaEdit(kategorija).then(() => {
            navigate('/kategorije')
        });
    };

    const changeHandler = (event) => {
        setNaziv(event.target.value);
    };

    const cancel = () => {
        navigate('/kategorije');
    };

    return (
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '500px'}}>
                <CardBody>
                    <CardTitle>
                        <h3>Update Kategorija</h3>
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
            </Card>
        </Container>
    );
};

export default UpdateKategorijaComponent;
