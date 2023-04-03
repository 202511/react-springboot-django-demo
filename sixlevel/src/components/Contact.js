import { useEffect, useRef, useState } from "react";
import axios from 'axios';
import Right from "./Right";
import  Left from './Left';
import '../css/contact.css'
const TOKEN_KEY = 'Authorization'

let websocket;
let username;

function Contact() {
    
    const messageRef = useRef(null)
    const [messageList,setMessageList] = useState([])
    const [message,setMessage] = useState("")
    const [userList,setUserList] = useState([])
    const data = [
        {
            render:(item) => {
                return <>item</>
            }
        }
    ]
    useEffect(() => {
        async function start() {
            if (!localStorage.getItem('username')) {
                await axios.get("http://localhost:8081/getName",{headers: {
                    Authorization: localStorage.getItem(TOKEN_KEY)
                }}).then(response => {
                    localStorage.setItem('username', response.data)
                })
            }
            username = localStorage.getItem('username')
            let baseUrl = "ws://localhost:8081/websocket/"
            websocket = new WebSocket(baseUrl + localStorage.getItem('username'));
    
            websocket.onopen =  ()=> {
                console.log("建立 websocket 连接...");
            };
            websocket.onmessage = (event) => {
                
                const data = event.data
                setMessage(data)
            };
            websocket.onerror =  (event) => {
                console.log("websocket发生错误..." + event + '\n');
            }
    
            websocket.onclose =  ()=> {
                console.log("关闭 websocket 连接...");
            };
        }
        start()
    },[])
    useEffect(() => {
        console.log(message)
        if (message.indexOf(":") > 0) {
            setMessageList([...messageList,message])
            console.log(messageList)
            setMessage("")
            return 
        }
        if (message.indexOf("登录") > 0) {
            setUserList([...userList.filter(item => {
                return item !== message
            }),message])
            setMessage("")
            return
        }
        if (message.indexOf('离开') > 0) {
            let messageUsername = message.substr(0,message.indexOf("]") + 1)
            setUserList([...userList.filter(item => item.indexOf(messageUsername) < 0 )])
            setMessage("")
            return
        }
    },[message])


    const sendMessage = () => {
        const message = messageRef.current.value
        if (message.trim() === "") {
            alert("请重新输入")
            return
        }
        messageRef.current.value = ""
        websocket.send(message)
    }
    return (
        <>
          
            <div className="container">
                <div className="chart">
                    {
                        messageList.map(item => {
                            if (item.indexOf(username) > 0) {
                                console.log(username)
                                return <Right value={item} key={item + Math.random()} />
                            } else {
                                return <Left value={item} key={item + Math.random()}/>

                            }
                        })
                    }
                </div>
                <div className="input-value">
                    <textarea ref={messageRef} type="text" placeholder="发送消息" />
                    <button onClick={sendMessage}>发送</button>
                </div>
            </div>
            
            <div className="online">

                <span>当前在线人数 {userList.length}</span>
                {
                    userList.map(item => {
                        return <h2 key={Math.random()}>{item}</h2>
                    })
                }
            </div>
        </>
    )

}


export default Contact;

