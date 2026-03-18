import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom"; // Gộp import
import axiosClient from "../services/axiosClient";
import { ShoppingCart } from "lucide-react";
import { useCart } from "../context/CartContext";

const Home = () => {
  const [products, setProducts] = useState({ content: [], totalElements: 0 });
  const [loading, setLoading] = useState(true);
  const { addToCart } = useCart();
  const [categories, setCategories] = useState([]);

  // 1. Khai báo location TRƯỚC các logic khác
  const location = useLocation();

  // 2. Định nghĩa hàm fetch dữ liệu
  const fetchProducts = async () => {
    setLoading(true);
    try {
      const searchParams = new URLSearchParams(location.search);
      const query = searchParams.get("q")?.trim() || "";
      const catId = searchParams.get("category") || "";

      let endpoint = `/products/list-products?q=${query}`;
      if (catId) {
        endpoint = `/products/filter?categoryId=${catId}`;
      }
      const response = await axiosClient.get(endpoint);
      setProducts(response.data);
    } catch (error) {
      console.error("Lỗi khi lấy dữ liệu:", error);
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => {
    fetchProducts();
  }, [location.search]);

  useEffect(() => {
    const fetchCats = async () => {
      const res = await axiosClient.get("/categories/user");
      setCategories(res.data);
    };
    fetchCats();
  }, []);

  // Lấy query để hiển thị tiêu đề nếu đang tìm kiếm
  const currentQuery = new URLSearchParams(location.search).get("q");

  return (
    <div className="p-4">
      {/* 1. HERO BANNER */}
      {/* <div className="bg-gradient-to-r from-blue-600 to-indigo-700 rounded-3xl p-8 mb-12 text-white flex flex-col md:flex-row items-center justify-between shadow-2xl shadow-blue-200">
        <div className="max-w-md">
          <h1 className="text-4xl font-black mb-4 uppercase tracking-tight">
            Khai phá sức mạnh công nghệ
          </h1>
          <p className="text-blue-100 mb-6 italic font-light">
            Hệ thống G-Store vận hành mượt mà trên Spring Boot & MongoDB.
          </p>
          <button className="bg-white text-blue-700 font-bold px-8 py-3 rounded-full hover:bg-orange-500 hover:text-white transition-all shadow-lg transform hover:-translate-y-1">
            Mua ngay
          </button>
        </div>
        <div className="hidden md:block opacity-20 text-8xl font-black select-none">
          G-STORE
        </div>
      </div> */}

      {/* 2. TIÊU ĐỀ */}
      <div className="flex justify-between items-end mb-8">
        <div>
          <h2 className="text-2xl font-bold text-gray-800">
            {currentQuery
              ? `Kết quả tìm kiếm cho: "${currentQuery}"`
              : "Sản phẩm tiêu biểu"}
          </h2>
          <div className="h-1.5 w-12 bg-orange-500 mt-2 rounded-full"></div>
        </div>
        {products?.content?.length > 0 && (
          <p className="text-gray-500 text-sm">
            Tìm thấy {products.totalElements || products.content.length} sản
            phẩm
          </p>
        )}
      </div>
      <div className="flex flex-wrap gap-3 mb-8">
        <Link
          to="/"
          className={`px-5 py-2 rounded-full text-sm font-medium transition-all ${
            !new URLSearchParams(location.search).get("category")
              ? "bg-blue-600 text-white shadow-md"
              : "bg-gray-100 text-gray-600 hover:bg-gray-200"
          }`}
        >
          Tất cả
        </Link>
        {categories.map((cat) => (
          <Link
            key={cat.id || cat.name}
            to={`/?category=${cat.id}`}
            className={`px-5 py-2 rounded-full text-sm font-medium transition-all ${
              new URLSearchParams(location.search).get("category") === cat.id
                ? "bg-blue-600 text-white shadow-md"
                : "bg-gray-100 text-gray-600 hover:bg-gray-200"
            }`}
          >
            {cat.name}
          </Link>
        ))}
      </div>
      {/* 3. DANH SÁCH SẢN PHẨM */}
      {loading ? (
        <div className="grid grid-cols-2 md:grid-cols-4 gap-6 animate-pulse">
          {[1, 2, 3, 4].map((n) => (
            <div key={n} className="h-80 bg-gray-200 rounded-2xl"></div>
          ))}
        </div>
      ) : !products.content || products.content.length === 0 ? (
        <div className="text-center py-20 bg-gray-50 rounded-3xl border-2 border-dashed border-gray-200">
          <p className="text-gray-400 text-lg">
            Không tìm thấy sản phẩm nào phù hợp.
          </p>
          <Link
            to="/"
            className="text-blue-600 font-bold mt-4 inline-block hover:underline"
          >
            Quay lại xem tất cả sản phẩm
          </Link>
        </div>
      ) : (
        <div className="grid grid-cols-2 md:grid-cols-4 gap-6">
          {products?.content?.map((item) => {
            const productId = item.id;
            return (
              <div
                key={productId}
                className="bg-white p-4 rounded-2xl shadow-sm border border-transparent hover:border-blue-200 hover:shadow-xl transition-all group relative"
              >
                <Link to={`/product/${productId}`} className="block">
                  <div className="bg-gray-50 rounded-xl h-48 mb-4 overflow-hidden flex items-center justify-center">
                    <img
                      src={
                        item.images?.[0] || "https://via.placeholder.com/300"
                      }
                      alt={item.name}
                      className="w-full h-full object-contain group-hover:scale-110 transition-transform duration-500"
                    />
                  </div>
                  <h3 className="font-bold text-gray-800 group-hover:text-blue-600 line-clamp-1">
                    {item.name}
                  </h3>
                </Link>

                <div className="flex items-center justify-between mt-4">
                  <span className="text-lg font-black text-orange-600">
                    {item.price?.toLocaleString()}đ
                  </span>

                  <button
                    onClick={(e) => {
                      e.preventDefault();
                      addToCart(item);
                      alert(`Đã thêm ${item.name} vào giỏ hàng!`);
                    }}
                    className="p-2 bg-blue-50 text-blue-600 rounded-lg hover:bg-blue-600 hover:text-white transition-colors relative z-10"
                  >
                    <ShoppingCart className="w-5 h-5" />
                  </button>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default Home;
