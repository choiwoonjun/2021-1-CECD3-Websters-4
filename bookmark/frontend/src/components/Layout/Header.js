import Avatar from 'components/Avatar';
import { UserCard } from 'components/Card';
//import Notifications from 'components/Notifications';
import SearchInput from 'components/SearchInput';
//import { notificationsData } from 'demos/header';
import withBadge from 'hocs/withBadge';
import {CardBody} from 'reactstrap';
import React from 'react';
import './style.scss';
import {
  MdClearAll,
  MdExitToApp,
  MdNotificationsActive,
  //MdNotificationsNone,
  MdPersonPin,
  MdSettingsApplications,
} from 'react-icons/md';
import {
  Button,
  ListGroup,
  ListGroupItem,
  // NavbarToggler,
  Nav,
  Navbar,
  NavItem,
  NavLink,
  Popover,
  PopoverBody,
} from 'reactstrap';
import bn from 'utils/bemnames';

const bem = bn.create('header');

const MdNotificationsActiveWithBadge = withBadge({
  size: 'md',
  color: 'primary',
  style: {
    top: -10,
    right: -10,
    display: 'inline-flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
  // children: <small>5</small>,
})(MdNotificationsActive);

class Header extends React.Component {
  state = {
    isOpenNotificationPopover: false,
    isNotificationConfirmed: false,
    isOpenUserCardPopover: false,
  };

  toggleNotificationPopover = () => {
    this.setState({
      isOpenNotificationPopover: !this.state.isOpenNotificationPopover,
    });

    if (!this.state.isNotificationConfirmed) {
      this.setState({ isNotificationConfirmed: true });
    }
  };

  toggleUserCardPopover = () => {
    this.setState({
      isOpenUserCardPopover: !this.state.isOpenUserCardPopover,
    });
  };

  handleSidebarControlButton = event => {
    event.preventDefault();
    event.stopPropagation();

    document.querySelector('.cr-sidebar').classList.toggle('cr-sidebar--open');
  };

  render() {
    const { isNotificationConfirmed } = this.state;

    return (
      <Navbar light expand className={bem.b('bg-white')}>
        <Nav navbar className="mr-2">
          <Button outline onClick={this.handleSidebarControlButton}>
            <MdClearAll size={25} />
          </Button>
        </Nav>
        <Nav navbar>
          {/* Use Search Engine */}
          <SearchInput />
        </Nav>


        <Nav navbar className={bem.e('nav-right')}>
        <CardBody className="logout-button">
        <Button color="primary" width="100">Logout</Button>
        </CardBody>
          <NavItem>
            <NavLink id="Popover2">
              <Avatar
                onClick={this.toggleUserCardPopover}
                className="can-click"
              /> </NavLink>
            <Popover
              placement="bottom-end"
              isOpen={this.state.isOpenUserCardPopover}
              toggle={this.toggleUserCardPopover}
              target="Popover2"
              className="p-0 border-0"
              style={{ minWidth: 150 }}
            >
              <PopoverBody className="p-0 border-light">
                <UserCard
                  title="Websters"
                  subtitle="websters@dgu.ac.kr"
                  text="Just got started"
                  className="border-light"
                >
                  <ListGroup flush>
                    <ListGroupItem tag="button" action className="border-light">
                      <MdPersonPin /> Profile
                    </ListGroupItem>
                    <ListGroupItem tag="button" action className="border-light">
                      <MdSettingsApplications /> 설정
                    </ListGroupItem>
                    {/* <ListGroupItem tag="button" action className="border-light">
                      <MdExitToApp /> Signout
                    </ListGroupItem> */}
                  </ListGroup>
                </UserCard>
              </PopoverBody>
            </Popover>
          </NavItem>
        </Nav>
      </Navbar>
    );
  }
}

export default Header;
