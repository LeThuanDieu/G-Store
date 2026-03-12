import React from "react";
import { ShoppingCart, User, Search, Menu } from "lucide-react"; // Cài thư viện: npm install lucide-react

const Layout = ({ children }) => {
  return (
    <div className="min-h-screen flex flex-col font-sans text-gray-900">
      {/* HEADER */}
      <header className="sticky top-0 z-50 bg-white border-b border-gray-100 shadow-sm">
        <div className="max-w-7xl mx-auto px-4 h-16 flex items-center justify-between">
          {/* Logo */}
          <div className="text-2xl font-black text-blue-600 tracking-tighter">
            G-STORE<span className="text-orange-500">.</span>
          </div>

          {/* Search Bar (Dành cho bản Desktop) */}
          <div className="hidden md:flex flex-1 max-w-md mx-8">
            <div className="relative w-full">
              <input
                type="text"
                placeholder="Tìm sản phẩm công nghệ..."
                className="w-full bg-gray-100 border-none rounded-full py-2 px-10 focus:ring-2 focus:ring-blue-500 transition-all"
              />
              <Search className="absolute left-3 top-2.5 text-gray-400 w-5 h-5" />
            </div>
          </div>

          {/* Actions */}
          <div className="flex items-center gap-6">
            <button className="relative hover:text-blue-600 transition">
              <ShoppingCart className="w-6 h-6" />
              <span className="absolute -top-2 -right-2 bg-orange-500 text-white text-[10px] font-bold px-1.5 py-0.5 rounded-full">
                0
              </span>
            </button>
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

      {/* FOOTER */}
      <footer className="bg-gray-900 text-gray-400 py-12">
        <div className="max-w-7xl mx-auto px-4 grid grid-cols-1 md:grid-cols-3 gap-8">
          <div>
            <h3 className="text-white font-bold text-lg mb-4">
              G-Store Backend Project
            </h3>
            <p className="text-sm leading-relaxed">
              Hệ thống quản lý kho và bán hàng hiện đại được xây dựng bởi Lê
              Thuận Diệu.
            </p>
          </div>
          <div>
            <h3 className="text-white font-bold mb-4">Danh mục</h3>
            <ul className="space-y-2 text-sm">
              <li>
                <a href="#" className="hover:text-white">
                  Điện thoại
                </a>
              </li>
              <li>
                <a href="#" className="hover:text-white">
                  Laptop
                </a>
              </li>
              <li>
                <a href="#" className="hover:text-white">
                  Phụ kiện
                </a>
              </li>
            </ul>
          </div>
          <div>
            <h3 className="text-white font-bold mb-4">Liên hệ</h3>
            <p className="text-sm">Email: dieult@gstore.com</p>
            <p className="text-sm">Địa chỉ: Da Nang, Vietnam</p>
          </div>
        </div>
        <div className="border-t border-gray-800 mt-8 pt-8 text-center text-xs">
          © 2026 G-Store Project. All rights reserved.
        </div>
      </footer>
    </div>
  );
};

export default Layout;
