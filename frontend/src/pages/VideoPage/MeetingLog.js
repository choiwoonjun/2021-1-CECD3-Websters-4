import React,{useState,useEffect} from 'react'
import { Button } from 'antd';


function MeetingLog(props) {

    const [Slides, setSlides] = useState([]);
    const [Index, setIndex] = useState(0);
    const [ImageURLs, setImageURLs] = useState([]);
    const [Url, setUrl] = useState("")
    const video = document.getElementById('video');

    useEffect(() => {
        const bookmarks=props.bookmarks
        const scripts= props.scripts
        let slides=[]
        let idx=0
        let temp=[]
        for(let script of scripts){
            if(script.end_time>bookmarks[idx]){
                idx+=1
                slides.push(temp)
                temp=[]
            }

            temp.push(script)

        }
        slides.push(temp)
        setSlides(slides)

        setTimeout(() => {
            getUrls(video)
                .then((urls)=>{

                    console.log(urls);
                    setImageURLs(urls)
                })
        }, 100);

        setTimeout(() => {
            capture(0)
                .then((url)=>{setUrl(url)})
                .catch(()=>{setUrl("")})
        }, 100);

        

        


    }, [props.scripts]);


    useEffect(() => {
        if(Index==0){
            capture(0)
            .then((url)=>{setUrl(url)})
            .catch(()=>{setUrl("")})
        }else{
            capture(props.bookmarks[Index-1])
            .then((url)=>{setUrl(url)})
            .catch(()=>{setUrl("")})
        }

        
    }, [Index])

    function getUrls(video){
        return new Promise((res,rej)=>{
            let urls=[]
            for(let time of props.bookmarks){
                capture(time)
                    .then((url)=>{
                        urls.push(url)
                    })
            }
            res(urls)

        })
    }

    function capture(time){
        return new Promise((resolve, reject) => {
            video.currentTime=time
            console.log(time);
            
            setTimeout(() => {
                const canvas = document.createElement("canvas");
                canvas.width = video.videoWidth;
                canvas.height = video.videoHeight;
                canvas.getContext('2d')
                .drawImage(video, 0, 0, canvas.width, canvas.height);
                const dataURL = canvas.toDataURL();
                resolve(dataURL)
            }, 100);
        })
    }

    const renderScripts=Slides[Index]&&Slides[Index].map((script)=>{
        return(
            <div id="script" onClick={() => {
                video.currentTime = script.start_time;
                video.play();
              }}>
                {script.content}
            </div>
        )
    })
    
    
    const prev=()=>{
        if(Index>0)
            setIndex(Index-1)
    }
    const next=()=>{
        if(Index<Slides.length-1)
            setIndex(Index+1)
    }

    
        

    return (
        <div id="meetingLog">
            <img src={Url} id="slide"/>
            <div id="scripts">
                {renderScripts}
            </div>
            <div id="buttons">
                <Button onClick={prev}>이전</Button>
                <Button onClick={next}>다음</Button>
            </div>
                
        </div>
    )
}



export default MeetingLog
