import { jwtVerify } from "jose";
import Cookies from "js-cookie";

const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;

export const login = async (username: string, password: string) => {
  const response = await fetch(`${BASE_URL}/api/users/auth`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  });

  if (!response.ok) {
    throw new Error("Usuário ou senha inválidos");
  }

  const token = await response.text();
  // localStorage.setItem("token", token);
  return token;
};

export const logout = () => {
  Cookies.remove("token");
  // localStorage.removeItem("token");
};

function parseJwt(token: string) {
  try {
    const base64Url = token.split(".")[1];
    const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split("")
        .map((c) => {
          return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join("")
    );

    return JSON.parse(jsonPayload);
  } catch (error) {
    return null;
  }
}

export const getRole = () => {
  const token = Cookies.get("token");
  if (!token) return null;

  const payload = parseJwt(token);
  const role = payload?.role || null;

  return role;
};
