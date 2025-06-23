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
            <th>Data</th>
            <th>Banco origem</th>
            <th>Banco destino</th>
            <th>Transação</th>
            <th>Categoria</th>
            <th>Valor</th>
            <th>Descrição</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.idTransaction}>
              <td>{formatDate(String(transaction.date))}</td>
              <td>{transaction.originBank}</td>
              <td>{transaction.destinationBank}</td>
              <td>{transaction.transactionType}</td>
              <td>{transaction.category}</td>
              <td>{formatCurrency(transaction.amount)}</td>
              <td>{transaction.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
