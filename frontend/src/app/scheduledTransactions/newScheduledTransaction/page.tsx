"use client";

import { getAllUserBanks } from "@/services/bank";
import { createScheduledTransaction } from "@/services/scheduledTransactions";
import { Bank } from "@/types/bank";
import { ScheduledTransaction } from "@/types/scheduledTransaction";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function NewScheduledTransactions() {
  const [banks, setBanks] = useState<Bank[]>([]);
  useEffect(() => {
    const fetchBanks = async () => {
      const data = await getAllUserBanks();
      if (data) setBanks(data);
    };
    fetchBanks();
  }, []);
  const [scheduledTransaction, setScheduledTransaction] =
    useState<ScheduledTransaction>({
      idScheduledTransaction: 0,
      recurenceType: "Entrada",
      paymentDate: new Date(),
      totalInstallments: 0,
      amount: 0,
      description: "",
      category: "",
      bankId: 1,
    });

  const router = useRouter();

  const toScheduledTransactions = async () => {
    router.push("/scheduledTransactions");
  };

  const toNewBank = async () => {
    router.push("/transactions/banks");
  };

  const handleCreateScheduledTransaction = async (event: React.FormEvent) => {
    event.preventDefault();
    console.log(scheduledTransaction);
    createScheduledTransaction(scheduledTransaction);
    router.push("/scheduledTransactions");
  };

  const handleChange = async (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = event.target;
    setScheduledTransaction((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  return (
    <div>
      <button onClick={toScheduledTransactions}>Voltar</button>
      <h1>Nova transação agendada</h1>
      <form onSubmit={handleCreateScheduledTransaction} method="post">
        <div className="p-4">
          <label>Transação: </label>
          <select
            name="recurenceType"
            value={scheduledTransaction.recurenceType}
            onChange={handleChange}
            required
          >
            <option className="text-black" value="Diário">
              Diário
            </option>
            <option className="text-black" value="Quinzenal">
              Quinzenal
            </option>
            <option className="text-black" value="Mensal">
              Mensal
            </option>
            <option className="text-black" value="Anual">
              Anual
            </option>
          </select>
          <label>Data da transação: </label>
          <input
            type="date"
            name="paymentDate"
            onChange={handleChange}
            required
          />
        </div>
        <div className="p-4">
          <label>Categoria: </label>
          <input type="text" name="category" onChange={handleChange} required />
          <label>Valor: </label>
          <input
            type="number"
            step="0.01"
            name="amount"
            onChange={handleChange}
            required
          />
        </div>
        <div className="p-4">
          <label>Total de parcelas: </label>
          <input
            type="number"
            name="totalInstallments"
            onChange={handleChange}
            required
          />

          <label>Descrição: </label>
          <input type="text" name="description" onChange={handleChange} />
        </div>

        <div className="p-4">
          <label>Banco: </label>
          <select name="bankId" onChange={handleChange} required>
            {banks.map((bank) => (
              <option
                key={bank.idBank}
                value={bank.idBank}
                className="text-black"
              >
                {bank.name}
              </option>
            ))}
          </select>
          <button onClick={toNewBank}>Novo banco</button>
        </div>
        <div>
          <button type="submit">Enviar</button>
        </div>
      </form>
    </div>
  );
}
