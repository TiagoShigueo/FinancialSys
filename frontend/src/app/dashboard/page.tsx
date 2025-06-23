"use client";

import { useEffect, useState } from "react";
import { getAllBalances } from "@/services/bank";
import { Balance } from "@/types/balance";
import { CategorySummary } from "@/types/categorySummary";
import {
  getExpenseCategorySummary,
  getIncomeCategorySummary,
  getMonthlySummary,
} from "@/services/transaction";
import { MonthlySummary } from "@/types/monthlySummary";
import dynamic from "next/dynamic";
import { ScheduledTransaction } from "@/types/scheduledTransaction";
import {
  getUserGroupedScheduledTransactions,
  getUserScheduledTransactions,
} from "@/services/scheduledTransactions";
import { formatDate } from "@/utils/formatDate";
import { PaymentDateGroup } from "@/types/paymentDateGroup";
import { lightningCssTransform } from "next/dist/build/swc/generated-native";
import { formatCurrency } from "@/utils/formatCurrency";
const MonthlyBalanceChart = dynamic(
  () => import("@/components/Charts/MonthlyBalanceChart"),
  {
    ssr: false,
  }
);

export default function Dashboard() {
  const [balances, setBalances] = useState<Balance[]>([]);
  const [incomeCategoriesSummary, setIncomeCategoriesSummary] = useState<
    CategorySummary[]
  >([]);
  const [expenseCategoriesSummary, setExpenseCategoriesSummary] = useState<
    CategorySummary[]
  >([]);
  const [scheduledTransactionsSummary, setScheduledTransactionsSummary] =
    useState<PaymentDateGroup[]>([]);
  const [monthlySummary, setMonthlySummary] = useState<MonthlySummary[]>([]);

  const sortedMonthlySummary = [...monthlySummary].sort(
    (a, b) => a.year * 12 + a.month - (b.year * 12 + b.month)
  );
  const months = sortedMonthlySummary.map((item) => {
    const monthNames = [
      "Jan",
      "Fev",
      "Mar",
      "Abr",
      "Mai",
      "Jun",
      "Jul",
      "Ago",
      "Set",
      "Out",
      "Nov",
      "Dez",
    ];
    return monthNames[item.month - 1];
  });
  const entries = sortedMonthlySummary.map((item) => item.totalIncome);
  const expenses = sortedMonthlySummary.map((item) => item.totalExpense);

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

      const scheduledTransactionsSummary =
        await getUserGroupedScheduledTransactions();
      if (scheduledTransactionsSummary)
        setScheduledTransactionsSummary(scheduledTransactionsSummary);

      const monthlySummary = await getMonthlySummary();
      if (monthlySummary) setMonthlySummary(monthlySummary);
    };
    fetchBalances();
  }, []);

  return (
    <div className="min-h-screen">
      {/* Patrimônio */}
      <h1 className="text-center font-bold text-xl justify-center">
        Patrimônio
      </h1>
      <div className="flex justify-center">
        {balances.map((balance) => (
          <div
            key={balance.bankId}
            className="p-2 m-2 w-40 text-center space-x-2 border-2 rounded-sm bg-gray-700"
          >
            <div>{balance.bankName}</div>
            <div> {formatCurrency(balance.balance)}</div>
          </div>
        ))}
      </div>

      {/* Resumos */}

      <div className="pt-4 flex">
        <div className="bg-gray-700 border-2 h-64 w-2/6 overflow-y-auto">
          <h1 className="text-center font-bold text-xl">Entradas</h1>
          {incomeCategoriesSummary.map((incomeCategorySummary) => (
            <ul key={incomeCategorySummary.category}>
              <li>
                {incomeCategorySummary.category}:
                {formatCurrency(incomeCategorySummary.total)}
              </li>
            </ul>
          ))}
        </div>
        <div className="bg-gray-700 border-2 h-64 w-2/6 mx-4 overflow-y-auto">
          <h1 className="text-center font-bold text-xl">Saídas</h1>
          {expenseCategoriesSummary.map((expenseCategorySummary) => (
            <ul key={expenseCategorySummary.category}>
              <li>
                {expenseCategorySummary.category}:{" "}
                {formatCurrency(expenseCategorySummary.total)}
              </li>
            </ul>
          ))}
        </div>

        <div className="bg-gray-700 border-2 h-64 w-2/6 mr-4 overflow-y-auto">
          <h1 className="text-center font-bold text-xl">Pagamentos futuros</h1>{" "}
          {scheduledTransactionsSummary.map((group) => (
            <div key={group.paymentDate}>
              <h2 className="font-bold mt-2">
                {formatDate(group.paymentDate)}
              </h2>

              <ul>
                {group.transactions.map((transaction) => (
                  <li key={transaction.idScheduledTransaction}>
                    {transaction.category}: {formatCurrency(transaction.amount)}
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>

        {/* <div className="bg-gray-700 border-2 h-64 w-2/6 overflow-y-auto">
          <h1 className="text-center font-bold text-xl">Cartões</h1>{" "}
        </div> */}
      </div>

      {/* Gráficos */}
      <div className="bg-gray-700 border-2 h-80 w-full mt-4">
        <MonthlyBalanceChart
          months={months}
          entries={entries}
          expenses={expenses}
        />
      </div>
    </div>
  );
}
