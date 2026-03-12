import React, { useEffect, useState } from "react";
import axiosClient from "../services/axiosClient";
import { ShoppingCart } from "lucide-react"; // Đừng quên cài: npm install lucide-react

const Home = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axiosClient.get("/products/list-products");
        setProducts(response.data);
      } catch (error) {
        console.error("Lỗi khi lấy dữ liệu:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchProducts();
  }, []);

  return (
    <div>
      {/* Hero Banner */}
      <div className="bg-gradient-to-r from-blue-600 to-indigo-700 rounded-3xl p-8 mb-12 text-white flex flex-col md:flex-row items-center justify-between shadow-2xl shadow-blue-200">
        <div className="max-w-md">
          <h1 className="text-4xl font-black mb-4 uppercase tracking-tight">
            Khai phá sức mạnh công nghệ
          </h1>
          <p className="text-blue-100 mb-6 italic font-light">
            Hệ thống G-Store với MongoDB vận hành mượt mà.
          </p>
          <button className="bg-white text-blue-700 font-bold px-8 py-3 rounded-full hover:bg-orange-500 hover:text-white transition-all shadow-lg transform hover:-translate-y-1">
            Mua ngay
          </button>
        </div>
        <div className="hidden md:block opacity-20 text-8xl font-black select-none">
          G-STORE
        </div>
      </div>

      {/* Product List Header */}
      <div className="flex justify-between items-end mb-8">
        <div>
          <h2 className="text-2xl font-bold text-gray-800">Sản phẩm nổi bật</h2>
          <div className="h-1.5 w-12 bg-orange-500 mt-2 rounded-full"></div>
        </div>
        <button className="text-blue-600 font-semibold text-sm hover:underline">
          Xem tất cả
        </button>
      </div>

      {/* Grid Sản phẩm - Dữ liệu thật từ Backend */}
      {loading ? (
        <div className="grid grid-cols-2 md:grid-cols-4 gap-6 animate-pulse">
          {[1, 2, 3, 4].map((n) => (
            <div key={n} className="h-80 bg-gray-200 rounded-2xl"></div>
          ))}
        </div>
      ) : (
        <div className="grid grid-cols-2 md:grid-cols-4 gap-6">
          {products.map((item) => (
            <div
              key={item.id || item._id}
              className="bg-white p-4 rounded-2xl shadow-sm border border-transparent hover:border-blue-200 hover:shadow-xl transition-all group"
            >
              <div className="bg-gray-50 rounded-xl h-48 mb-4 overflow-hidden flex items-center justify-center">
                <img
                  src={item.imageUrl || "https://via.placeholder.com/300"}
                  alt={item.name}
                  className="w-full h-full object-contain group-hover:scale-110 transition-transform duration-500"
                />
              </div>
              <h3 className="font-bold text-gray-800 group-hover:text-blue-600 line-clamp-1">
                {item.name}
              </h3>
              <p className="text-xs text-gray-500 mb-4 line-clamp-2 h-8">
                {item.description || "Chưa có mô tả cho sản phẩm này."}
              </p>
              <div className="flex items-center justify-between">
                <span className="text-lg font-black text-orange-600">
                  {item.price?.toLocaleString()}đ
                </span>
                <button className="p-2 bg-blue-50 text-blue-600 rounded-lg hover:bg-blue-600 hover:text-white transition-colors">
                  <ShoppingCart className="w-5 h-5" />
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Home;
