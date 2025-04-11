"use client";

import Cookies from "js-cookie";
import { logout } from "@/services/auth";
import { useRouter } from "next/navigation";

export default function Dashboard() {
  const router = useRouter();

  const handleLogout = async () => {
    logout();
    router.push("/login");
  };

  return (
    <div className="min-h-screen">
      <h1>Página de Dashboard</h1>

      <button type="submit" onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
}
