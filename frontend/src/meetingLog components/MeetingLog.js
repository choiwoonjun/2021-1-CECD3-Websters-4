import React, { useEffect, useState } from 'react';
import getMeetLog from '../apis/getMeetLog';
import Section from '../meetingLog components/Section';
import transcription from './datas/edit.json';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import { Carousel } from 'react-responsive-carousel';
// import {
//   MDBCarousel,
//   MDBCarouselInner,
//   MDBCarouselItem,
//   MDBCarouselElement,
// } from 'mdb-react-ui-kit';
function MeetingLog() {
  const [Info, setInfo] = useState({});
  const [Sections, setSections] = useState([]);
  // useEffect(async () => {
  //     const info=await getMeetLog('testLog')
  //     console.log(info)
  //     setInfo(info)
  // }, [])
  useEffect(async () => {
    // 클라이언트 개발용
    setInfo(transcription);
  }, []);

  useEffect(() => {
    //Info를 section 으로 나누는 작업
    let sections = [];
    let sliceIdx = 0;
    if (!Info.images) return;

    let divideTime = Info.images.map(image => image.time);
    divideTime.shift();
    divideTime.push(10 ** 4);
    for (let i = 0; i < Info.images.length; i++) {
      let section = { transcripts: [] };
      section.image = Info.images[i];
      while (
        sliceIdx < Info.transcripts.length &&
        Info.transcripts[sliceIdx].time < divideTime[i]
      ) {
        section.transcripts.push(Info.transcripts[sliceIdx]);
        sliceIdx += 1;
      }
      sections.push(section);
    }
    setSections(sections);
  }, [Info]);
  return (
    <div className="meeting_log">
      <Carousel
        showThumbs={true}
        showStatus={false}
        infiniteLoop
        // emulateTouch
        // autoPlay
        useKeyboardArrows
        transitionTime={1000}
        // axis="vertical"
        // selectedItem={1}
        showArrows={true}
        // height="20%"
        // width="80%"
      >
        {Sections &&
          Sections.map(section => {
            return <Section info={section} />;
          })}
      </Carousel>
    </div>
  );
}

export default MeetingLog;
