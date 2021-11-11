import React,{useEffect,useState} from 'react'
import { Table } from 'antd';
import videoAPI from '../apis/video';
function FolderPage2() {

    const [Data, setData] = useState([])

    useEffect( () => {
        videoAPI.getList().then(res=>{
            console.log(res.data.result);
            
            setData(res.data.result)
        })}, [])

    const columns = [
      {
        title: '비디오',
        dataIndex: 'video_name',
        render: text => <a>{text}</a>,
        align:'center',
        columnWidth:100, 
		width:100
        
      },
      {
        title: '수정',
        className: 'column-money',
        dataIndex: 'video_name',
        align: 'center',
        width:100,
        render:(video_name)=><a href={`/video/Editor/${video_name}`}>수정</a>
      },
      {
        title: '조회',
        dataIndex: 'video_name',
        align:'center',
        render:(video_name)=><a href={`/video/${video_name}`}>조회</a>,
        width:100
      },
    ];

    return (
        <div>
            <Table
            columns={columns}
            dataSource={Data}
            bordered
            style={{width:'1000px'}}
        />
        </div>
        
    )
}

export default FolderPage2
