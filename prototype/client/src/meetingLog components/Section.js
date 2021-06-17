import React, { useEffect, useState } from 'react';
import time from 'assets/img/logo/time.png';
import './style.scss';
function Section(props) {
  const [Info, setInfo] = useState({});
  useEffect(() => {
    setInfo(props.info);
  }, [props.info]);
  //const imageUrl=`http://localhost:5000/meetingLog/image?imagePath=${Info.image&&Info.image.path.replaceAll('/','%2F')}`
  const imageUrl = ``;
  const video = document.getElementById('meetingVideo');
  return (
    <div className="Meeting_container">
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
               <img src={time} style={{width:"10px"}}/> {t.time} : {t.speech}
              </div>
            );
          })}
      </div>
    </div>
  );
}

export default Section;
