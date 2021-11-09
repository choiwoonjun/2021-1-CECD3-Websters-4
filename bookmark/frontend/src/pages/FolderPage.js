// import bg11Image from 'assets/img/bg/background_1920-11.jpg';
// import bg18Image from 'assets/img/bg/background_1920-18.jpg';
// import bg1Image from 'assets/img/bg/background_640-1.jpg';
// import bg3Image from 'assets/img/bg/background_640-3.jpg';
// import user1Image from 'assets/img/users/100_1.jpg';

import { UserCard } from 'components/Card';

import Page from 'components/Page';
import { bgCards, gradientCards, overlayCards } from 'demos/FolderPage';
import { getStackLineChart, stackLineChartOptions } from 'demos/chartjs';
import React, { useState } from 'react';
import {
  FaBeer,
  FaPlus,
  FaColon,
  FaEllipsisV,
  FaEllipsisH,
} from 'react-icons/fa';
import {
  MdAccountCircle,
  MdArrowDropDownCircle,
  MdBorderAll,
  // MdBrush,
  MdChromeReaderMode,
  MdDashboard,
  MdExtension,
  MdGroupWork,
  MdInsertChart,
  MdKeyboardArrowDown,
  // MdNotificationsActive,
  MdPages,
  // MdRadioButtonChecked,
  MdSend,
  //  MdStar,
  MdTextFields,
  MdViewCarousel,
  // MdViewDay,
  MdViewList,
  // MdOutlineSpaceDashboard,
  MdTextSnippet,
  MdWeb,
  MdWidgets,
} from 'react-icons/md';
import FontAwesome from 'react-fontawesome';
import { IconName } from 'react-icons/bs';
// import 'font-awesome/css/font-awesome.min.css';
import { Line } from 'react-chartjs-2';
import {
  Button,
  Card,
  CardBody,
  CardImg,
  CardImgOverlay,
  CardLink,
  CardText,
  CardTitle,
  Col,
  ListGroup,
  ListGroupItem,
  Row,
} from 'reactstrap';
// import React from 'react';
import { Menu, MenuItem, SubMenu, MenuButton } from '@szhsin/react-menu';
import '@szhsin/react-menu/dist/index.css';
import '@szhsin/react-menu/dist/transitions/slide.css';

import './style.scss';

