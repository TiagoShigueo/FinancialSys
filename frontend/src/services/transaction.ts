import { Transaction } from "@/types/transaction";
import { TransactionResponse } from "@/types/transactionResponse";
import Cookies from "js-cookie";
const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;

export const createTransaction = async (transaction: Transaction) => {
  const token = String("Bearer " + Cookies.get("token"));

  try {
    const res = await fetch(`${BASE_URL}/transactions/createTransaction`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(transaction),
    });

    if (!res.ok) {
      const errText = await res.text();
      console.error("Erro na requisição: ", res.status, errText);
      return;
    }

    const data: Transaction = await res.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error("Erro na requisição: ", error);
  }
};

export const getUserTransactions = async () => {
  const token = String("Bearer " + Cookies.get("token"));

  try {
    const res = await fetch(`${BASE_URL}/transactions/getUserTransactions`, {
      method: "GET",
      headers: {
        Authorization: token,
        "Content-Type": "application/json",
      },
    });
    const data: TransactionResponse[] = await res.json();
    return data;
  } catch (error) {
    console.error("Erro na requisição: ", error);
  }
};
