import { ShoppingCart, User, Search, Menu } from "lucide-react";
import { Link, useNavigate } from "react-router-dom"; // Thêm Link và useNavigate
import { useCart } from "../context/CartContext"; // Import hook giỏ hàng
import React, { useState } from "react";

const Layout = ({ children }) => {
  const { cartItems } = useCart(); // Lấy dữ liệu giỏ hàng
  const navigate = useNavigate();
  const [search, setSearch] = useState("");

  const totalItems = cartItems.reduce((sum, item) => sum + item.quantity, 0);
  const handleSearch = (e) => {
    e.preventDefault();
    navigate(`/?q=${search.trim()}`);
  };
  return (
    <div className="min-h-screen flex flex-col font-sans text-gray-900">
      {/* HEADER */}
      <header className="sticky top-0 z-50 bg-white border-b border-gray-100 shadow-sm">
        <div className="max-w-7xl mx-auto px-4 h-16 flex items-center justify-between">
          {/* Logo - Bọc trong Link để click là về Home */}
          <Link
            to="/"
            className="text-2xl font-black text-blue-600 tracking-tighter hover:opacity-80 transition"
          >
            G-STORE<span className="text-orange-500">.</span>
          </Link>

          {/* Search Bar */}
          <div className="hidden md:flex flex-1 max-w-md mx-8">
            <div className="relative w-full">
              <form
                onSubmit={handleSearch}
                className="relative w-full max-w-md"
              >
                {/* Icon Search nằm tuyệt đối bên trái */}
                <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                  <Search className="h-5 w-5 text-gray-400" />
                </div>

                {/* Input phải có padding-left (pl-10) để chữ không đè lên icon */}
                <input
                  type="text"
                  className="block w-full pl-10 pr-3 py-2 border border-gray-300 rounded-full leading-5 bg-white placeholder-gray-500 focus:outline-none focus:ring-1 focus:ring-blue-500 focus:border-blue-500 sm:text-sm transition-all"
                  placeholder="Tìm sản phẩm..."
                  value={search} // hoặc searchTerm tùy bạn đặt tên
                  onChange={(e) => setSearch(e.target.value)}
                />
              </form>
            </div>
          </div>

          {/* Actions */}
          <div className="flex items-center gap-6">
            {/* GIỎ HÀNG - Cập nhật Link và Badge */}
            <Link
              to="/cart"
              className="relative hover:text-blue-600 transition group"
            >
              <ShoppingCart className="w-6 h-6" />
              {totalItems > 0 && (
                <span className="absolute -top-2 -right-2 bg-orange-500 text-white text-[10px] font-bold px-1.5 py-0.5 rounded-full min-w-[18px] text-center animate-bounce">
                  {totalItems}
                </span>
              )}
              {/* Hiệu ứng tooltip nhỏ khi hover */}
              <span className="absolute -bottom-8 left-1/2 -translate-x-1/2 bg-gray-800 text-white text-[10px] px-2 py-1 rounded opacity-0 group-hover:opacity-100 transition whitespace-nowrap">
                Giỏ hàng
              </span>
            </Link>

            <button className="hover:text-blue-600 transition">
              <User className="w-6 h-6" />
            </button>

            <button className="md:hidden">
              <Menu className="w-6 h-6" />
            </button>
          </div>
        </div>
      </header>

      {/* MAIN CONTENT */}
      <main className="flex-grow bg-gray-50/50">
        <div className="max-w-7xl mx-auto px-4 py-8">{children}</div>
      </main>

      {/* FOOTER - Giữ nguyên các thông tin của bạn */}
      <footer className="bg-gray-900 text-gray-400 py-12">
        {/* ... (phần nội dung footer giữ nguyên như bạn đã viết) ... */}
      </footer>
    </div>
  );
};

export default Layout;
