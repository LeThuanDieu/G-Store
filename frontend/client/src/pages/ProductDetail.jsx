import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import axiosClient from "../services/axiosClient";

const ProductDetail = () => {
  const { id } = useParams();

  const [product, setProduct] = useState(null);

  useEffect(() => {
    axiosClient
      .get(`products/${id}`)
      .then((res) => setProduct(res.data))
      .catch((err) => console.log(err));
  }, [id]);

  if (!product) return <p>Loading...</p>;

  return (
    <div className="detail">
      <img src={product.images?.[0]} />

      <h2>{product.name}</h2>

      <p>{product.price.toLocaleString()} đ</p>

      <p>Stock: {product.stock}</p>

      <button>Add to Cart</button>
    </div>
  );
};

export default ProductDetail;
