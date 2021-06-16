import React,{useEffect,useState} from 'react'

function Section(props) {
    const [Info, setInfo] = useState({})
    useEffect(() => {
        setInfo(props.info)
    }, [props.info])
    const imageUrl=`http://localhost:5000/meetingLog/image?imagePath=${Info.image&&Info.image.path.replaceAll('/','%2F')}`
    return (
        <div style={{width:'1000px',border:'1px solid'}}>
            <img src={imageUrl} 
                style={{width:'200px',height:'100px'}}
            />
            {Info.transcripts&&Info.transcripts.map((t)=>{
                return <div>{t.speech}</div>
            })}
        </div>
    )
}

export default Section
