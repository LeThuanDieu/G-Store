import axios from "axios";

const axiosClient = axios.create({
  baseURL: "http://localhost:8080/api/v1", // Thay đổi port theo backend của bạn
  headers: {
    "Content-Type": "application/json",
  },
});

export default axiosClient;
