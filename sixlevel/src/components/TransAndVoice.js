import { useParams } from "react-router-dom";
import React from 'react';
import { Breadcrumb, Layout, Menu, theme } from 'antd';
import { LeftOutlined } from '@ant-design/icons';
import { Button, Tooltip, Space } from 'antd';
import { Input } from 'antd';
import { useEffect } from "react";
const { Header, Content, Footer } = Layout;
const { TextArea } = Input;
function TransAndVoice(props) {
  const [audio, setAudio] = React.useState('')
  const [audioE, setAudioE] = React.useState('')
  const [audioC, setAudioC] = React.useState('')
  const [robot, setRobot] = React.useState('1')
  let button;
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  const params = useParams()
  console.log(params.id)
  const TOKEN_KEY = 'Authorization'
  const p = "data:audio/wav;base64,"
  const t = "http://localhost:8081/getAudioById/"
  useEffect(() => {
    fetch(t + params.id,{headers: {
      Authorization: localStorage.getItem(TOKEN_KEY)
  }})
      .then(res => res.json())
      .then(
        (result) => {
          setAudio(result.audio)
          setAudioE(result.english)
          setAudioC(result.china)
          console.log(result)

        },
        // 注意：需要在此处处理错误
        // 而不是使用 catch() 去捕获错误
        // 因为使用 catch 去捕获异常会掩盖掉组件本身可能产生的 bug
        (error) => {
        }
      )
  }, [])
  function robot1() {
    setRobot('2')
  }
  function write() {
    setRobot('1')
  }
  if (robot == '1') {
    button = <div className="site-layout-content" style={{ background: colorBgContainer }}>
      <>
        <TextArea
          showCount
          maxLength={1500}
          style={{ height: 120, marginBottom: 24 }}
          placeholder="listen to the audio and write down what you have heard  "
        />
        <TextArea
          showCount
          maxLength={1500}
          style={{ height: 120, resize: 'none' }}
          placeholder="listen to the audio and write down what you have heard in chinese "
        />
        <div>
       
       <audio controls="controls" autobuffer="autobuffer">
         <source src={p + audio} />
       </audio>
       </div>
      </>
    </div>;
  }
  else if (robot == '2') {
    button = <div className="site-layout-content" style={{ background: colorBgContainer }}>
      <>
        <TextArea
          showCount
          maxLength={1500}
          style={{ height: 120, marginBottom: 24 }}
          value={audioE}

        ></TextArea>
        <TextArea
          showCount
          maxLength={1500}
          style={{ height: 120, resize: 'none' }}
          value={audioC}
        />
        <div>
       
       <audio controls="controls" autobuffer="autobuffer">
         <source src={p + audio} />
       </audio>
       </div>
      </>
    </div>;
  }


  return (
    <Layout className="layout">
      <Header>
        <div className="logo" />
      </Header>
      <Content style={{ padding: '0 50px' }}>
        <Breadcrumb style={{ margin: '16px 0' }}>
          <Breadcrumb.Item onClick={robot1}>Answer</Breadcrumb.Item>
          <Breadcrumb.Item onClick={write}>Train</Breadcrumb.Item>
        </Breadcrumb>
        {button}
        
      </Content>
      <Footer style={{ textAlign: 'center' }}>Ant Design ©2023 Created by Ant UED</Footer>
    </Layout>
  );

}

export default TransAndVoice;








