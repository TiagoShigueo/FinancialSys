"use client";

import Image from "next/image";
import { login } from "@/services/auth";
import { useState } from "react";
import { useRouter } from "next/navigation";
import Cookies from "js-cookie";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();

  const handleLogin = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const token = await login(username, password);
      Cookies.set("token", token, { expires: 1 });
      router.push("/dashboard");
    } catch (err) {
      setError("Usuário ou senha inválidos");
      console.error(err);
    }
  };

  return (
    <div className="h-svh bg-gradient-to-r from-blue-500 flex flex-col items-center justify-center">
      <Image
        src="/LogoDesenhoFinancialSys.png"
        width={200}
        height={200}
        alt="Logo do Financial Sys com um símbolo moderno de crescimento financeiro, composto por um cifrão
        integrado a uma seta apontando para cima, ao lado do nome da empresa em letras maiúsculas e fonte limpa, 
        em tom azul-turquesa sobre fundo transparente"
      />
      <form onSubmit={handleLogin} className="flex flex-col items-center">
        <div className="p-4">
          <label htmlFor="username">Username: </label>
          <input
            type="text"
            className="outline-2 outline-blue-500 rounded-lg"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="p-4">
          <label htmlFor="password">Password: </label>
          <input
            type="password"
            className="outline-2 outline-blue-500 rounded-lg"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {error && <p className="text-red-500">{error}</p>}

        <div>
          <button
            type="submit"
            className="bg-indigo-500 p-2 rounded-lg shadow-lg"
          >
            Login
          </button>
        </div>
      </form>
    </div>
  );
}
