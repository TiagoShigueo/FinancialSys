import { Transaction } from "@/types/transaction";
import Cookies from "js-cookie";
const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;

// Precisa enviar como Transaction, e não como TransactionRequest. Com isso daria para deletar o TransactionRequest
export const createTransaction = async (transaction: Transaction) => {
  const token = String("Bearer " + Cookies.get("token"));
  // const transaction: Transaction = {
  //   transactionType: "Transferência",
  //   date: new Date("2025-02-21"),
  //   amount: 700.0,
  //   description: null,
  //   category: "Salário",
  //   originBankId: 1,
  //   destinationBankId: 1,
  // };

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
