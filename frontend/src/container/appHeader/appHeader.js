import React, {useState} from 'react'
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
    NavItem, NavLink
} from 'reactstrap';

const AppHeader = () =>  {
    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => setIsOpen(!isOpen);

    return (
        <div>
            <Navbar color="light" light expand="md">
                <NavbarBrand href="/">Mikrosimulator naplacivanja cestarine</NavbarBrand>
                <NavbarToggler onClick={toggle} />
                <Collapse isOpen={isOpen} navbar>
                    <Nav className="ml-auto" navbar>
                        {/*<NavItem>*/}
                        {/*    <NavLink href="https://github.com/reactstrap/reactstrap">GitHub</NavLink>*/}
                        {/*</NavItem>*/}
                        <UncontrolledDropdown nav inNavbar>
                            <DropdownToggle nav caret>
                                CRUD
                            </DropdownToggle>
                            <DropdownMenu end>
                                <DropdownItem href="/ekorazredi">
                                   Ekorazred
                                </DropdownItem>
                                <DropdownItem href="/drzave">
                                    Drzava
                                </DropdownItem>
                                <DropdownItem href="/kategorije">
                                    Kategorija
                                </DropdownItem>
                                <DropdownItem href="/dionice">
                                    Dionica
                                </DropdownItem>
                                <DropdownItem href="/naplatnetocke">
                                    Naplatne Tocke
                                </DropdownItem>
                                <DropdownItem href="/uredaji">
                                    Uredaj
                                </DropdownItem>
                                {/*<DropdownItem divider/>*/}
                                {/*<DropdownItem href="/vozila">*/}
                                {/*    Vozilo*/}
                                {/*</DropdownItem>*/}
                            </DropdownMenu>
                        </UncontrolledDropdown>
                        <NavItem>
                            <NavLink href="/simulator">Mikrosimulator</NavLink>
                        </NavItem>
                    </Nav>
                </Collapse>
            </Navbar>
        </div>
    );
}

export default AppHeader;