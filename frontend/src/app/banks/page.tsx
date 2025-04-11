"use client";

import { getAllBanks } from "@/services/bank";
import { Bank } from "@/types/bank";
import { useEffect, useState } from "react";

export default function banks() {
  const [banks, setBanks] = useState<Bank[]>([]);

  useEffect(() => {
    const fetchBanks = async () => {
      const data = await getAllBanks();
      if (data) {
        setBanks(data);
      }
    };
    fetchBanks();
  }, []);

  return (
    <>
      <h1>Bancos</h1>
      <ul>
        {banks.map((bank) => (
          <li key={bank.id}>{bank.name}</li>
        ))}
      </ul>
    </>
  );
}
