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
        columnWidth:100
        ,width:500
        
      },
      {
        title: '수정',
        className: 'column-money',
        dataIndex: 'video_id',
        align: 'center',
        width:100,
        render:(id)=><a href={`/video/Editor/${id}`}>수정</a>
      },
      {
        title: '조회',
        dataIndex: 'video_id',
        align:'center',
        render:(id)=><a href={`/video/${id}`}>조회</a>,
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
