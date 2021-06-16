import Page from 'components/Page';
import React from 'react';
import MeetingLog from '../meetingLog components/MeetingLog'
import { Card, CardBody, CardHeader, Col, Row } from 'reactstrap';
import {
  // Button,
  // Form,
  // FormFeedback,
  // FormGroup,
  // FormText,
  Input,
  Label,
} from 'reactstrap';
import { getColor } from 'utils/colors';

const today = new Date();
const lastWeek = new Date(
  today.getFullYear(),
  today.getMonth(),
  today.getDate() - 7,
);

class DashboardPage extends React.Component {
  componentDidMount() {
    // this is needed, because InfiniteCalendar forces window scroll
    window.scrollTo(0, 0);
  }

  render() {
    const primaryColor = getColor('primary');
    const secondaryColor = getColor('secondary');

    return (
      <Page
        className="DashboardPage"
        title="회의록"
        breadcrumbs={[{ name: 'Dashboard', active: true }]}
      >
        <Row>
          <Col lg="6" md="12" sm="12" xs="12">
            <Card>
              <CardHeader style={{ }}>
                생성된 회의록 <Label for="exampleDate" />
              </CardHeader>
              <MeetingLog/>
              <CardBody style={{ height: '100vh' }} />
            </Card>
          </Col>

          <Col lg="6" md="12" sm="12" xs="12">
            <Card>
              <Input type="date" name="date" id="date" placeholder="date" />
              <CardHeader>Shown Video</CardHeader>
              <CardBody style={{ height: '100vh' }}>
                <video id='meetingVideo' src="./testVideo.mp4" style={{width:'400px',height: '400px',border:'solid 1px'}} controls></video>
              </CardBody>
              {}
            </Card>
          </Col>
        </Row>
      </Page>
    );
  }
}
export default DashboardPage;
