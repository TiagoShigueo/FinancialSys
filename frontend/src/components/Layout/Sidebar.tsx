import Link from "next/link";
import Image from "next/image";
import { useEffect, useState } from "react";
import { getRole } from "@/services/auth";

export default function Sidebar() {
  const [role, setRole] = useState<string | null>();

  useEffect(() => {
    const fetchRole = async () => {
      const userRole = await getRole();
      console.log(userRole);
      if (userRole) setRole(userRole);
    };
    fetchRole();
  });
  return (
    <aside className="w-44  text-white h-screen p-4 overflow-y-auto">
      <nav>
        <Image
          src="/LogoDesenhoFinancialSys.png"
          width={150}
          height={150}
          alt="Logo do Financial Sys com um símbolo moderno de crescimento financeiro, composto por um cifrão
                integrado a uma seta apontando para cima, ao lado do nome da empresa em letras maiúsculas e fonte limpa, 
                em tom azul-turquesa sobre fundo transparente"
        />
        <ul>
          <li className="mb-2">
            <Link
              href="/dashboard"
              className="block hover:bg-gray-700 p-2 rounded"
            >
              Dashboard
            </Link>
          </li>
          <li className="mb-2">
            <Link
              href="/transactions"
              className="block hover:bg-gray-700 p-2 rounded"
            >
              Transações
            </Link>
          </li>
          <li className="mb-2">
            <Link
              href="/scheduledTransactions"
              className="block hover:bg-gray-700 p-2 rounded"
            >
              Transações agendadas
            </Link>
          </li>
          {role === "Admin" && (
            <li className="mb-2">
              <Link
                href="/admin"
                className="block hover:bg-gray-700 p-2 rounded"
              >
                Admin
              </Link>
            </li>
          )}
        </ul>
      </nav>
    </aside>
  );
}
