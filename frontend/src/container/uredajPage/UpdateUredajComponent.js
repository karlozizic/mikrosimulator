import { useState, useEffect } from 'react';
import {Button, Card, CardBody, CardTitle, Col, Container, Form, FormGroup, Input, Label, Row} from 'reactstrap';
import { useNavigate, useParams } from 'react-router-dom';
import {getUredaj, uredajEdit} from "../../utils/axios/backendCalls/uredajEndpoints";
import Select from "react-select";

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
        <Container className="d-flex justify-content-center align-items-center">
            <Card style={{width: '500px'}}>
                <CardBody>
                    <CardTitle>
                        <h3 className="text-center">Update uredaj</h3>
                    </CardTitle>
                    <Form>
                        <FormGroup style={{ padding: '1em' }}>
                            <Label>Name:</Label>
                            <Input name="name" className="form-control" value={name} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                            <Label>Kvar:</Label>
                            <select name="kvar" className="form-control" value={kvar} onChange={changeHandler}>
                                <option value={0}>Ne</option>
                                <option value={1}>Da</option>
                            </select>
                            <Label>Razina pouzdanosti:</Label>
                            <Input name="razinaPouzdanosti" className="form-control" value={razinaPouzdanosti} onChange={changeHandler} style={{ width: '80%', margin: '0 auto'}}></Input>
                        </FormGroup>
                        <Button color="success" onClick={updateFunction}>
                            Save
                        </Button>
                        <Button color="danger" onClick={cancel}>
                            Cancel
                        </Button>
                    </Form>
                    </CardBody>
                </Card>
        </Container>
    );
};

export default UpdateUredajComponent;