export default function FolderPage() {
  const [display, setDisplay] = useState(false);
  const handleToggleDisplay = () => {
    setDisplay(!display);
  };
  // const Modal = ({ handleClose, show, children }) => {
  //   const showHideClassName = show ? "modal display-block" : "modal display-none";

  //   return (
  //     <div className={showHideClassName}>
  //       <section className="modal-main">
  //         {children}
  //         <button type="button" onClick={handleClose}>
  //           Close
  //         </button>
  //       </section>
  //     </div>
  //   );
  // };
  // const FolderPage = () => {
  return (
    <div className="folder-page">
      <h3>Meeting Folder</h3>
      <div class="navbarr">
        <a href="#home">Import</a>
        <a href="#news">Edit</a>
        <a href="#home">Setting</a>
      </div>
      <div class="folder_page_body">
      <div class="navbarr">
        <table>
          <thead>
            <tr>
              <th width="10%">No</th>
              <th width="40%">File Name</th>
              <th width="20%">Shared By</th>
              <th width="20%">Shared Date</th>
              <th width="20%">Last Edited </th>
              <th width="20%">Last Edited By</th>
              <th width="20%">Created on</th>
              <th width="20%">Content</th>
              <th width="20%">Content Edit</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>컴퓨터공학과2</td>
              <td>홍일동</td>
              <td>2021.10.06</td>
              <td>홍일동</td>
              <td>2021.10.06</td>
              <td>2021.10.06</td>
              <td>
                <Menu menuButton={<MenuButton>View</MenuButton>}>
                  <MenuItem>New File</MenuItem>
                  <SubMenu label="Open">
                    <MenuItem>index.html</MenuItem>
                    <MenuItem>example.js</MenuItem>
                    <SubMenu label="Styles">
                      <MenuItem>about.css</MenuItem>
                      <MenuItem>home.css</MenuItem>
                      <MenuItem>index.css</MenuItem>
                    </SubMenu>
                  </SubMenu>
                  <MenuItem>Save</MenuItem>
                </Menu>
              </td>
              <td>
                <Menu menuButton={<MenuButton>Edit</MenuButton>}>
                  <MenuItem>New File</MenuItem>
                  <SubMenu label="Open">
                    <MenuItem>index.html</MenuItem>
                    <MenuItem>example.js</MenuItem>
                    <SubMenu label="Styles">
                      <MenuItem>about.css</MenuItem>
                      <MenuItem>home.css</MenuItem>
                      <MenuItem>index.css</MenuItem>
                    </SubMenu>
                  </SubMenu>
                  <MenuItem>Save</MenuItem>
                </Menu>
              </td>
            </tr>
            <tr>
              <td>2</td>
              <td>시스템공학과</td>
              <td>홍일동</td>
              <td>2021.10.06</td>
              <td>홍일동</td>
              <td>2021.10.06</td>
              <td>2021.10.06</td>
              <td>
                <Menu menuButton={<MenuButton>View</MenuButton>}>
                  <MenuItem>New File</MenuItem>
                  <SubMenu label="Open">
                    <MenuItem>index.html</MenuItem>
                    <MenuItem>example.js</MenuItem>
                    <SubMenu label="Styles">
                      <MenuItem>about.css</MenuItem>
                      <MenuItem>home.css</MenuItem>
                      <MenuItem>index.css</MenuItem>
                    </SubMenu>
                  </SubMenu>
                  <MenuItem>Save</MenuItem>
                </Menu>
              </td>
              <td>
                <Menu menuButton={<MenuButton>Edit</MenuButton>}>
                  <MenuItem>New File</MenuItem>
                  <SubMenu label="Open">
                    <MenuItem>index.html</MenuItem>
                    <MenuItem>example.js</MenuItem>
                    <SubMenu label="Styles">
                      <MenuItem>about.css</MenuItem>
                      <MenuItem>home.css</MenuItem>
                      <MenuItem>index.css</MenuItem>
                    </SubMenu>
                  </SubMenu>
                  <MenuItem>Save</MenuItem>
                </Menu>
              </td>
            </tr>
            <tr>
              <td>3</td>
              <td>화학개론</td>
              <td>홍일동</td>
              <td>2021.10.06</td>
              <td>홍일동</td>
              <td>2021.10.06</td>
              <td>2021.10.06</td>
              <td>
                <Menu menuButton={<MenuButton>View</MenuButton>}>
                  <MenuItem>New File</MenuItem>
                  <SubMenu label="Open">
                    <MenuItem>index.html</MenuItem>
                    <MenuItem>example.js</MenuItem>
                    <SubMenu label="Styles">
                      <MenuItem>about.css</MenuItem>
                      <MenuItem>home.css</MenuItem>
                      <MenuItem>index.css</MenuItem>
                    </SubMenu>
                  </SubMenu>
                  <MenuItem>Save</MenuItem>
                </Menu>
                
              </td>
              <td>
                <Menu menuButton={<MenuButton>Edit</MenuButton>}>
                  <MenuItem>New File</MenuItem>
                  <SubMenu label="Open">
                    <MenuItem>index.html</MenuItem>
                    <MenuItem>example.js</MenuItem>
                    <SubMenu label="Styles">
                      <MenuItem>about.css</MenuItem>
                      <MenuItem>home.css</MenuItem>
                      <MenuItem>index.css</MenuItem>
                    </SubMenu>
                  </SubMenu>
                  <MenuItem>Save</MenuItem>
                </Menu>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      </div>
      <FaPlus />
      <span> New</span>

      {/* <Modal show={this.show} handleClose={this.hideModal}>
          <p>Modal</p>
        </Modal>
        <button type="button" onClick={this.showModal}>
          Open
        </button> */}
    </div>

    //     <Page title="Cards" breadcrumbs={[{ name: 'cards', active: true }]}>
    //       <Row>
    //         <Col md={6} sm={6} xs={12} className="mb-3">
    //           <Card className="flex-row">
    //             <CardImg
    //               className="card-img-left"
    //               src={bg1Image}
    //               style={{ width: 'auto', height: 150 }}
    //             />
    //             <CardBody>
    //               <CardTitle>Horizontal Image Card(Left)</CardTitle>
    //               <CardText>
    //                 Some quick example text to build on the card title and make up
    //                 the bulk of the card's content.
    //               </CardText>
    //             </CardBody>
    //           </Card>
    //         </Col>

    //         <Col md={6} sm={6} xs={12} className="mb-3">
    //           <Card className="flex-row">
    //             <CardBody>
    //               <CardTitle>Horizontal Image Card(Right)</CardTitle>
    //               <CardText>Some quick example card</CardText>
    //             </CardBody>
    //             <CardImg
    //               className="card-img-right"
    //               src={bg3Image}
    //               style={{ width: 'auto', height: 150 }}
    //             />
    //           </Card>
    //         </Col>
    //       </Row>
    //       <Row>
    //         <Col md={6} sm={6} xs={12} className="mb-3">
    //           <Card>
    //             <CardImg top src={bg11Image} />
    //             <CardBody>
    //               <CardTitle>Card with image</CardTitle>
    //               <CardText>
    //                 Some quick example text to build on the card title and make up
    //                 the bulk of the card's content.
    //               </CardText>
    //             </CardBody>
    //           </Card>
    //         </Col>

    //         <Col md={6} sm={6} xs={12} className="mb-3">
    //           <Card>
    //             <CardImg top src={bg18Image} />
    //             <CardBody>
    //               <CardTitle>Card with list group</CardTitle>
    //               <CardText>
    //                 This example shows how to use card with list group{' '}
    //               </CardText>
    //             </CardBody>
    //             <ListGroup flush>
    //               <ListGroupItem>Cras justo odio</ListGroupItem>
    //               <ListGroupItem>Dapibus ac facilisis in</ListGroupItem>
    //               <ListGroupItem>Morbi leo risus</ListGroupItem>
    //             </ListGroup>
    //             <CardBody>
    //               <CardLink tag="a" href="#">
    //                 Go to details
    //               </CardLink>
    //               <CardLink tag="a" href="#">
    //                 More
    //               </CardLink>
    //             </CardBody>
    //           </Card>
    //         </Col>
    //       </Row>

    //       <Row>
    //         {['', 'top', 'left', 'right'].map((color, index) => (
    //           <Col key={index} md={6} sm={6} xs={12} className="mb-3">
    //             <Card
    //               inverse
    //               className={`border-0 bg-gradient-theme${
    //                 !!color ? '-' : ''
    //               }${color}`}
    //               style={{
    //                 height: 200,
    //               }}
    //             >
    //               <CardBody className="d-flex flex-column justify-content-start align-items-start">
    //                 <CardTitle>Card title</CardTitle>
    //                 <CardText>card text</CardText>
    //               </CardBody>

    //               <CardBody className="d-flex justify-content-between align-items-center">
    //                 <CardText>Karl David</CardText>
    //                 <Button outline color="light">
    //                   Click
    //                 </Button>
    //               </CardBody>
    //             </Card>
    //           </Col>
    //         ))}
    //       </Row>

    //       <Row>
    //         {overlayCards.map(({ imgUrl }, index) => {
    //           return (
    //             <Col key={index} md={6} sm={6} xs={12}>
    //               <Card inverse className="text-center">
    //                 <CardImg width="100%" src={imgUrl} alt="Card image cap" />
    //                 <CardImgOverlay>
    //                   <CardTitle>Card Title</CardTitle>
    //                   <CardText>inversed card</CardText>
    //                   <CardText>
    //                     <small className="text-muted">
    //                       Last updated 3 mins ago
    //                     </small>
    //                   </CardText>
    //                 </CardImgOverlay>
    //               </Card>
    //             </Col>
    //           );
    //         })}
    //       </Row>

    //       <Row>
    //         <Col md={5}>
    //           <UserCard
    //             avatar={user1Image}
    //             title="Chris"
    //             subtitle="Project Lead"
    //             text="Give me a star!"
    //             style={{
    //               height: 300,
    //             }}
    //           />
    //         </Col>

    //         <Col md={7}>
    //           <Card>
    //             <Line
    //               data={getStackLineChart({
    //                 labels: [
    //                   'January',
    //                   'February',
    //                   'March',
    //                   'April',
    //                   'May',
    //                   'June',
    //                   'July',
    //                 ],
    //                 data: [0, 13000, 5000, 24000, 16000, 25000, 10000],
    //               })}
    //               options={stackLineChartOptions}
    //             />
    //             <CardBody className="text-primary" style={{ position: 'absolute' }}>
    //               <CardTitle>Chart Card</CardTitle>
    //             </CardBody>
    //           </Card>
    //         </Col>
    //       </Row>

    //       <Row>
    //         {bgCards.map(({ color }, index) => (
    //           <Col key={index} lg={4} md={6} sm={6} xs={12} className="mb-3">
    //             <Card inverse color={color}>
    //               <CardBody>
    //                 <CardTitle className="text-capitalize">
    //                   {color} card title
    //                 </CardTitle>
    //                 <CardText>
    //                   Some quick example text to build on the card title and make up
    //                   the bulk of the card's content.
    //                 </CardText>
    //               </CardBody>
    //             </Card>
    //           </Col>
    //         ))}
    //       </Row>

    //       <Row>
    //         {gradientCards.map(({ color }, index) => (
    //           <Col key={index} lg={4} md={6} sm={6} xs={12} className="mb-3">
    //             <Card
    //               inverse
    //               className={`bg-gradient-${color} text-center`}
    //               style={{ height: 200 }}
    //             >
    //               <CardBody className="d-flex flex-column flex-wrap justify-content-center align-items-center">
    //                 <CardTitle>Gradient {color} title</CardTitle>
    //                 <CardText>card text</CardText>
    //               </CardBody>
    //             </Card>
    //           </Col>
    //         ))}
    //       </Row>
    //     </Page>
  );
}
// }

// export default FolderPage;
