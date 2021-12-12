import  React, { useState } from 'react';
import * as ReactDOM from 'react-dom';
// import { Editor, EditorTools } from "@progress/kendo-react-editor";
// import content from "./content-overview";
import { RichTextEditor } from 'react-rte';
import { CKEditor } from 'ckeditor4-react';
import { Table, Modal, Button } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';
import { Menu } from 'antd';
import { UploadOutlined, ExportOutlined, SettingOutlined } from '@ant-design/icons';
const { SubMenu } = Menu;
const columns = [
	{
	  title: 'Import',
	  dataIndex: 'video_name',
	  render: text => <a>{text}</a>,
	  align:'center',
	  columnWidth:100, 
	  width:100
	  
	},
	{
	  title: 'Export',
	  className: 'column-money',
	  dataIndex: 'video_id',
	  align: 'center',
	  width:100,
	  render:(id)=><a href={`/video/Editor/${id}`}>Export <Button type="primary" shape="round" icon={<DownloadOutlined />} size={""} /></a>
	},
	{
	  title: 'Settings',
	  dataIndex: 'video_id',
	  align:'center',
	  render:(id)=><a href={`/video/${id}`}>Settings</a>,
	  width:100
	},
  ];


const EditorPage = () => {
	const [visible, setVisible] = useState(false);
	const [current,setCurrent] = useState(false);
	
	function handleClick(){
		// console.log('click ', e);
		setCurrent(current)
	  };
  return (
    <div>
      <div className="editor-page">
        <h3>Editor Page</h3>
      
      {/* <div class="navbarr">
        <a>Import</a>
        <a>Export</a>
        <a>Setting</a>
      </div> */}
      {/* <div>
        <RichTextEditor date="data" />
      </div> */}
	   <div>
		   {/* <columns></columns>
            <Table
            columns={columns}
        /> */}
		<div>
		<Menu onClick={""} selectedKeys={[current]} mode="horizontal">
        <Menu.Item key="mail" icon={<UploadOutlined />}>
          Import
        </Menu.Item>
        <Menu.Item key="app" icon={<ExportOutlined />}>
          Export
        </Menu.Item>
        <SubMenu key="SubMenu" icon={<SettingOutlined />} title="Settings">
          <Menu.ItemGroup title="Item 1">
            {/* <Menu.Item key="setting:1">Option 1</Menu.Item>
            <Menu.Item key="setting:2">Option 2</Menu.Item> */}
          </Menu.ItemGroup>
          <Menu.ItemGroup title="Item 2">
            {/* <Menu.Item key="setting:3">Option 3</Menu.Item>
            <Menu.Item key="setting:4">Option 4</Menu.Item> */}
          </Menu.ItemGroup>
        </SubMenu>
        <Menu.Item key="alipay">
          {/* <a href="https://ant.design" target="_blank" rel="noopener noreferrer">
            Navigation Four - Link
          </a> */}
        </Menu.Item>
      </Menu>
		</div>
        </div>
      <CKEditor data="<p>Hello from the first editor working with the context!</p>"></CKEditor>
      <div className="button-center">
      {/* <button className="button">Save</button> */}
	  <Button type="primary" onClick={() => setVisible(true)}>
       저장
      </Button>
	  <Modal
        title="수정한 회의록을 저장하시겠습니까?"
        centered
        visible={visible}
        onOk={() => setVisible(false)}
        onCancel={() => setVisible(false)}
        width={500}
      >
      </Modal>
      {/* <button>Reset</button> */}
	  <Button danger onClick={() => ""}>
       취소
      </Button>
      </div>
      </div>
    </div>
    
  );
};

// ReactDOM.render(<EditorPage />, document.querySelector("my-app"));
export default EditorPage;
