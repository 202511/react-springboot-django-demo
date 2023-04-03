
import React from 'react';
import { Breadcrumb, Layout, Menu, theme ,Image} from 'antd';
import { Carousel } from 'antd';
import MainPage from './components/MainPage';
import Voice from './components/Voice';
import Contact  from './components/Contact';
const { Header, Content, Footer } = Layout;
const array = ['主页','热播榜单', '语音栏目', '聊天室', '收藏', '我的']


function App() {
  
  const [message,setMessage] = React.useState(2)
  const contentStyle = {
    height: '160px',
    color: '#fff',
    lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
  };
  
  const {
    token: { colorBgContainer },
  } = theme.useToken();
  let button;
  if (message==0 ) {
    button=<MainPage/>;
  }
  else if (message==1 )
  {
    button=<div>热播榜单</div>;
  }
  else if( message == 2)
  {
    button=<Voice/>;
  }
  else if ( message == 3)
  {
    button=<Contact />;
  }
  else if ( message ==4 )
  {
    button=<div>收藏</div>;
  }
  else
  {
    button=<div>我的</div>;
  }
  
  function MenuIndex(props)
  {
   
   console.log(props.key)
   let number=props.key
   console.log(number==0)
   setMessage(number)
   console.log(message)
   console.log(button)
  }



  return (



    <Layout className="layout">
      <Header>
        <div className="logo" />
        <Menu
          onClick={MenuIndex}
          theme="dark"
          mode="horizontal"
          defaultSelectedKeys={['2']}
        
          items={array.map((value,index) => {
            return {
              key:index,
              label: value,
            }
          })}
        />
      </Header>
      {button}
      <Footer style={{ textAlign: 'center' }}>Ant Design ©2023 Created by Ant UED</Footer>
    </Layout>
  );

}

export default App;

