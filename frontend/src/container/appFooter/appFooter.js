import React from 'react'
import { Container, Row, Col } from 'reactstrap';

const AppFooter = () => {
    return (
        <div>
            <footer className="footer bg-light" >
                <Container fluid >
                    <Row>
                        <Col>
                            <span className="text-muted">Zavrsni rad - 2023</span>
                        </Col>
                    </Row>
                </Container>
            </footer>
        </div>
    )
}

export default AppFooter;