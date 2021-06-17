import React, { useEffect, useState } from 'react';
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
                className="duration"
                onClick={() => {
                  video.currentTime = t.time;
                  video.play();
                }}
              >
                {t.time}: <div className="speech">{t.speech}</div>
              </div>
            );
          })}
      </div>
    </div>
  );
}

export default Section;
