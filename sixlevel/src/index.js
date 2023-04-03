import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { Navigate, Redirect, Route, Routes } from 'react-router-dom'
import {  BrowserRouter,  Link } from 'react-router-dom'
import TransAndVoice from './components/TransAndVoice';
import Login from './components/Login';
import { useState } from 'react';
const root = ReactDOM.createRoot(document.getElementById('root'));
const TOKEN_KEY = 'Authorization'
// 获取 token
let button;
if(localStorage.getItem(TOKEN_KEY)!=null)
{
     button=<BrowserRouter>
     <Routes>
         <Route path="/" element={<App />}></Route>
         <Route path="/trans/:id" element={<TransAndVoice/>} />
     </Routes>
 </BrowserRouter>;
}
else 
{
    button=<BrowserRouter>
    <Routes>
        <Route path="/*" element={<Login/>}></Route>
    </Routes>
</BrowserRouter>;
}

  
root.render(
    <div>
    {button}</div>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
