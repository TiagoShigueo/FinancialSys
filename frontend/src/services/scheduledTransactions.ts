import { ScheduledTransaction } from "@/types/scheduledTransaction";
import Cookies from "js-cookie";

const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;

export const createScheduledTransaction = async (
  scheduledTransaction: ScheduledTransaction
) => {
  const token = String("Bearer " + Cookies.get("token"));

  try {
    const res = await fetch(`${BASE_URL}/scheduled/createScheduled`, {
      method: "POST",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(scheduledTransaction),
    });

    if (!res.ok) {
      const errText = await res.text();
      console.error("Erro na requisição: ", res.status, errText);
      return;
    }

    const data: ScheduledTransaction = await res.json();
    return data;
  } catch (error) {
    console.error("Erro na requisição: ", error);
  }
};

export const getUserScheduledTransactions = async () => {
  const token = String("Bearer " + Cookies.get("token"));

  try {
    const res = await fetch(`${BASE_URL}/scheduled/getUserScheduled`, {
      method: "GET",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    });
    const data: ScheduledTransaction[] = await res.json();
    return data;
  } catch (error) {
    console.error("Erro na requisição: ", error);
  }
};
