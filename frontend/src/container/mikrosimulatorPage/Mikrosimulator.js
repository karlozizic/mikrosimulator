import React from 'react'
import {Button, Card, CardBody, Col, Container, Form, FormGroup, Row, Table, Input, Label} from 'reactstrap';
import Select from "react-select";
//import css file

const Mikrosimulator = () => {

    return (
        <div>
            <Container>
                <Row>
                    <Card>
                        <Col>
                            <h3>Mikrosimulator</h3>
                            <CardBody>
                                <Label>Broj vozila</Label>
                                <Input></Input>
                                <Label>Vremenski interval</Label>
                                <Label>Od</Label>
                                <Input></Input>
                                <Label>Do</Label>
                                <Input></Input>
                                <Button>Generiraj Vozila</Button>
                                <a href="http://localhost:3333/vehicles/all">
                                    <Button>Pregled Vozila</Button>
                                </a>
                                <a href="http://localhost:4444/generate">
                                    <Button>Generiraj Očitanja</Button>
                                </a>
                                <a href="http://localhost:5555/payments/all">
                                    <Button>Pregled Očitanja</Button>
                                </a>
                                {/*<Form>*/}
                                    {/*<FormGroup style={{ padding: '1em' }}>*/}
                                    {/*    <label>Smjer:</label>*/}
                                    {/*    <input name="smjer" className="form-control" value={smjer === "" ? null : smjer} onChange={changeHandler}></input>*/}
                                    {/*    <label>Najveca brzina:</label>*/}
                                    {/*    <input name="najvecaBrzina" className="form-control" value={najvecaBrzina === "" ? null : najvecaBrzina} onChange={changeHandler}></input>*/}
                                    {/*    <label>Broj traka:</label>*/}
                                    {/*    <input name="brojTraka" className="form-control" value={brojTraka === "" ? null : brojTraka} onChange={changeHandler}></input>*/}
                                    {/*    <label>Oznaka:</label>*/}
                                    {/*    <input name="oznaka" className="form-control" value={oznaka === "" ? null : oznaka} onChange={changeHandler}></input>*/}
                                    {/*    <label>Pocetna stacionaza:</label>*/}
                                    {/*    <input name="pocetnaStacionaza" className="form-control" value={pocetnaStacionaza ==="" ? null : pocetnaStacionaza} onChange={changeHandler}></input>*/}
                                    {/*    <label>Zavrsna stacionaza:</label>*/}
                                    {/*    <input name="zavrsnaStacionaza" className="form-control" value={zavrsnaStacionaza === "" ? null : zavrsnaStacionaza} onChange={changeHandler}></input>*/}
                                    {/*    <label>Oznaka autoceste:</label>*/}
                                    {/*    <input name="oznakaAutoceste" className="form-control" value={oznakaAutoceste === "" ? null : oznakaAutoceste} onChange={changeHandler}></input>*/}
                                    {/*    Dionica Prije:*/}
                                    {/*    <Select*/}
                                    {/*        name="dionicaPrije"*/}
                                    {/*        value={dionicaPrije}*/}
                                    {/*        onChange={handleDionicaPrijeChange}*/}
                                    {/*        options={dionice && dionice.length > 0 ? dionice.map((dionica) => ({ value: dionica.dionicaId, label: dionica.oznaka })) : []}/>*/}
                                    {/*    Dionica poslije*/}
                                    {/*    <Select*/}
                                    {/*        name="dionicaPoslije"*/}
                                    {/*        value={dionicaPoslije}*/}
                                    {/*        onChange={handleDionicaPoslijeChange}*/}
                                    {/*        options={dionice && dionice.length > 0 ? dionice.map((dionica) => ({ value: dionica.dionicaId, label: dionica.oznaka })) : []}/>*/}
                                    {/*</FormGroup>*/}
                                    {/*<Button color="success" onClick={saveDionica}>*/}
                                    {/*    Save*/}
                                    {/*</Button>*/}
                                    {/*<Button style={{marginLeft: "1em"}} color="danger" onClick={cancel}>*/}
                                    {/*    Cancel*/}
                                    {/*</Button>*/}
                                {/*</Form>*/}
                            </CardBody>
                        </Col>
                    </Card>
                </Row>
            </Container>
        </div>
    );
};
export default Mikrosimulator;