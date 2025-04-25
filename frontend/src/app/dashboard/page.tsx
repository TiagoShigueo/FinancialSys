"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { getAllBalances } from "@/services/bank";
import { Balance } from "@/types/balance";
import { CategorySummary } from "@/types/categorySummary";
import {
  getExpenseCategorySummary,
  getIncomeCategorySummary,
} from "@/services/transaction";

export default function Dashboard() {
  const [balances, setBalances] = useState<Balance[]>([]);
  const [incomeCategoriesSummary, setIncomeCategoriesSummary] = useState<
    CategorySummary[]
  >([]);
  const [expenseCategoriesSummary, setExpenseCategoriesSummary] = useState<
    CategorySummary[]
  >([]);

  useEffect(() => {
    const fetchBalances = async () => {
      const balanceData = await getAllBalances();
      if (balanceData) setBalances(balanceData);

      const incomeCategoriesSummaryData = await getIncomeCategorySummary();
      if (incomeCategoriesSummaryData)
        setIncomeCategoriesSummary(incomeCategoriesSummaryData);

      const expenseCategoriesSummary = await getExpenseCategorySummary();
      if (expenseCategoriesSummary)
        setExpenseCategoriesSummary(expenseCategoriesSummary);
    };
    fetchBalances();
  }, []);

  return (
    <div className="min-h-screen">
      <h1 className="text-center font-bold text-xl justify-center">
        Patrimônio
      </h1>
      <div className="flex justify-center">
        {balances.map((balance) => (
          <div
            key={balance.bankId}
            className="p-2 m-2 w-40 text-center space-x-2 border-2 rounded-sm"
          >
            <div>{balance.bankName}</div>
            <div> R$ {balance.balance}</div>
          </div>
        ))}
      </div>
      <div className="pt-4 flex">
        <div className="border-2 h-128 w-2/6">
          <h1 className="text-center font-bold text-xl">Entradas</h1>
          {incomeCategoriesSummary.map((incomeCategorySummary) => (
            <ul key={incomeCategorySummary.category}>
              <li>
                {incomeCategorySummary.category}: R${" "}
                {incomeCategorySummary.total}
              </li>
            </ul>
          ))}
        </div>
        <div className="border-2 h-128 w-2/6 mx-4">
          <h1 className="text-center font-bold text-xl">Saídas</h1>
          {expenseCategoriesSummary.map((expenseCategorySummary) => (
            <ul key={expenseCategorySummary.category}>
              <li>
                {expenseCategorySummary.category}: R${" "}
                {expenseCategorySummary.total}
              </li>
            </ul>
          ))}
        </div>
        <div className="border-2 h-128 w-2/6">
          <h1 className="text-center font-bold text-xl">Cartões</h1>
        </div>
      </div>
    </div>
  );
}
