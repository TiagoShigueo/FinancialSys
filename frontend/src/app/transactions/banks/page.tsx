"use client";

import { createBank, getAllUserBanks } from "@/services/bank";
import { Bank } from "@/types/bank";
import { useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";

export default function Banks() {
  const router = useRouter();
  const [banks, setBanks] = useState<Bank[]>([]);
  const [bank, setBank] = useState<Omit<Bank, "idBank">>({
    name: "",
    initialBalance: 0,
  });
  useEffect(() => {
    const fetchBanks = async () => {
      const data = await getAllUserBanks();
      if (data) {
        setBanks(data);
      }
    };
    fetchBanks();
  }, []);

  const handleChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setBank((prev) => ({
      ...prev,
      [name]: name === "initialBalance" ? parseFloat(value) : value,
    }));
  };

  const handleCreateBank = async (event: React.FormEvent) => {
    event.preventDefault();
    createBank(bank);
    router.push("/transactions/newTransaction");
  };

  return (
    <>
      <h1>Bancos</h1>
      <ul>
        {banks.map((bank) => (
          <li key={bank.idBank}>{bank.name}</li>
        ))}
      </ul>
      <h1>Novo banco</h1>
      <form onSubmit={handleCreateBank}>
        <div className="p-4">
          <label>Nome do banco: </label>
          <input type="text" name="name" onChange={handleChange} required />
        </div>
        <div className="p-4">
          <label>Saldo inicial: R$ </label>
          <input
            type="number"
            step="0.01"
            name="initialBalance"
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <button type="submit">Enviar</button>
        </div>
      </form>
    </>
  );
}
