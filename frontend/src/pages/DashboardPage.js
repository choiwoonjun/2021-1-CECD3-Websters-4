import Page from 'components/Page';
import React from 'react';
import MeetingLog from '../meetingLog components/MeetingLog';
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
      // <Page
      //   className="DashboardPage"
      //   title="회의록"
      //   breadcrumbs={[{ name: 'Dashboard', active: true }]}
      // >
      <div  className="editor-page">
        <h3>회의록</h3>
      
      {/* <div class="navbarr">
        <a href="#home">Import</a>
        <a href="#news">Edit</a>
        <a href="#home">Setting</a>
      </div> */}
        <div>
          <div className="testvideo">
            Test Video
            <video
              id="meetingVideo"
              className="shown_video"
              src="./testVideo.mp4"
              controls
            ></video>
          </div>
          <div className="meeting-slide">
            <MeetingLog />
          </div>
        </div>
        </div>
      // </Page>
    );
  }
}
export default DashboardPage;
