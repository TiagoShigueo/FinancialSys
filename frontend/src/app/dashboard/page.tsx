"use client";

import Cookies from "js-cookie";
import { logout } from "@/services/auth";
import { useRouter } from "next/navigation";

export default function dashboard() {
  const router = useRouter();

  const handleLogout = async () => {
    logout();
    router.push("/login");
  };

  return (
    <div>
      <h1>Página de Dashboard</h1>

      <button type="submit" onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
}
