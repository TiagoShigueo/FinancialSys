"use client";

import { getUserScheduledTransactions } from "@/services/scheduledTransactions";
import { ScheduledTransaction } from "@/types/scheduledTransaction";
import { formatDate } from "@/utils/formatDate";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function ScheduledTransactions() {
  const [scheduledTransactions, setScheduledTransactions] = useState<
    ScheduledTransaction[]
  >([]);
  const router = useRouter();
  const newScheduledTransaction = async () => {
    router.push("/scheduledTransactions/newScheduledTransaction");
  };

  useEffect(() => {
    const fetchScheduledTransactions = async () => {
      const data = await getUserScheduledTransactions();
      if (data) setScheduledTransactions(data);
    };
    fetchScheduledTransactions();
  }, []);

  return (
    <div>
      <h1>Transações agendadas</h1>
      <button
        type="submit"
        className="bg-green-600 hover:bg-green-700"
        onClick={newScheduledTransaction}
      >
        Nova transação agendada
      </button>
      <table className="table-auto border-separate border border-green-800">
        <thead>
          <tr>
            <th>Frequência</th>
            <th>Dia do pagamento</th>
            <th>Categoria</th>
            <th>Parcelas</th>
            <th>Valor</th>
            <th>Banco</th>
            <th>Descrição</th>
          </tr>
        </thead>
        <tbody>
          {scheduledTransactions.map((scheduledTransaction) => (
            <tr key={scheduledTransaction.idScheduledTransaction}>
              <td>{scheduledTransaction.recurenceType}</td>
              <td>{formatDate(String(scheduledTransaction.paymentDate))}</td>
              <td>{scheduledTransaction.category}</td>
              <td>{scheduledTransaction.totalInstallments}</td>
              <td>{scheduledTransaction.amount}</td>
              <td>{scheduledTransaction.bankId}</td>
              {/* Acho que preciso criar uma type para retorno com o nome do banco */}
              <td>{scheduledTransaction.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
