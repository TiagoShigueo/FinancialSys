"use client";

import Cookies from "js-cookie";
import { logout } from "@/services/auth";
import { useRouter } from "next/navigation";
import { decodeJwt } from "jose";
import { useEffect, useState } from "react";

export default function Navbar() {
  const router = useRouter();
  const [username, setUsername] = useState<string>("");
  useEffect(() => {
    const token = Cookies.get("token");
    if (token) {
      try {
        const user = decodeJwt(token);
        if (user.sub) {
          setUsername(String(user.sub));
        }
      } catch (error) {
        console.error("Erro ao decodificar o token: " + error);
      }
    }
  }, []);

  const handleLogout = async () => {
    logout();
    router.push("/login");
    // Quem sabe no futuro eu redirecione para a página principal
  };

  return (
    <nav className="w-full h-14  px-6 flex items-center justify-between text-white">
      <h1 className="text-lg">Olá {username}</h1>
      <button
        onClick={handleLogout}
        className="bg-blue-600 text-white font-medium py-1 px-4 rounded hover:bg-blue-300 transition"
      >
        Logout
      </button>
    </nav>
  );
}
