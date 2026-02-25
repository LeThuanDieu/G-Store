# 🛒 G-Store - Nền tảng Thương mại Điện tử Đa năng

G-Store là một ứng dụng Full-stack Web hỗ trợ người dùng mua sắm trực tuyến và quản trị viên quản lý kho hàng. Dự án tập trung vào tính toàn vẹn dữ liệu, hiệu năng cao và bảo mật với JWT.

## 🚀 Tính năng nổi bật
- **Quản lý Sản phẩm (CRUD):** Thêm, sửa, xóa sản phẩm với hình ảnh (tích hợp Cloudinary).
- **Giỏ hàng & Đơn hàng:** Xử lý logic đặt hàng và tính toán tổng tiền phía Server.
- **Bảo mật:** Phân quyền người dùng (User/Admin) sử dụng JWT (JSON Web Token).
- **Kho hàng:** Quản lý số lượng tồn kho theo thời gian thực (Real-time stock update).
- **Tìm kiếm & Phân trang:** Tối ưu tốc độ tải trang khi dữ liệu lớn.

## 🛠 Tech Stack
- **Frontend:** React (Vite), Tailwind CSS, Axios.
- **Backend:** Java Spring Boot, Spring Security.
- **Database:** MySQL (hoặc MongoDB).
- **Tools:** Maven, Git, Docker.

## 📁 Cấu trúc thư mục
- `/client`: Mã nguồn Frontend (React).
- `/backend`: Mã nguồn Backend (Spring Boot).
- `/docs`: Chứa sơ đồ Database (ERD) và tài liệu API.

## ⚙️ Hướng dẫn cài đặt

### 1. Cấu hình Backend
- Mở thư mục `backend/src/main/resources/application.properties`.
- Cập nhật thông tin kết nối Database của bạn.
- Chạy lệnh:
  ```bash
  ./mvnw spring-boot:run
