
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import axios from "axios";
// import { Editor, EditorTools } from "@progress/kendo-react-editor";
// import content from "./content-overview";
import { RichTextEditor } from 'react-rte';
import { CKEditor } from 'ckeditor4-react';
import './style.scss';
const UploadVideoPage = (props) => {
    const { width, height } = props;
    const inputRef = React.useRef();
    const [source, setSource]= React.useState();

    const handleFileChange = (event) => {
        const file = event.target.files[0];
        const url = URL.createObjectURL(file);
        setSource(url);
      };
      
  const handleChoose = (event) => {
    inputRef.current.click();
  };
const handleUpload=(event) =>{
    const file = event.target.files[0];
    const url = URL.createObjectURL(file);
    Upload(url);

}
  const Upload = (file) =>{
      try{
          console.log("Video Upload");
          const formData = new FormData();
          console.log(formData)
          formData.append("filename",file)
          console.log(file)
          formData.append("destination","videos")
          formData.append("Create_Video",true)
          const config={headers:{"content-type":"multipart/form-data"}}

          const API= "API";
          const HOST="HOST";
          const url=`${HOST}/${API}`;

          const result= axios.post(url, formData, config);
          console.log("Result",result);
      }catch(error){
          console.log(error);
      }
  }
  return (
    <div className="video-page">
     <h3>Video Page</h3>
     <div class="navbarr">
        <a href="#home">Import</a>
        <a href="#news">Edit</a>
        <a href="#home">Setting</a>
      </div>
      <div className="VideoInput">
      <input
        ref={inputRef}
        className="VideoInput_input"
        type="file"
        onChange={handleFileChange}
        accept=".mov,.mp4"
      />
      {!source && <button className="select-file"onClick={handleChoose}>파일 선택</button>}

      {source && (  
        <video
          className="VideoInput_video"
          width="500px"
          height="200px"
          controls
          src={source}
        />
      )}
      {/* <button onClick={handleChoose}>비디오 선택</button> */}
      <div className="VideoInput_footer"> {source ?'비디오 재생 중' : '선택된 영상 없습니다'}</div>
      <button className="send-video" onClick={handleUpload}>전송</button>

      </div>
      {/* <button className="send-video">전송</button> */}
    </div>
  );
};

// ReactDOM.render(<EditorPage />, document.querySelector("my-app"));
export default UploadVideoPage;
