import React, { Component } from 'react'
import { Container, Nav, Navbar } from 'reactstrap'

export default class AppHeader extends Component {
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark" style={{padding:"1em"}} >
                        <div><a href="/" className="navbar-brand">Mikrosimulator naplacivanja cestarine</a></div>
                        <div><a href="/ekorazredi" className="navbar-brand">Ekorazredi</a></div>
                    </nav>
                </header>
            </div>
        )
    };
}
