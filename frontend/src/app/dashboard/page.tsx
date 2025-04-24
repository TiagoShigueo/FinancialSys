"use client";

import { logout } from "@/services/auth";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { getAllBalances } from "@/services/bank";
import { Balance } from "@/types/balance";

export default function Dashboard() {
  const router = useRouter();
  const [balances, setBalances] = useState<Balance[]>([]);
  useEffect(() => {
    const fetchBalances = async () => {
      const data = await getAllBalances();
      if (data) setBalances(data);
    };
    fetchBalances();
  }, []);

  const handleLogout = async () => {
    logout();
    router.push("/login");
  };

  return (
    <div className="min-h-screen">
      <h1>Página de Dashboard</h1>

      <button type="submit" onClick={handleLogout}>
        Logout
      </button>
      <div className="flex justify-center">
        {balances.map((balance) => (
          <div
            key={balance.bankId}
            className="p-2 m-2 text-center space-x-2 border-2 rounded-sm"
          >
            <div>{balance.bankName}</div>
            <div> R$ {balance.balance}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
