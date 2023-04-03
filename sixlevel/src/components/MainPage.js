
import React from 'react';
import { Breadcrumb, Layout, Menu, theme ,Image} from 'antd';
import { Carousel } from 'antd';
const { Header, Content, Footer } = Layout;
function MainPage() {
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
    return (
        <Content style={{ padding: '0 50px' }}>
          <Breadcrumb style={{ margin: '16px 0' }}>
            <Breadcrumb.Item>Home</Breadcrumb.Item>
            <Breadcrumb.Item>List</Breadcrumb.Item>
            <Breadcrumb.Item>App</Breadcrumb.Item>
          </Breadcrumb>
          <div className="site-layout-content" style={{ background: colorBgContainer }}>
            <Carousel effect="fade" autoplay>
                <Image
                    height={600}
                    width={800}
                  src={require('../static/star.jpg')}
                />
                <Image 
                  height={600}
                  width={800}
                  src="https://img.zcool.cn/community/01e95757af077d0000012e7e9932ae.jpg@1280w_1l_2o_100sh.jpg"
                />
                
            </Carousel>
          </div>
        </Content>
    );
  
  }
  
  export default MainPage;
  