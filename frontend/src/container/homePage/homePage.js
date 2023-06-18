import React from "react";
import "./homePage.css";
import {Container, Row} from "reactstrap";
const HomePage = () => {
    return(
        <Container>
            <Row className="d-flex justify-content-center align-items-center my-2" style={{ fontSize: '32px' }}>Dobrodosli na home page mikrosimulatora!</Row>
            <Row className="d-flex justify-content-center align-items-center my-4" style={{ fontSize: '20px' }}>Za uređivanje podataka simulacije odaberite CRUD, a za pokretanje simulacije odaberite Mikrosimulator</Row>
        </Container>
    );
}

export default HomePage;