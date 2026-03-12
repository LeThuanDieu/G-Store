import React, { useEffect, useState } from "react";
import axiosClient from "../services/axiosClient";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);

  useEffect(() => {
    axiosClient
      .get("/cart")
      .then((res) => setCartItems(res.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      <h2>Cart</h2>

      {cartItems.map((item) => (
        <div key={item.id}>
          <h4>{item.productName}</h4>

          <p>Quantity: {item.quantity}</p>

          <p>{item.price} đ</p>
        </div>
      ))}
    </div>
  );
};

export default Cart;
