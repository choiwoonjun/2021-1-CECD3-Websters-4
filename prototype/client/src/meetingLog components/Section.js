import React, { useEffect, useState } from 'react';
import time from 'assets/img/logo/time.png';
import './style.scss';
// import { Carousel } from 'antd';
import { Carousel } from '@trendyol-js/react-carousel';
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
    paddingLeft: '10px'
  };
  return (
    
    
    <div className="Meeting_container">
            {/* <Carousel afterChange={""}>
    <div className="carousel">
      <h3 style={contentStyle}>1</h3>
    </div>
    <div>
      <h3 style={contentStyle}>2</h3>
    </div>
    <div>
      <h3 style={contentStyle}>3</h3>
    </div>
    <div>
      <h3 style={contentStyle}>4</h3>
    </div>
  </Carousel> */}

    {/* <h1 color="#2d66c3">We love Web1 🌐</h1>
    <h1 color="#2d66c3">We love Web2 🌐</h1>
    <h1 color="#2d66c3">We love Web3 🌐</h1>
    <h1 color="#2d66c3">We love Web4 🌐</h1> */}'
  {/* <button>Click</button>
    <div>
      <h1>{Info.transcripts &&
          Info.transcripts.map(t => {
            return (              
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
            );
          })}</h1>
    
          </div> */}


<Carousel show={1} slide={1} swiping={true} >
      <div className="">
        <img className="video" src={imageUrl} />
      </div>

      <div className="meeting_log">
        {Info.transcripts &&
          Info.transcripts.map(t => {
            return (              
              <div
                className="speech"
                onClick={() => {
                  video.currentTime = t.time;
                  video.play();
                }}
              >
                {/* <img src={time} style={{ width: '20px' }} /> */}
                {t.time} : {t.speech}
              </div>
            );
          })}
      </div></Carousel>
    </div>
  );
}

export default Section;
