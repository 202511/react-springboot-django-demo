import React from 'react';
import { Breadcrumb, Layout, Menu, theme, Image } from 'antd';
import { Carousel } from 'antd';
import { LikeOutlined, MessageOutlined, StarOutlined } from '@ant-design/icons';
import { Avatar, List, Space } from 'antd';
import { Route, BrowserRouter, Routes,Link } from 'react-router-dom'
import TransAndVoice from './TransAndVoice';
const { Header, Content, Footer } = Layout;

function Voice() {
  const l='/trans/'
  const data = Array.from({ length: 23 }).map((_, i) => ({
    key: i,
    title: `please relax  ${i}`,
    avatar: `https://pic1.zhimg.com/v2-c1c0953cc850ec68b807e68e20cf84cf_r.jpg?source=1940ef5c`,
    description:
      'Ant Design, a design language for background applications, is refined by Ant UED Team.',
    content:
      'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  }));

  const IconText = ({ icon, text }) => (
    <Space>
      {React.createElement(icon)}
      {text}
    </Space>
  );
  return (
    <List
      itemLayout="vertical"
      size="large"
      pagination={{
        onChange: (page) => {
          console.log(page);
        },
        pageSize: 3,
      }}
      dataSource={data}
      footer={
        <div>
          <b>ant design</b> footer part
        </div>
      }
      renderItem={(item) => (
        <List.Item
          key={item.title}
          actions={[
            <IconText icon={StarOutlined} text="156" key="list-vertical-star-o" />,
            <IconText icon={LikeOutlined} text="156" key="list-vertical-like-o" />,
            <IconText icon={MessageOutlined} text="2" key="list-vertical-message" />,
          ]}
          extra={
            <img
              width={272}
              alt="logo"
              src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
            />
          }
        >
          <List.Item.Meta
            avatar={<Avatar src={item.avatar} />}
            title={<Link to={l+item.key}>{item.title}</Link>}
            description={item.description}
          />
          
        </List.Item>
      )}
    />
  );

}

export default Voice;






