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
  localStorage.setItem("token", token);
  return token;
};

export const logout = () => {
  Cookies.remove("token");
  localStorage.removeItem("token");
};
