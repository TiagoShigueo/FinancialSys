import { UserResponse } from "@/types/userResponse";
import Cookies from "js-cookie";

const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;
export const getUsers = async () => {
  const token = String("Bearer " + Cookies.get("token"));
  try {
    const res = await fetch(`${BASE_URL}/users/admin/getUsers`, {
      method: "GET",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    });
    const data: UserResponse[] = await res.json();
    return data;
  } catch (error) {
    console.error("Erro na requisição: ", error);
  }
};
