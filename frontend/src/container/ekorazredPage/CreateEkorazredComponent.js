import React, { Component } from 'react'
import { Button, Card, CardBody, Col, Container, Form, FormGroup, Row } from 'reactstrap'
import {getAllEkorazredi, getEkorazred, ekorazredEdit, ekorazredRegister, deleteEkorazred} from "../../utils/axios/backendCalls/ekorazredEndpoints";


export default class CreateEkorazredComponent extends Component {
    constructor(props){
        super(props)

        this.state = {
            naziv: ''
        }

        this.changeHandler = this.changeHandler.bind(this);
        this.saveEkorazred = this.saveEkorazred.bind(this);
    }

    saveEkorazred = (e) => {
        e.preventDefault();
        let ekorazred = {naziv: this.state.naziv};
        console.log('employee = '+ JSON.stringify(ekorazred));

        //tu treba pozvati ekorazredRegister iz endpointova!
        // EmployeeService.createEmployee(employee).then(res => {
        //     this.props.history.push('/employees');
        // });

    }

    changeHandler = (event)=>{
        this.setState({ [event.target.name]: event.target.value });
    }

    //u slucaju da je neuspjesna registracija
    cancel()
    {
        this.props.history.push('/ekorazredi')
    }

    render() {
        return (
            <div>
                <Container>
                    <Row>
                        <Card>
                            <Col>
                                <h3>Add Ekorazred</h3>
                                <CardBody>
                                    <Form>
                                        <FormGroup style={{padding:"1em"}}>
                                            <label>Naziv:</label>
                                            <input name="naziv" className='form-control' value={this.state.naziv} onChange={this.changeHandler}></input>
                                        </FormGroup>
                                        <Button color="success" onClick={this.saveEkorazred}>Save</Button>
                                        <Button color="danger"onClick={this.cancel.bind(this)}>Cancel</Button>
                                    </Form>
                                </CardBody>
                            </Col>
                        </Card>
                    </Row>
                </Container>
            </div>
        )
    }
}
