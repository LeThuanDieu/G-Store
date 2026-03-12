import React from "react";
import { Link } from "react-router-dom";

const ProductCard = ({ product, addToCart }) => {
  return (
    <div className="product-card">
      <img src={product.images?.[0]} alt={product.name} />

      <h3>{product.name}</h3>

      <p className="price">{product.price.toLocaleString()} đ</p>

      <div className="btn-group">
        <button onClick={() => addToCart(product.id)}>Add to cart</button>

        <Link to={`/product/${product.id}`}>
          <button>Detail</button>
        </Link>
      </div>
    </div>
  );
};

export default ProductCard;
