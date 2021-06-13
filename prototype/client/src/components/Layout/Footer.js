import React from 'react';

import { Navbar, Nav, NavItem } from 'reactstrap';

//import SourceLink from 'components/SourceLink';

const Footer = () => {
  return (
    <Navbar>
      {/* Set footer to center */}
      <Nav className="justify-content-center" style={{ flex: 1}}>
        <NavItem>
          Websters Created on 2021/06/12
        </NavItem>
      </Nav>
    </Navbar>
  );
};

export default Footer;
