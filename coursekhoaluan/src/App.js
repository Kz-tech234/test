import React, { useReducer } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Home from "./components/Home";

import Login from "./components/Login";
import Profile from "./components/Profile";

import AddUser from "./components/Admin/AddUser";

import AddTieuChi from "./components/GiaoVu/AddTieuChi";
import XepDeTai from "./components/GiaoVu/XepDeTai";

import { Container } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';

import { MyUserContext, MyDispatchContext } from "./config/Contexts";
import MyUserReducer from "./reducers/MyUserReducer";

function App() {
  // Khởi tạo state user và dispatch
  console.log("MyUserReducer =", MyUserReducer);
  const [user, dispatch] = useReducer(MyUserReducer, null);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <BrowserRouter>
          <Header />
          <Container className="my-4">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/admin/add-user" element={<AddUser />} />
              <Route path="/profile" element={<Profile />} />
              <Route path="/giaovu/tieuchi" element={<AddTieuChi />} />
              <Route path="/giaovu/xepdetai" element={<XepDeTai />} />
            </Routes>
          </Container>
          <Footer />
        </BrowserRouter>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
  );
}

export default App;
