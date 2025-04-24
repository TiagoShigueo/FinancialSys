"use client";

import { getAllBanks } from "@/services/bank";
import { createTransaction } from "@/services/transaction";
import { Bank } from "@/types/bank";
import { Transaction } from "@/types/transaction";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function NewTransactions() {
  const [transaction, setTransaction] = useState<Transaction>({
    idTransaction: 0,
    transactionType: "Entrada",
    date: new Date(),
    amount: 0,
    description: "",
    category: "",
    originBankId: 1,
    destinationBankId: null,
  });
  const [banks, setBanks] = useState<Bank[]>([]);
  const router = useRouter();
  useEffect(() => {
    const fetchBanks = async () => {
      const data = await getAllBanks();
      if (data) setBanks(data);
    };
    fetchBanks();
  }, []);

  const handleChange = async (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    const { name, value } = e.target;
    setTransaction((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleCreateTransaction = async (event: React.FormEvent) => {
    event.preventDefault();
    createTransaction(transaction);
    router.push("/transactions");
  };

  const toNewBank = async () => {
    router.push("/transactions/banks");
  };

  return (
    <div className="bg-gradient-to-b from-blue-200">
      <h1>Transações</h1>
      <form onSubmit={handleCreateTransaction} method="post">
        <div className="p-4">
          <label>Transação: </label>
          <select
            name="transactionType"
            value={transaction.transactionType}
            onChange={handleChange}
            required
          >
            <option className="text-black" value="Entrada">
              Entrada
            </option>
            <option className="text-black" value="Saída">
              Saída
            </option>
            <option className="text-black" value="Transferência">
              Transferência
            </option>
          </select>

          <label className="pl-4">Data da transação: </label>
          <input type="date" name="date" onChange={handleChange} required />
        </div>

        <div className="p-4">
          <label>Categoria: </label>
          {/* Depois dá para criar uma CRUD das categorias */}
          <input type="text" name="category" onChange={handleChange} required />
          <label>Valor: </label>
          {/* Dá para colocar máscara de dinheiro */}
          <input type="number" name="amount" onChange={handleChange} required />
        </div>

        <div className="p-4">
          <label>Banco de origem: </label>
          <select name="originBankId" onChange={handleChange} required>
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
          {transaction.transactionType === "Transferência" && (
            <>
              <label className="p-4">Banco de destino: </label>
              <select name="destinationBankId" onChange={handleChange}>
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
            </>
          )}
          <button onClick={toNewBank}>Novo banco</button>
        </div>

        <div>
          <button type="submit">Enviar</button>
        </div>
      </form>
    </div>
  );
}
