"use client";

import { getUsers } from "@/services/admin";
import { UserResponse } from "@/types/userResponse";
import { useEffect, useState } from "react";

export default function admin() {
  const [users, setUsers] = useState<UserResponse[]>([]);
  useEffect(() => {
    const fetchUsers = async () => {
      const usersData = await getUsers();
      if (usersData) setUsers(usersData);
    };
    fetchUsers();
  });
  return (
    <div className="min-h-screen">
      <h1 className="text-center font-bold text-xl justify-center">
        Administrador
      </h1>
      <table className="table-auto border-separate border border-green-800">
        <thead>
          <tr>
            <th>Nome</th>
            <th>Role</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.idUser}>
              <td>{user.name}</td>
              <td>{user.role}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
