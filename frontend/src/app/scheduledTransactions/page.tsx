"use client";

import { getUserScheduledTransactions } from "@/services/scheduledTransactions";
import { ScheduledTransaction } from "@/types/scheduledTransaction";
import { formatCurrency } from "@/utils/formatCurrency";
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
            <th className="px-2">Frequência</th>
            <th className="px-2">Dia do pagamento</th>
            <th className="px-2">Categoria</th>
            <th className="px-2">Parcelas</th>
            <th className="px-2">Valor</th>
            <th className="px-2">Descrição</th>
          </tr>
        </thead>
        <tbody>
          {scheduledTransactions.map((scheduledTransaction) => (
            <tr key={scheduledTransaction.idScheduledTransaction}>
              <td className="px-2 text-center">
                {scheduledTransaction.recurenceType}
              </td>
              <td className="px-2 text-center">
                {formatDate(String(scheduledTransaction.paymentDate))}
              </td>
              <td className="px-2 text-center">
                {scheduledTransaction.category}
              </td>
              <td className="px-2 text-center">
                {scheduledTransaction.totalInstallments}
              </td>
              <td className="px-2 text-center">
                {formatCurrency(scheduledTransaction.amount)}
              </td>
              <td className="px-2">{scheduledTransaction.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
