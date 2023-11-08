import React, { useState } from "react";
import { useEffect } from "react";
import Logout from "./Logout";
import { Radio, Button } from "antd";
import { useNavigate } from "react-router-dom";
import { message } from "antd";
import api from "../vnpayApi/api.js";
import {
  FacebookOutlined,
  GoogleCircleFilled,
  InstagramOutlined,
  GithubOutlined,
  MailFilled,
} from "@ant-design/icons";

function QRCode() {
  const navigate = useNavigate();

  const [value, setValue] = useState(1);
  const onChange = (e) => {
    console.log("radio checked", e.target.value);
    setValue(e.target.value);
  };
  const handleSubmit = () => {
    message.success("Data sent successfully");
    navigate("/home");
  };
  const [orderInfo, setOrderInfo] = useState({
    id: 1,
    createdDate: "2023-10-17",
    userID: 2, // Thay thế bằng giá trị thực tế của userID
    deliveryInformationId: null, // Thay thế bằng giá trị thực tế của deliveryInformationId
    promotionID: null, // Thay thế bằng giá trị thực tế của promotionID
    status: true, // Thay thế bằng giá trị thực tế của status
    totalPrice: 100.0, // Thay thế bằng giá trị thực tế của totalPrice
    quantity: 0, // Thay thế bằng giá trị thực tế của quantity
    vnp_OrderInfo: "Parrot", // Thay thế bằng thông tin đặt hàng thực tế
    vnp_OrderType: "20000", // Thay thế bằng giá trị thực tế của vnp_OrderType
    vnp_TxnRef: null, // Thay thế bằng giá trị thực tế của vnp_TxnRef
  });
  useEffect(() => {
    console.log(orderInfo);
  }, [orderInfo]);
  const handlePayment = async () => {
    try {
      const data = {
        id: orderInfo.id,
        createdDate: orderInfo.createdDate,
        userID: orderInfo.userID,
        deliveryInformationId: orderInfo.deliveryInformationId,
        promotionID: orderInfo.promotionID,
        status: orderInfo.status,
        totalPrice: orderInfo.totalPrice,
        quantity: orderInfo.quantity,
        vnp_OrderInfo: orderInfo.vnp_OrderInfo,
        vnp_OrderType: orderInfo.vnp_OrderType,
        vnp_TxnRef: orderInfo.vnp_TxnRef,
      };
      const response = await api.add(data);

      console.log(response);
      window.location.href = response;
      if (response.status === 200) {
        console.log("Payment Sucessful");
      } else {
        console.error("payment not successful ", response.status);
      }

      // setPaymentStatus(true);
    } catch (error) {
      console.error("Error:", error);
      // setPaymentStatus(false);
    }
  };
  return (
    <div>
      <section id="header">
        <a href="home">
          <img src={require("../assets/img/1.png")} class="logo" alt="" />
        </a>
        <div>
          <ul id="navbar">
            <li>
              <a href="home">Home</a>
            </li>
            <li>
              <a href="store">Our Services</a>
            </li>
            <li>
              <a href="about">About</a>
            </li>
            <li>
              <a href="contact">Contact</a>
            </li>
            <Logout></Logout>
          </ul>
        </div>
      </section>
      <section class="about container" id="about">
        <h2 class="heading">Payment</h2>
        <div class="about-text">
          <p>
            Greetings everyone! <span class="color">F Laundry</span>, as a team
            from <span class="color">SWP391</span> in class{" "}
            <span class="color">JS1701</span>, want to acknowledge that we're
            tackling a project we find quite challenging for the first time.
            Various unexpected obstacles have arisen along the way, making it
            even more demanding. We sincerely hope you'll be open-hearted and
            receptive to our project. We appreciate each and every one of you!
          </p>
        </div>
      </section>
      <div
        style={{
          width: "100%",
          display: "flex",
          margin: "5%",
          justifyContent: "center",
        }}
      >
        <Radio.Group onChange={onChange} value={value}>
          <Radio value={1}>
            <img
              style={{ width: "50%" }}
              src="https://img.vietqr.io/image/BIDV-75010000538711-compact.png"
            ></img>
          </Radio>
          <Radio value={2}>
            <h3> Thanh toán trực tiếp</h3>
          </Radio>
        </Radio.Group>
      </div>

      <div style={{ display: "flex", justifyContent: "center", margin: "5%" }}>
        <Button onClick={handlePayment} type="primary">
          Submit
        </Button>
      </div>
      <section class="footer container" id="footer">
        <div class="social">
          <a href="mailto:namdhse173582@fpt.edu.vn">
            <MailFilled className="bx" />
          </a>
          <a
            href="https://www.youtube.com/watch?v=a0qC7lG3Vfc"
            target="_blank"
            rel="noreferrer"
          >
            <GoogleCircleFilled className="bx" />
          </a>
          <a
            href="https://www.instagram.com/_5thjuly_/"
            target="_blank"
            rel="noreferrer"
          >
            <InstagramOutlined className="bx" />
          </a>
          <a
            href="https://www.facebook.com/nam0507.orc"
            target="_blank"
            rel="noreferrer"
          >
            <FacebookOutlined className="bx" />
          </a>
          <a href="https://github.com/5thJuly" target="_blank" rel="noreferrer">
            <GithubOutlined className="bx" />
          </a>
        </div>
        <div class="footer-links">
          <a
            href="https://docs.google.com/document/d/17o3KWvLfRAjSnGftBiv7JtIBD9eSEgRGTLFStxhcrsA/edit"
            target="_blank"
            rel="noreferrer"
          >
            Privacy Policy
          </a>
          <a href="#">Terms of Use</a>
          <a href="#">Disclaimer</a>
        </div>

        <p>&#169; FPT University</p>
      </section>
    </div>
  );
}
export default QRCode;
