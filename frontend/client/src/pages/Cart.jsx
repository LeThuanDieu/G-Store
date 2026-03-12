import React from "react";
import { useCart } from "../context/CartContext";
import { Trash2, Plus, Minus, ShoppingBag } from "lucide-react";
import { Link } from "react-router-dom";

const Cart = () => {
  const { cartItems, removeFromCart, updateQuantity } = useCart();

  // Tính tổng tiền: Luôn ép kiểu Number để an toàn tuyệt đối
  const totalPrice = cartItems.reduce((sum, item) => {
    return sum + Number(item.price || 0) * Number(item.quantity || 0);
  }, 0);

  if (cartItems.length === 0) {
    return (
      <div className="flex flex-col items-center justify-center p-20 text-center">
        <ShoppingBag className="w-16 h-16 text-gray-300 mb-4" />
        <p className="text-gray-500 text-xl">Giỏ hàng của bạn đang trống.</p>
        <Link to="/" className="mt-4 text-blue-600 hover:underline">
          Quay lại mua sắm
        </Link>
      </div>
    );
  }

  return (
    <div className="max-w-4xl mx-auto p-6">
      <h1 className="text-3xl font-black mb-8">Giỏ hàng</h1>

      <div className="grid grid-cols-1 gap-6">
        {cartItems.map((item) => (
          <div
            key={item.id}
            className="flex items-center gap-6 bg-white p-4 rounded-2xl shadow-sm border border-gray-100"
          >
            {/* Ảnh sản phẩm */}
            <img
              src={item.images?.[0] || "https://via.placeholder.com/150"}
              alt={item.name}
              className="w-24 h-24 object-cover rounded-xl bg-gray-50"
            />

            {/* Thông tin sản phẩm */}
            <div className="flex-1">
              <h3 className="font-bold text-lg text-gray-800 line-clamp-1">
                {item.name}
              </h3>
              <p className="text-sm text-gray-500 mb-1">
                Đơn giá: {Number(item.price).toLocaleString()}đ
              </p>
              <p className="text-orange-600 font-black">
                Thành tiền:{" "}
                {(Number(item.price) * item.quantity).toLocaleString()}đ
              </p>
            </div>

            {/* Bộ điều khiển số lượng */}
            <div className="flex items-center gap-3">
              <div className="flex items-center border-2 border-gray-100 rounded-xl overflow-hidden bg-gray-50">
                <button
                  onClick={() => updateQuantity(item.id, -1)}
                  className="p-2 hover:bg-white transition-colors"
                >
                  <Minus size={16} />
                </button>
                <span className="px-4 font-bold text-blue-600">
                  {item.quantity}
                </span>
                <button
                  onClick={() => updateQuantity(item.id, 1)}
                  className="p-2 hover:bg-white transition-colors"
                >
                  <Plus size={16} />
                </button>
              </div>

              {/* Nút xóa */}
              <button
                onClick={() => removeFromCart(item.id)}
                className="p-2 text-red-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-all"
              >
                <Trash2 size={20} />
              </button>
            </div>
          </div>
        ))}
      </div>

      {/* Tổng kết giỏ hàng */}
      <div className="mt-10 p-8 bg-blue-600 rounded-3xl text-white flex flex-col md:flex-row justify-between items-center shadow-xl shadow-blue-200">
        <div className="mb-4 md:mb-0">
          <p className="text-blue-100 font-medium">Tổng tiền thanh toán:</p>
          <p className="text-4xl font-black">{totalPrice.toLocaleString()}đ</p>
        </div>
        <button className="bg-orange-500 hover:bg-orange-600 text-white px-12 py-4 rounded-2xl font-black text-lg transition-transform hover:scale-105 active:scale-95 shadow-lg">
          THANH TOÁN NGAY
        </button>
      </div>
    </div>
  );
};

export default Cart;
