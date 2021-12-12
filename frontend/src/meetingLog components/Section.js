import React, { useEffect, useState } from 'react';
import time from 'assets/img/logo/time.png';
import './style.scss';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";
// import { Carousel } from 'antd';
// import { Carousel } from '@trendyol-js/react-carousel';
// var Carousel = require('react-responsive-carousel').Carousel;
// import Carousel from "@davistran86/carousel";
// import {
//   MDBCarousel,
//   MDBCarouselInner,
//   MDBCarouselItem,
//   MDBCarouselElement,
// } from 'mdb-react-ui-kit';
function Section(props) {
  const [Info, setInfo] = useState({});
  useEffect(() => {
    setInfo(props.info);
  }, [props.info]);
  //const imageUrl=`http://localhost:5000/meetingLog/image?imagePath=${Info.image&&Info.image.path.replaceAll('/','%2F')}`
  const imageUrl = ``;
  const video = document.getElementById('meetingVideo');
  const contentStyle = {
    height: '160px',
    color: '#fff',
    lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
    margin: '1.5rem auto',
    paddingLeft: '10px',
  };
  return (
    
    <div className="Meeting_container">
      <div className="smallerVideo">
        <img className="video" src={imageUrl} />
      </div>
      <div className="meeting_log">
        {Info.transcripts &&
          Info.transcripts.map(t => {
            return (
              <div>
                <div
                  className="speech"
                  onClick={() => {
                    video.currentTime = t.time;
                    video.play();
                  }}
                >
                  <img src={time} style={{ width: '20px' }} />
                  {t.time} : {t.speech}

                </div>
              </div>
            );
          })}
      </div>
    </div>
  );
}

export default Section;
