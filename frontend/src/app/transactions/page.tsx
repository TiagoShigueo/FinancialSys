"use client";

import { getUserTransactions } from "@/services/transaction";
import { TransactionResponse } from "@/types/transactionResponse";
import { formatDate } from "@/utils/formatDate";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { formatCurrency } from "@/utils/formatCurrency";

export default function Transactions() {
  const [transactions, setTransactions] = useState<TransactionResponse[]>([]);
  const router = useRouter();

  useEffect(() => {
    const fetchTransactions = async () => {
      const data = await getUserTransactions();
      if (data) setTransactions(data);
    };
    fetchTransactions();
  }, []);

  const newTransaction = async () => {
    router.push("/transactions/newTransaction");
  };

  return (
    <div>
      <h1>Transações</h1>
      <button type="submit" onClick={newTransaction}>
        Nova transação
      </button>
      <table className="table-auto border-separate border border-green-800">
        <thead>
          <tr>
            <th className="px-2">Data</th>
            <th className="px-2">Banco origem</th>
            <th className="px-2">Banco destino</th>
            <th className="px-2">Transação</th>
            <th className="px-2">Categoria</th>
            <th className="px-2">Valor</th>
            <th className="px-2">Descrição</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.idTransaction}>
              <td className="px-2 text-center">
                {formatDate(String(transaction.date))}
              </td>
              <td className="px-2 text-center">{transaction.originBank}</td>
              <td className="px-2 text-center">
                {transaction.destinationBank}
              </td>
              <td className="px-2 text-center">
                {transaction.transactionType}
              </td>
              <td className="px-2 text-center">{transaction.category}</td>
              <td className="px-2 text-center">
                {formatCurrency(transaction.amount)}
              </td>
              <td className="px-2">{transaction.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
