import { useParams } from "react-router-dom";
import React, { cloneElement } from 'react';
import { Breadcrumb, Layout, Menu, theme, Image, message } from 'antd';
import { LeftOutlined } from '@ant-design/icons';
import { Button, Tooltip, Space } from 'antd';
import { Input } from 'antd';
import { useEffect } from "react";
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Checkbox, Form } from 'antd';
import { InfoCircleOutlined, } from '@ant-design/icons';
import { queryAllByLabelText } from "@testing-library/react";
import { Route, BrowserRouter, Routes,Link } from 'react-router-dom'
import { SmileOutlined } from '@ant-design/icons';
import { Result } from 'antd';
const { Header, Content, Footer } = Layout;
const { TextArea } = Input;
const TOKEN_KEY = 'Authorization'
function Login() {
    const [messageApi, contextHolder] = message.useMessage();
    const success = () => {
    messageApi.open({
      type: 'success',
      content: 'This is a success message',
    });
  };
 
  const error = () => {
    messageApi.open({
      type: 'error',
      content: 'This is an error message',
    });
  };

  const warning = () => {
    messageApi.open({
      type: 'warning',
      content: 'This is a warning message',
    });
  };
    const {
        token: { colorBgContainer },
    } = theme.useToken();
    const onFinish = (values) => {
        console.log('Received values of form: ', values);
    };
    const t = "http://localhost:8081/login"
    const [messag, setMessag] = React.useState(Date.now)
    const [user, setUser] = React.useState('')
    const [pass, setPass] = React.useState('')
    const [codeT, setCodeT] = React.useState('')
    const [u, setU] = React.useState('')

    function login() {

        fetch(t, {
            method: 'POST',
            body: JSON.stringify({
                string: user,
                password: pass,
                code: codeT,
                u: u
                // Add parameters here
            }),
            headers: {
                'Content-type': 'application/json'
            },
        })
            .then(res => res.json(), console.log('sadasdsa'))
            .then(
                (result) => {
                    console.log(result.mess)
                    if (result.mess == '验证码不正确' || result.mess == '登录失败') {
                        console.log(result.mess)
                        error()
                    }
                    else {
        success()
        localStorage.setItem(TOKEN_KEY,result.mess)
        window.location.reload()
    }
},
// 注意：需要在此处处理错误
// 而不是使用 catch() 去捕获错误
// 因为使用 catch 去捕获异常会掩盖掉组件本身可能产生的 bug
(error) => {

}
            )
    }
function change() {
    setMessag(Date.now)
}
const [code, setCode] = React.useState('')
const q = <img width='60px' height='30px' src={"data:audio/wav;base64," + code} onClick={change} />
useEffect(() => {
    fetch("http://localhost:8081/getCode")
        .then(res => res.json())
        .then(
            (result) => {
                setCode(result.code)
                console.log(result.uuid)
                setU(result.uuid)
                console.log(result.uuid)
            },
            // 注意：需要在此处处理错误
            // 而不是使用 catch() 去捕获错误
            // 因为使用 catch 去捕获异常会掩盖掉组件本身可能产生的 bug
            (error) => {
            }
        )
}, [])
useEffect(() => {
    fetch("http://localhost:8081/getCode")
        .then(res => res.json())
        .then(
            (result) => {
                setCode(result.code)
                setU(result.uuid)
            },
            // 注意：需要在此处处理错误
            // 而不是使用 catch() 去捕获错误
            // 因为使用 catch 去捕获异常会掩盖掉组件本身可能产生的 bug
            (error) => {
            }
        )
}, [messag])

const onChangeU = (e) => {
    setUser(e.target.value)
};
const onChangeP = (e) => {
    setPass(e.target.value)
};
const onChangeC = (e) => {
    setCodeT(e.target.value)
};
return (
    <Layout className="layout">
        <Header>
            <div className="logo" />
        </Header>
        <Content style={{ padding: '0 50px' }}>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>

            <Form
                style={{ padding: '0 380px' }}
                name="normal_login"
                className="login-form"
                initialValues={{ remember: true }}
                onFinish={onFinish}
            >

                <Form.Item
                    style={{ width: '380px' }}
                    name="username"
                    rules={[{ required: true, message: 'Please input your Username!' }]}
                >

                    <Input prefix={<UserOutlined className="site-form-item-icon" />} onChange={onChangeU} placeholder="Username" />
                </Form.Item>
                <Form.Item
                    style={{ width: '380px' }}
                    name="password"

                    rules={[{ required: true, message: 'Please input your Password!' }]}
                >
                    <Input
                        prefix={<LockOutlined className="site-form-item-icon" />}
                        type="password"
                        placeholder="Password" onChange={onChangeP}
                    />
                </Form.Item>
                <Form.Item style={{ width: '380px' }}>
                    <>
                        <Input placeholder="code" onChange={onChangeC} suffix={q} />
                    </>
                </Form.Item>
                <Form.Item>
                    <Form.Item name="remember" valuePropName="checked" noStyle>
                        <Checkbox>Remember me</Checkbox>
                    </Form.Item>

                    <a className="login-form-forgot" href="">
                        Forgot password
                    </a>
                </Form.Item>

                <Form.Item >
                    <Button onClick={login} type="primary" htmlType="submit" className="login-form-button" style={{ width: '380px' }} >
                        Log in
                    </Button>
                    <br></br>
                    Or <a href="">register now!</a>
                </Form.Item>

            </Form>
            <>
           {contextHolder}
      
            </>
        </Content>
        <Footer style={{ textAlign: 'center' }}>Ant Design ©2023 Created by Ant UED</Footer>
    </Layout>
)
}
export default Login;










  

 