import React, { Component } from 'react'
import { Button, Row, Table } from 'reactstrap';
import Ekorazred from "./Ekorazred";
import {getAllEkorazredi, getEkorazred, ekorazredEdit, ekorazredRegister, deleteEkorazred} from "../../utils/axios/backendCalls/ekorazredEndpoints";
import { useNavigate } from "react-router-dom";

export default class EkorazredComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            ekorazredi: []
        }
        this.addEkorazred = this.addEkorazred.bind(this);
        this.editEkorazred = this.editEkorazred.bind(this);
        this.ekorazredDelete = this.ekorazredDelete.bind(this);
    }

    componentDidMount(){
        getAllEkorazredi().then((res) => {
            this.setState({
                ekorazredi: res.listaEkoRazreda
            });
        });
    }

    addEkorazred()
    {
        this.props.history.push('/register');
    }

    editEkorazred(id)
    {

        this.props.history.push(`/update/${id}`);
    }

    ekorazredDelete(id)
    {
        deleteEkorazred(id).then(res => {
            this.setState({ekorazredi : this.state.ekorazredi.filter(ekorazred => ekorazred.id !== id)});
        })

    }


    render() {
        return (
            <div>
                <h2 className="text-center" style={{padding:"1em"}}>Ekorazred List</h2>
                <div style={{textAlign:'left'}}>
                    <Button color="primary" onClick={this.addEkorazred}>Add Ekorazred</Button>
                </div>
                <Row>
                    <Table striped bordered responsive hover>
                        <thead>
                        <tr>
                            <th>Ekorazred ID</th>
                            <th>Ekorazred Naziv</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.ekorazredi.map(
                                ekorazred =>
                                    <tr key = {ekorazred.id}>
                                        <td> {ekorazred.id} </td>
                                        <td> {ekorazred.naziv} </td>
                                        <td>
                                            <Button onClick={ ()=> this.editEkorazred(ekorazred.id) } color="primary">Update</Button>
                                            <Button style={{marginLeft: "1em"}} onClick={ ()=> this.ekorazredDelete(ekorazred.id) } color="danger">Delete</Button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </Table>
                </Row>

            </div>
        )
    }

}
