/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        // Bạn có thể tùy chỉnh màu sắc thương hiệu G-Store tại đây
        primary: "#2563eb", // Blue
        secondary: "#f97316", // Orange
      },
      boxShadow: {
        card: "0 10px 25px -5px rgba(0, 0, 0, 0.05), 0 8px 10px -6px rgba(0, 0, 0, 0.05)",
      },
    },
  },
  plugins: [],
};
