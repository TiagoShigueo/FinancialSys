import { Transaction } from "@/types/transaction";
import { TransactionResponse } from "@/types/transactionResponse";
import { CategorySummary } from "@/types/categorySummary";
import Cookies from "js-cookie";
import { MonthlySummary } from "@/types/monthlySummary";
const BASE_URL = process.env.NEXT_PUBLIC_BASE_URL;

export const createTransaction = async (transaction: Transaction) => {
  const token = String("Bearer " + Cookies.get("token"));

  try {
    const res = await fetch(`${BASE_URL}/transactions/createTransaction`, {
      method: "POST",
      headers: {
        Authorization: token,
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

export const getIncomeCategorySummary = async () => {
  const token = String("Bearer " + Cookies.get("token"));

  try {
    const res = await fetch(
      `${BASE_URL}/transactions/getIncomeCategorySummary`,
      {
        method: "GET",
        headers: {
          Authorization: token,
          "Content-Type": "application/json",
        },
      }
    );
    const data: CategorySummary[] = await res.json();
    return data;
  } catch (error) {
    console.error("Erro na requisição: ", error);
  }
};

export const getExpenseCategorySummary = async () => {
  const token = String("Bearer " + Cookies.get("token"));

  try {
    const res = await fetch(
      `${BASE_URL}/transactions/getExpenseCategorySummary`,
      {
        method: "GET",
        headers: {
          Authorization: token,
          "Content-Type": "application/json",
        },
      }
    );
    const data: CategorySummary[] = await res.json();
    return data;
  } catch (error) {
    console.error("Erro na requisição: ", error);
  }
};

export const getMonthlySummary = async () => {
  const token = String("Bearer " + Cookies.get("token"));

  try {
    const res = await fetch(`${BASE_URL}/transactions/getMonthlySummary`, {
      method: "GET",
      headers: {
        Authorization: token,
        "Content-Type": "applocation/json",
      },
    });
    const data: MonthlySummary[] = await res.json();
    return data;
  } catch (error) {
    console.error("Erro na requisição: ", error);
  }
};
