"use client";

import { getUserTransactions } from "@/services/transaction";
import { TransactionResponse } from "@/types/transactionResponse";
import { formatDate } from "@/utils/formatDate";
import { useEffect, useState } from "react";

export default function Transactions() {
  const [transactions, setTransactions] = useState<TransactionResponse[]>([]);

  useEffect(() => {
    const fetchTransactions = async () => {
      const data = await getUserTransactions();
      if (data) setTransactions(data);
    };
    fetchTransactions();
  }, []);

  return (
    <div>
      <h1>Transações</h1>
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
              {/* Preciso de um método para converter o formato da data */}
              <td>{transaction.originBank}</td>
              <td>{transaction.destinationBank}</td>
              <td>{transaction.transactionType}</td>
              <td>{transaction.category}</td>
              <td>{transaction.amount}</td>
              <td>{transaction.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
