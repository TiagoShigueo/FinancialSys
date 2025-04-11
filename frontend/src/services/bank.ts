import { Bank } from "@/types/bank";
import Cookies from "js-cookie";

const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;

export const getAllBanks = async (): Promise<Bank[] | undefined> => {
  const token = String("Bearer " + Cookies.get("token"));
  try {
    const res = await fetch(`${BASE_URL}/banks/getAllBanks`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });

    if (!res.ok) {
      const errText = await res.text();
      console.error("Erro na resposta:", res.status, errText);
      return;
    }

    const data: Bank[] = await res.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error("Erro na requisição:", error);
  }
};
