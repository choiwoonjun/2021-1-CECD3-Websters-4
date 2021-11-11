import React,{useState,useEffect} from 'react'
import videoAPI from '../../apis/video';
import MeetingLog from './MeetingLog';
import './style.scss';


function VideoPage(props) {
    const videoName=props.match.params.videoName
    const [Scripts, setScripts] = useState([]);
    const [Bookmarks, setBookmarks] = useState([]);
    const [url, setUrl] = useState("")
    useEffect( () => {
        videoAPI.getBookmarks(videoName).then(res=>{
            const results =res.data.results
            setBookmarks(results.map(o=>o.time))
        })
        videoAPI.getScripts(videoName).then(res=>{
            const results =res.data.results
            
            setScripts(results)
        })


    }, [])

    
    return (
        
        <div id="container">
            <div class="videoContainer">
                <video
                    id="video"
                    src="../testVideo.mp4"
                    controls
                    ></video>
            </div>
                

            <MeetingLog scripts={Scripts} bookmarks={Bookmarks}></MeetingLog>
        </div>
    )
}

export default VideoPage
